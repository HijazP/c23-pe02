package com.amati.amatiapp.network.response

import com.google.gson.annotations.SerializedName

data class ProblemResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("masalah")
	val masalah: Masalah,

	@field:SerializedName("statusCode")
	val statusCode: Int
)

data class Masalah(

	@field:SerializedName("idDesa")
	val idDesa: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("deskripsi")
	val deskripsi: String,

	@field:SerializedName("namaMasalah")
	val namaMasalah: String
)
