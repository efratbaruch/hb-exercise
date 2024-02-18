package blocks

import util.*

class ListingDetailsBlocks : PageAndBlockInit() {

    fun isListingDatesCorrect(vacationParameters: VacationParameters): Boolean {
        val dateUtils = DateUtils()
        val isCheckInDateCorrect = dateUtils.verifyDate(listingPage.checkInDate.text, vacationParameters.startDate)
        val isCheckOutDateCorrect = dateUtils.verifyDate(
            listingPage.checkOutDate.text,
            vacationParameters.startDate.plusDays(vacationParameters.vacationLength)
        )
        return (isCheckInDateCorrect && isCheckOutDateCorrect)
    }

    fun isNumOfGuestsCorrect(guestGroup: GuestGroup): Boolean {
        val numOfGuests = listingPage.guestsButton.text[0].digitToInt()
        val expectedGuests = guestGroup.adults + guestGroup.children + guestGroup.infants + guestGroup.pets

        return (numOfGuests == expectedGuests)
    }

    //todo: think about adding all types
    // if needs be logics for all types of guests can be added
    fun changeNumOfGuests(oldGuestGroup: GuestGroup, newGuestGroup: GuestGroup) {
        if (oldGuestGroup.children > newGuestGroup.children) {
            ClickUtils.clickByTimes(listingPage.removeChildGuestButton, oldGuestGroup.children - newGuestGroup.children)
        } else if (oldGuestGroup.children < newGuestGroup.children) {
            ClickUtils.clickByTimes(listingPage.addChildGuestButton, newGuestGroup.children - oldGuestGroup.children)
        }
    }

    fun changeDatesIfAvailableAndVerify(newVacationParameters: VacationParameters): Boolean {
        val newStartDate =
            Driver.driver.findElement(DateLocatorUtil().getDateByLocator(newVacationParameters.startDate, 0))
        val isStartDateBlocked = newStartDate.getAttribute("data-is-day-blocked").toBoolean()

        val newEndDate = Driver.driver.findElement(
            DateLocatorUtil().getDateByLocator(
                newVacationParameters.startDate,
                newVacationParameters.vacationLength
            )
        )
        val isNewEndDateBlocked = newEndDate.getAttribute("data-is-day-blocked").toBoolean()

        if (!isStartDateBlocked && !isNewEndDateBlocked) {
            newStartDate.click()
            newEndDate.click()

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

}