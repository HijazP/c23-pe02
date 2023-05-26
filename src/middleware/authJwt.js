const jwt = require('jsonwebtoken');
const config = require('../config/auth.config');

const verifyToken = (req, res) => {
  const { token } = req.session;

  if (!token) {
    return res.status(403).send({
      message: 'Tidak ada token yang diberikan!',
    });
  }

  jwt.verify(token, config.secret, (err, decoded) => {
    if (err) {
      return res.status(401).send({
        message: 'Tidak berwenang!',
      });
    }
    req.userId = decoded.id;
  });
};

const authJwt = {
  verifyToken,
};
module.exports = authJwt;
