package util

import java.time.LocalDate

data class VacationParameters(
    var location: String,
    var startDate: LocalDate,
    var vacationLength: Long,
    var guestGroup: GuestGroup
)
