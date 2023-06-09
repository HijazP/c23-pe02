package com.amati.amatiappuser.network.response

import com.google.gson.annotations.SerializedName

data class GetAllProgressResponse(

	@field:SerializedName("progress")
	val progress: List<ProgressItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("statusCode")
	val statusCode: Int
)

data class ProgressItem(

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
