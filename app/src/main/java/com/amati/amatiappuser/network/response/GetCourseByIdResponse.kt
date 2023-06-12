package com.amati.amatiappuser.network.response

import com.google.gson.annotations.SerializedName

data class GetCourseByIdResponse(

	@field:SerializedName("statusCode")
	val statusCode: Int,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("ambilKursus")
	val ambilKursus: AmbilKursus?,

	@field:SerializedName("checkAmbilKursus")
	val checkAmbilKursus: CheckAmbilKursus?,

	@field:SerializedName("modul")
	val modul: Modul?
)

data class CheckAmbilKursus(

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

data class AmbilKursus(

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

data class Modul(

	@field:SerializedName("idKursus")
	val idKursus: Int,

	@field:SerializedName("namaModul")
	val namaModul: String,

	@field:SerializedName("id")
	val id: Int
)

