package com.amati.amatiappuser.network.response

import com.google.gson.annotations.SerializedName

data class GetAllCourseResponse(

	@field:SerializedName("kursus")
	val kursus: List<KursusItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("statusCode")
	val statusCode: Int
)

data class KursusItem(

	@field:SerializedName("dampak")
	val dampak: String,

	@field:SerializedName("foto")
	val foto: String,

	@field:SerializedName("namaKursus")
	val namaKursus: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("deskripsi")
	val deskripsi: String
)
