const express = require('express');
const cors = require('cors');
const cookieSession = require('cookie-session');

const app = express();
app.use(cors());

const db = require('./src/models');

db.sequelize.sync();

// parse requests of content-type: application/json
app.use(express.json());

// parse requests of content-type: application/x-www-form-urlencoded
app.use(express.urlencoded({ extended: true }));

app.use(cookieSession({
  name: 'amati-session',
  secret: 'COOKIE_SECRET',
  httpOnly: true,
}));

// simple route
app.get('/', (req, res) => {
  res.json({ message: 'Welcome to amati application.' });
});

// routes
require('./src/routes/auth.routes')(app);
require('./src/routes/desa.routes')(app);

// set port, listen for requests
const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}.`);
});
