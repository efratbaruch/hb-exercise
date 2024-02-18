package util

import org.openqa.selenium.By
import java.time.LocalDate

object DateElementsUtilConst {
    const val DATE_LOCATOR_CSS_PREFIX = "div[data-testid='calendar-day-"
    const val DATE_LOCATOR_CSS_SUFFIX = "']"
}

class DateLocatorUtil {

    fun getDateByLocator(date: LocalDate = LocalDate.now(), daysToAdd: Long) : By {
        return By.cssSelector(getDateStringLocator(date, daysToAdd))
    }

    private fun getDateStringLocator(date: LocalDate = LocalDate.now(), daysToAdd: Long): String {
        val dateCssLocatorString: String =
            DateElementsUtilConst.DATE_LOCATOR_CSS_PREFIX + createVacationRelativeDates(date, daysToAdd) + DateElementsUtilConst.DATE_LOCATOR_CSS_SUFFIX

        return dateCssLocatorString
    }

    private fun createVacationRelativeDates(date: LocalDate = LocalDate.now(), daysToAdd: Long = 1): String {
        val neededDate = date.plusDays(daysToAdd)
        return DateUtils().convertLocalDateToStringFormat(neededDate)
    }
}