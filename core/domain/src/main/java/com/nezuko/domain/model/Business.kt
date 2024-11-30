package com.nezuko.domain.model

import java.time.LocalDateTime

data class Business(
    val id: Int,
    val timeStart: LocalDateTime,
    val timeEnd: LocalDateTime,
    val name: String,
    val description: String,
    val color: Int
)
