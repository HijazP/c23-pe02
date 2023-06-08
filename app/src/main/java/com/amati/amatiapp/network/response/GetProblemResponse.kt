package com.amati.amatiapp.network.response

import com.google.gson.annotations.SerializedName

data class GetProblemResponse(

	@field:SerializedName("totalMasalah")
	val totalMasalah: Int,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("masalah")
	val masalah: List<MasalahItem>,

	@field:SerializedName("statusCode")
	val statusCode: Int
)

data class MasalahItem(

	@field:SerializedName("idDesa")
	val idDesa: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("deskripsi")
	val deskripsi: String,

	@field:SerializedName("namaMasalah")
	val namaMasalah: String
)
