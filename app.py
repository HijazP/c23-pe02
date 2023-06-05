from typing import List
import pandas as pd
import pickle
from fastapi import FastAPI
import sklearn 
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
from tensorflow.keras.models import load_model

app = FastAPI()

with open('./load_model/cos_sim.pkl', 'rb') as file:
    cos_sim = pickle.load(file)

with open('./load_model/cosine_similarity.pkl', 'rb') as file:
    cosine_similarity = pickle.load(file)

with open('./load_model/course.pkl', 'rb') as file:
    course = pickle.load(file)

with open('./load_model/tfidf_matrix.pkl', 'rb') as file:
    tfidf_matrix = pickle.load(file)

# Generate recommendations
def recommendations(name, model, dataset, cos_sim):
    recommended_course = []

    # Get the index of the input course title
    idx = dataset.index.get_loc(name)

    # Reshape the cosine similarity matrix for the input data
    input_data = cos_sim.reshape(len(dataset), -1)

    # Predict the TF-IDF matrix for the input course
    predicted_tfidf = model.predict(input_data[idx].reshape(1, -1)).reshape(-1,)

    # Calculate the cosine similarity between the predicted and actual TF-IDF matrices
    score_series = pd.Series(cosine_similarity(predicted_tfidf.reshape(1, -1), tfidf_matrix).flatten())

    # Get the top 10 indexes with the highest cosine similarity scores
    top_10_indexes = list(score_series.sort_values(ascending=False).iloc[1:11].index)

    for i in top_10_indexes:
        recommended_course.append(dataset.index[i])

    return recommended_course

# Memuat model dari file .h5
model = load_model('./load_model/model.h5')

@app.get("/recommendations/{name}")
def get_recommendations(name: str):
    title = name
    recommended_course = recommendations(title, model, course, cos_sim)
    return {"rekomendasi":recommended_course}


###############################


with open('./load_model/course_clean.pkl', 'rb') as file:
    course_clean = pickle.load(file)

with open('./load_model/prob_clean.pkl', 'rb') as file:
    prob_clean = pickle.load(file)


def find_nearest_similarity(dataset1, dataset2):
    # Extract text and index from DataFrame
    text1 = dataset1['summary'].tolist()
    text2 = dataset2['problem'].tolist()
    index1 = dataset1.index.tolist()
    index2 = dataset2.index.tolist()

    # Vectorize the datasets using TF-IDF
    vectorizer = TfidfVectorizer()

    vectors1 = vectorizer.fit_transform(text1)
    vectors2 = vectorizer.transform(text2)

    # Calculate cosine similarity
    similarity_matrix = cosine_similarity(vectors1, vectors2)

    # Find the nearest similarity for each row in dataset1
    nearest_similarities = {}
    nearest_indices = {}

    for i in range(len(dataset1)):
        max_similarity = -1
        max_index = -1

        for j in range(len(dataset2)):
            if similarity_matrix[i, j] > max_similarity:
                max_similarity = similarity_matrix[i, j]
                max_index = index2[j]

        nearest_similarities[index1[i]] = max_similarity
        nearest_indices[index1[i]] = max_index

    return nearest_indices, nearest_similarities

def get_top_3_similar_indices(index1, similarity_matrix):
    similarities = similarity_matrix[index1]
    sorted_indices = similarities.argsort()[::-1][:3]

    return sorted_indices.tolist()


@app.get("/predict/{input}")
def get_recommendations(input: str):

    nearest_indices, nearest_similarities = find_nearest_similarity(course_clean, prob_clean)

    return {"Nearest similarity pair for [{}]: [{}], Similarity: {}".format([input], nearest_indices[input], nearest_similarities[input])}