const db = require('../models');

const Desa = db.desa;

const checkDuplicateUsernameOrEmail = async (req, res) => {
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
