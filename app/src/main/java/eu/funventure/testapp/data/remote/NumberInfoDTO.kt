package eu.funventure.testapp.data.remote

import com.google.gson.annotations.SerializedName

data class NumberInfoDTO(

    @SerializedName("number")
    val number: Int,

    @SerializedName("text")
    val text: String,

    @SerializedName("isFound")
    val isFound: Boolean,

    @SerializedName("type")
    val type: String
)

// type = trivia or math
