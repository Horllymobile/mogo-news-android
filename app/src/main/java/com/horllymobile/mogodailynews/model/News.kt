package com.horllymobile.mogodailynews.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class News(
    val id: String,
    val title: String,
    val time: String,
    val link: String,
    val blog: String,
    @SerialName("created_at")
    val createdAt: String,
)
