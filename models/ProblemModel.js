import { Sequelize } from "sequelize";
import db from "../config/Database.js";

const { DataTypes } = Sequelize;

const Problem = db.define(
  "problem",
  {
    problem: DataTypes.STRING,
    description: DataTypes.STRING,
  },
  {
    freezeTableName: true,
  }
);

export default Problem;

(async () => {
  await db.sync();
})();
