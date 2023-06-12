package com.amati.amatiappuser.network.response

import com.google.gson.annotations.SerializedName

data class RekomendasiMasalahResponse(

	@field:SerializedName("masalahdesa")
	val masalahdesa: List<MasalahdesaItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("recDesa")
	val recDesa: List<RecDesaItem>,

	@field:SerializedName("statusCode")
	val statusCode: Int
)

data class MasalahdesaItem(

	@field:SerializedName("idDesa")
	val idDesa: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("deskripsi")
	val deskripsi: String,

	@field:SerializedName("namaMasalah")
	val namaMasalah: String
)

data class RecDesaItem(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("namaDesa")
	val namaDesa: String,

	@field:SerializedName("foto")
	val foto: String,

	@field:SerializedName("latitude")
	val latitude: Any,

	@field:SerializedName("telepon")
	val telepon: String,

	@field:SerializedName("lokasiDesa")
	val lokasiDesa: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("longitude")
	val longitude: Any
)
