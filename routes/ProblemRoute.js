import express from "express";
import { getProblem, getProblemById, createProblem, updateProblem, deleteProblem } from "../controllers/ProblemController.js";

const router = express.Router();

router.get("/problem", getProblem);
router.get("/problem/:id", getProblemById);
router.post("/problem", createProblem);
router.put("/problem/:id", updateProblem);
router.delete("/problem/:id", deleteProblem);

export default router;
