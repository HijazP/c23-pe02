package com.amati.amatiapp.network.response

import com.google.gson.annotations.SerializedName

data class DetailResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("masalah")
	val masalah: List<DetailMasalahItem?>? = null,

	@field:SerializedName("statusCode")
	val statusCode: Int? = null
)

data class DetailMasalahItem(

	@field:SerializedName("idDesa")
	val idDesa: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("namaMasalah")
	val namaMasalah: String? = null
)
