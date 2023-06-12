package com.amati.amatiappuser.network.response

import com.google.gson.annotations.SerializedName

data class ListModulResponse(

	@field:SerializedName("allModul")
	val allModul: List<AllModulItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("statusCode")
	val statusCode: Int
)

data class AllModulItem(

	@field:SerializedName("idKursus")
	val idKursus: Int,

	@field:SerializedName("namaModul")
	val namaModul: String,

	@field:SerializedName("id")
	val id: Int
)
