package com.amati.amatiapp.network

data class RequestLogin(
    var email: String,
    var password: String
)

data class RequestReg(
    var name: String,
    var email: String,
    var password: String
)