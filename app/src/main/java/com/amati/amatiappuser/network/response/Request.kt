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
    var nama: String,
    var telepon: String
)