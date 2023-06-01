package com.amati.amatiapp.response

data class RequestLogin(
    var name: String,
    var password: String
)

data class RequestReg(
    var name: String,
    var email: String,
    var password: String
)