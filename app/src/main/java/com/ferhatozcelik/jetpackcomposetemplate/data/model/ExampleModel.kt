package com.ferhatozcelik.jetpackcomposetemplate.data.model

import com.google.gson.annotations.SerializedName

data class ExampleModel(
    @SerializedName("title")
    var title: String?,
    @SerializedName("description")
    var description: String?
)
