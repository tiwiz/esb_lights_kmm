package com.rob.lights.shared

import kotlinx.serialization.Serializable

@Serializable
data class DayLight(
    val image: String,
    val color: String,
    val reason: String,
    val date: String
)

@Serializable
data class Lights(
    val todayColor: String,
    val picture: String,
    val calendar: List<DayLight>
)
