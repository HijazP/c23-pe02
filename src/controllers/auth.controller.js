const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');
const db = require('../models');
const config = require('../config/auth.config');

const Desa = db.desa;

exports.signup = async (req, res) => {
  // Save Desa to Database
  try {
    const desa = await Desa.create({
      Email: req.body.email,
      Password: bcrypt.hashSync(req.body.password, 8),
    });
    if (desa) res.send({ message: 'Desa sukses terdaftar!' });
  } catch (error) {
    res.status(500).send({ message: error.message });
  }
};

exports.signin = async (req, res) => {
  try {
    const desa = await Desa.findOne({
      where: {
        Email: req.body.email,
      },
    });

    if (!desa) {
      return res.status(404).send({ message: 'Desa tidak ditemukan!' });
    }

    const passwordIsValid = bcrypt.compareSync(
      req.body.password,
      desa.Password,
    );

    if (!passwordIsValid) {
      return res.status(401).send({
        message: 'Password tidak valid!',
      });
    }

    const token = jwt.sign({ ID: desa.ID }, config.secret, {
      expiresIn: 86400, // 24 hours
    });

    req.session.token = token;

    return res.status(200).send({
      ID: desa.ID,
      Email: desa.Email,
    });
  } catch (error) {
    return res.status(500).send({ message: error.message });
  }
};

exports.signout = async (req, res) => {
  try {
    req.session = null;
    return res.status(200).send({
      message: 'Kamu telah sign out!',
    });
  } catch (err) {
    this.next(err);
  }
};
