package com.amati.amatiapp.network.response

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("password_confirm")
	val passwordConfirm: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)
