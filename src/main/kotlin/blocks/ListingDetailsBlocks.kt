package blocks

import org.openqa.selenium.WebElement
import util.*
import util.Driver.driver
import java.time.LocalDate

class ListingDetailsBlocks : PageAndBlockInit() {

    // This method verifies that the presented dates are as expected.
    fun isListingDatesCorrect(vacationParameters: VacationParameters): Boolean {
        val dateUtils = DateUtils()

        val isCheckInDateCorrect = dateUtils.verifyDate(listingPage.checkInDate.text, vacationParameters.startDate)
        val isCheckOutDateCorrect = dateUtils.verifyDate(
            listingPage.checkOutDate.text,
            vacationParameters.startDate.plusDays(vacationParameters.vacationLength)
        )

        return (isCheckInDateCorrect && isCheckOutDateCorrect)
    }

    // This method verifies that the presented total guest number is as expected.
    fun isNumOfGuestsCorrect(guestGroup: GuestGroup): Boolean {
        val numOfGuests = listingPage.guestsButton.text[0].digitToInt()
        val expectedGuests = guestGroup.adults + guestGroup.children + guestGroup.infants + guestGroup.pets

        return (numOfGuests == expectedGuests)
    }

    // This method updates the number of guests. This method currently handles only child guests,
    // but can be easily expanded to all guest groups if needed, under the same logic.
    fun changeNumOfGuests(oldGuestGroup: GuestGroup, newGuestGroup: GuestGroup) {
        changeNumOfChildGuest(oldGuestGroup.children, newGuestGroup.children)
    }

    private fun changeNumOfChildGuest(oldChildNum: Int, newChildNum: Int) {
        if (oldChildNum > newChildNum) {
            ClickUtils.clickByTimes(listingPage.removeChildGuestButton, oldChildNum - newChildNum)
        } else if (oldChildNum < newChildNum) {
            ClickUtils.clickByTimes(listingPage.addChildGuestButton, newChildNum - oldChildNum)
        }
    }

    // This method attempts to change the vacation dates and returns the success status.
    fun changeDatesIfAvailableAndVerify(newVacationParameters: VacationParameters): Boolean {
        // Get new date elements and verify date status, i.e if blocked.
        val newStartDate = getDateElementAndBlockedStatus(newVacationParameters.startDate, 0)
        val newEndDate =
            getDateElementAndBlockedStatus(newVacationParameters.startDate, newVacationParameters.vacationLength)

        //if none both date are blocked attempt to click
        if (!newStartDate.second && !newEndDate.second) {
            newStartDate.first.click()
            newEndDate.first.click()

            // Sometimes the check-out date becomes blocked after click on the check-in date, so the
            // vacations dates are verified and in case of failure, all dates are cleared and false is returned.
            try {
                val isSuccess = isListingDatesCorrect(newVacationParameters)
                return isSuccess
            } catch (e: IndexOutOfBoundsException) {
                println(e.message)
                listingPage.clearDatesButton.click()
            }
        }
        return false
    }

    private fun getDateElementAndBlockedStatus(date: LocalDate, daysToAdd: Long): Pair<WebElement, Boolean> {
        val dateElement = driver.findElement(DateSelectionLocatorUtil().getDateByLocator(date, daysToAdd))
        val blockedStatus = dateElement.getAttribute("data-is-day-blocked").toBoolean()
        return Pair(dateElement, blockedStatus)
    }

}