package com.amati.amatiappuser.network.response

data class RequestLogin(
    var email: String,
    var password: String
)

data class RequestReg(
    var namaLengkap: String,
    var email: String,
    var password: String
)

data class RequestProfil(
    var namaLengkap: String,
    var email: String,
    var password: String,
    var foto: String
)