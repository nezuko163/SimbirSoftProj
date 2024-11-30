package com.nezuko.domain.model

import java.time.LocalDate

data class BusinessMonth(
    val month: LocalDate,
    val businessDayList: List<BusinessDay>
)
