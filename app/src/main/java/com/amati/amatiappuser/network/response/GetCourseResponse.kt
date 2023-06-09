package com.amati.amatiappuser.network.response

import com.google.gson.annotations.SerializedName

data class GetCourseResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("statusCode")
	val statusCode: Int,

	@field:SerializedName("ambilKursus")
	val ambilKursus: AmbilKursus
)

data class AmbilKursus(

	@field:SerializedName("jumlahModul")
	val jumlahModul: Int,

	@field:SerializedName("idPengguna")
	val idPengguna: Int,

	@field:SerializedName("modulSekarang")
	val modulSekarang: Int,

	@field:SerializedName("idKursus")
	val idKursus: Int,

	@field:SerializedName("statusSelesai")
	val statusSelesai: Boolean,

	@field:SerializedName("id")
	val id: Int
)
