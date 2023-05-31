package com.amati.amatiapp.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestLogin(
    var email: String? = null,
    var password: String? = null
)

data class RequestReg(
    var email: String? = null,
    var password: String? = null,
    var passwordConfirm: String? = null
)
