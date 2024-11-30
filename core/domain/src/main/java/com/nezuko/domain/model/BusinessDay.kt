package com.nezuko.domain.model

import java.time.LocalDate

data class BusinessDay(
    val date: LocalDate,
    val businessList: List<Business>
)
