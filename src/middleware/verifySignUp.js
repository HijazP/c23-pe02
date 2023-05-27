const db = require('../models');
const Desa = db.Desa;

const checkDuplicateUsernameOrEmail = async (req, res, next) => {
  try {
    // Email
    const desa = await Desa.findOne({
      where: {
        Email: req.body.email,
      },
    });

    if (desa) {
      return res.status(400).send({
        message: 'Email sudah terdaftar!',
      });
    }

    next();
  } catch (error) {
    return res.status(500).send({
      message: 'Tidak dapat memproses permintaan!',
    });
  }
};

const verifySignUp = {
  checkDuplicateUsernameOrEmail,
};

module.exports = verifySignUp;
