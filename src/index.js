const express = require("express");
const multer = require("multer");
const fs = require("fs");
const path = require("path");

const app = express();
const PORT = process.env.PORT || 3000;

// upload papka
const upload = multer({ dest: "uploads/" });

// static fayllar (html)
app.use(express.static("public"));

// rasm qabul qilish
app.post("/capture", upload.single("photo"), (req, res) => {
    console.log("Rasm keldi:", req.file);

    res.send("Rasm qabul qilindi ✅");
});

app.listen(PORT, () => {
    console.log("Server ishlayapti port: " + PORT);
});
