const express = require('express');

// impor fungsi registerUser dan loginUser dari file authController.js
const { registerUser, loginUser } = require('../controllers/authController');

// membuat router Express
const router = express.Router();

// menentukan route POST di endpoint /register atau /login yang akan memanggil fungsi registerUser atau loginUser
router.post("/register", registerUser);
router.post("/login", loginUser);

module.exports = router;