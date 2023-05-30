package com.amati.amatiapp.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestLogin(
    @SerializedName("name")
    @Expose
    var email: String? = null,
    @SerializedName("password")
    @Expose
    var password: String? = null
)

data class RequestReg(
    @SerializedName("name")
    @Expose
    var email: String? = null,
    @SerializedName("password")
    @Expose
    var password: String? = null,
    @SerializedName("password_confirm")
    @Expose
    var passwordConfirm: String? = null
)
