package com.amati.amatiappuser.network.response

import com.google.gson.annotations.SerializedName

data class RekomendasiNamaDesaResponse(

	@field:SerializedName("Kabupaten")
	val kabupaten: String,

	@field:SerializedName("Rekomendasi Desa")
	val rekomendasiDesa: String
)
