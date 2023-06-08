package com.amati.amatiappuser.network.response

import com.google.gson.annotations.SerializedName

data class GetProfilResponse(
    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("user")
    val user: List<UserItem>,

    @field:SerializedName("statusCode")
    val statusCode: Int
)

data class UserItem(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("namaLengkap")
    val namaLengkap: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("foto")
    val foto: String
)