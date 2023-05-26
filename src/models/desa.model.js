module.exports = (sequelize, Sequelize) => {
  const desa = sequelize.define('Desa', {
    ID: {
      type: Sequelize.INTEGER(10),
      primaryKey: true,
    },
    Email: {
      type: Sequelize.STRING,
    },
    Password: {
      type: Sequelize.STRING,
    },
    NamaDesa: {
      type: Sequelize.STRING,
    },
    Telepon: {
      type: Sequelize.STRING(15),
    },
    LokasiDesa: {
      type: Sequelize.STRING,
    },
    Latitude: {
      type: Sequelize.FLOAT,
    },
    Longitude: {
      type: Sequelize.FLOAT,
    },
  });

  return desa;
};
