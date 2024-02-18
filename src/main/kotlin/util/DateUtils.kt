package util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateUtils {

    fun convertLocalDateToStringFormat(localDate: LocalDate) : String {
        val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
        return localDate.format(formatter)
    }

    //todo: improve return
    private fun convertStringDateToLocalDate(strDate: String): LocalDate {

        val paddedDate = formatPaddedDate(strDate)
        val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")

        return try {
            LocalDate.parse(paddedDate, formatter)
        } catch (e: Exception) {
            println("Error parsing date: ${e.message}")
            LocalDate.now()
        }
    }

    fun verifyDate(actualDate: String, expectedDate: LocalDate): Boolean {
        val date = convertStringDateToLocalDate(actualDate)
        return (date == expectedDate)
    }

    private fun formatPaddedDate(date: String): String {
        val parts = date.split("/")
        val paddedParts = parts.map { it.trim().padStart(2, '0') }
        return "${paddedParts[0]}/${paddedParts[1]}/${paddedParts[2]}"
    }
}