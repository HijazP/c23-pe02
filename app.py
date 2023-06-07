from typing import List
import pandas as pd
import pickle
from fastapi import FastAPI
import sklearn 
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

app = FastAPI()

course_names = {
    0: "Fundamental Course (1)",
    1: "Fundamental Course (2)",
    2: "Fundamental Course (3)",
    3: "Indonesia Sustainability Coral Reef University Network (ISCORE)",
    4: "Ecotourism",
    5: "Moringa Academy",
    6: "Indonesia Sustainable Social Forestry Education Program (IS-FREE)",
    7: "Waste Management",
    8: "Integrated Farming",
    9: "Solar Academy",
    10: "Program Startup"
}
   
course_id = {
    0: 1,
    1: 2,
    2: 3,
    3: 4,
    4: 5,
    5: 6,
    6: 7,
    7: 8,
    8: 9,
    9: 10,
    10: 11
}

# Load the necessary data
with open('./load_model/cos_sim_course.pkl', 'rb') as f:
    cos_sim_course = pickle.load(f)

with open('./load_model/indices.pkl', 'rb') as f:
    indices = pickle.load(f)

def recommendations(name: str, cos_sim=cos_sim_course) -> List[str]:
    recommended_course = []
    idx = indices[indices == name].index[0]
    score_series = pd.Series(cos_sim[idx]).sort_values(ascending=False)
    top_10_indexes = list(score_series.iloc[1:11].index)


    for i in top_10_indexes:
        course_index = list(indices.index)[i]
        course_name = course_names.get(course_index)
        recommended_course.append(list(indices.index)[i])

    return recommended_course

@app.get("/recommendations/{name}")
def get_recommendations(name: str):
    recommended_courses = recommendations(name)
    recommended_course_names = [course_names[i] for i in recommended_courses]
    recommended_course_id = [course_id[i] for i in recommended_courses]
    #berdasarkan nama course
    return {"Kursus Saat Ini": name,"Rekomendasi": recommended_course_names}


#Berdasarkan ID
#@app.get("/recommendations/{course_id}")
#def get_recommendations(course_id: int):
#    course_name = course_names.get(course_id)
#    recommended_courses = recommendations(course_name)
#    recommended_course_id = [course_id[i] for i in recommended_courses]
#    return {"Kursus Saat Ini": course_name, "Rekomendasi": recommended_course_id}


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