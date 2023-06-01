package com.amati.amatiapp.network.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("success")
	val success: Boolean,

)