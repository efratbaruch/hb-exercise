package util

import java.time.LocalDate

// todo: Add a comment about a more complicated date search with the capability to look for far ahead dates.
data class VacationParameters(
    var location: String,
    var startDate : LocalDate,
    var vacationLength: Long,
    var guestGroup: GuestGroup
)
