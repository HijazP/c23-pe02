package com.amati.amatiapp.network.response

import com.google.gson.annotations.SerializedName

data class GetProfilResponse(
    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("desa")
    val desa: List<DesaItem>,

    @field:SerializedName("statusCode")
    val statusCode: Int
)

data class DesaItem(
    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("namaDesa")
    val namaDesa: String,

    @field:SerializedName("telepon")
    val telepon: String,

    @field:SerializedName("lokasiDesa")
    val lokasiDesa: String,

    @field:SerializedName("longitude")
    val longitude: Float,

    @field:SerializedName("latitude")
    val latitude: Float,

    @field:SerializedName("foto")
    val foto: String
)