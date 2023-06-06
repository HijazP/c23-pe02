package com.amati.amatiapp.response

data class RequestLogin(
    var name: String,
    var password: String
)

data class RequestReg(
    var name: String,
    var password: String,
    var password_confirm: String
)

data class RequestProfil(
    var nama: String,
    var telepon: String,
    var lokasi: String,
    var latitude: Float = Float.MIN_VALUE,
    var longitude: Float = Float.MIN_VALUE
)