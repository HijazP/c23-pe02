package com.amati.amatiapp.response

data class RequestLogin(
    var email: String,
    var password: String
)

data class RequestReg(
    var email: String,
    var password: String,
    var namaDesa: String,
    var telepon: String,
)

data class RequestProfil(
    var namaDesa: String,
    var telepon: String,
    var lokasi: String,
    var latitude: Float = Float.MIN_VALUE,
    var longitude: Float = Float.MIN_VALUE
)

data class RequestProblem(
    var namaMasalah: String,
    var deskripsi: String
)