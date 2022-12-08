package com.example.radioplayer.entity

import com.example.radioplayer.Enums.LoginState
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class User (

    @SerializedName("id")
    var userId: Int = 0,

    var name: String = "",

    var password: String = "",

    var loginState: LoginState = LoginState.SIGNED_OFF,

)
