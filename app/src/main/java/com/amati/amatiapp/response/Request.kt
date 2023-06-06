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
    var name: String? = null,
    var password: String? = null,
    var password_confirm: String? = null,
    var nama_desa: String,
    var telepon: String,
    var lokasi: String,
    var latitude: Float,
    var longitude: Float
)