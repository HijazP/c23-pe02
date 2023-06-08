package com.amati.amatiappuser.network.response

import com.google.gson.annotations.SerializedName

data class ProfilResponse(
    @field:SerializedName("code")
    val code: Int,

    @field:SerializedName("success")
    val success: Boolean,
)