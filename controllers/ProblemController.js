import Problem from "../models/ProblemModel.js";

export const getProblem = async (req, res) => {
  try {
    const response = await Problem.findAll();
    res.status(200).json(response);
  } catch (error) {
    console.log(error.message);
  }
};

export const getProblemById = async (req, res) => {
  try {
    const response = await Problem.findOne({
      where: {
        id: req.params.id,
      },
    });
    res.status(200).json(response);
  } catch (error) {
    console.log(error.message);
  }
};

export const createProblem = async (req, res) => {
  try {
    await Problem.create(req.body);
    res.status(201).json({ message: "Problem Added" });
  } catch (error) {
    console.log(error.message);
  }
};

export const updateProblem = async (req, res) => {
  try {
    await Problem.update(req.body, {
      where: {
        id: req.params.id,
      },
    });
    res.status(200).json({ message: "Problem Updated" });
  } catch (error) {
    console.log(error.message);
  }
};

export const deleteProblem = async (req, res) => {
  try {
    await Problem.destroy({
      where: {
        id: req.params.id,
      },
    });
    res.status(200).json({ message: "Problem Deleted" });
  } catch (error) {
    console.log(error.message);
  }
};
