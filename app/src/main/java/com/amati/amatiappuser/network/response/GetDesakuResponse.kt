package com.amati.amatiappuser.network.response

import com.google.gson.annotations.SerializedName

data class GetDesakuResponse (
    @field:SerializedName("kursus")
    val kursus: String
)