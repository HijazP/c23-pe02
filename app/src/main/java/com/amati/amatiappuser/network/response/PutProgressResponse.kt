package com.amati.amatiappuser.network.response

import com.google.gson.annotations.SerializedName

data class PutProgressResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("updateKursus")
	val updateKursus: UpdateKursus,

	@field:SerializedName("statusCode")
	val statusCode: Int
)

data class UpdateKursus(

	@field:SerializedName("jumlahModul")
	val jumlahModul: Int,

	@field:SerializedName("idPengguna")
	val idPengguna: Int,

	@field:SerializedName("statusModul")
	val statusModul: Int,

	@field:SerializedName("modulSekarang")
	val modulSekarang: Int,

	@field:SerializedName("idKursus")
	val idKursus: Int,

	@field:SerializedName("statusSelesai")
	val statusSelesai: Boolean,

	@field:SerializedName("id")
	val id: Int
)
