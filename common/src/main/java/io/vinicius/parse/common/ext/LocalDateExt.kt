package io.vinicius.parse.common.ext

import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

fun LocalDate.toDate(zoneId: String) = Date.from(this.atStartOfDay(ZoneId.of(zoneId)).toInstant())