import express from "express";
import cors from "cors";
import ProblemRoute from "./routes/ProblemRoute.js";

const app = express();
app.use(cors());
app.use(express.json());
app.use(ProblemRoute);

app.listen(5000, ()=> console.log('Server up and running...'));