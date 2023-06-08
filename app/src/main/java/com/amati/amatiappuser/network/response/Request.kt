package com.amati.amatiappuser.network.response

data class RequestLogin(
    var email: String,
    var password: String
)

data class RequestReg(
    var email: String,
    var password: String
)

data class RequestProfil(
    var namaDesa: String,
    var telepon: String,
    var lokasi: String,
    var latitude: Float = Float.MIN_VALUE,
    var longitude: Float = Float.MIN_VALUE
)