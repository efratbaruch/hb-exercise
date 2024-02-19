package blocks

import org.openqa.selenium.support.ui.ExpectedConditions
import util.ClickUtils.clickByTimes
import util.Driver.wait
import util.PageAndBlockInit
import util.VacationParameters

class VacationSearchBlocks : PageAndBlockInit() {

    //This method inputs all the data received from the vacation params and clicks search.
    fun searchForStay(vacationParameters: VacationParameters) {
        inputDestination(vacationParameters)

        chooseVacationDates(vacationParameters)

        landingPage.guestSelectionButton.click()
        addGuests(vacationParameters)

        landingPage.searchButton.click()
    }

    //This method inputs destination to the search text field.
    private fun inputDestination(vacationParameters: VacationParameters) {
        wait.until(ExpectedConditions.elementToBeClickable(landingPage.destinationSearchTextField))
        landingPage.destinationSearchTextField.click()
        landingPage.destinationSearchTextField.sendKeys(vacationParameters.location)
    }

    // This method selects the vacation dates by creating the date locator dynamically.
    // It supports search for the current 2-month period. For further away date selection a month scroll
    // logic will need to be added.
    private fun chooseVacationDates(vacationParameters: VacationParameters) {
        openDatesCalendar()
        landingPage.getStartDateElement(vacationParameters.startDate).click()
        landingPage.getEndDateElement(vacationParameters.startDate, vacationParameters.vacationLength).click()
    }

    // This method opens the dates calendar by clicking the checkIn button. It was necessary because
    // At some point the website closed the search menu once the button was clicked.
    private fun openDatesCalendar() {
        landingPage.checkInButton.click()

        try {
            wait.until(ExpectedConditions.visibilityOf(landingPage.littleSearch))
            landingPage.littleSearch.click()
        } catch (e: Exception) {
            println("Little search was not shown" + e.message)
        }

        wait.until(ExpectedConditions.elementToBeClickable(landingPage.checkInButton))
        landingPage.checkInButton.click()
    }

    // This method adds guests according to the guest group requested.
    private fun addGuests(vacationParameters: VacationParameters) {
        val guests = vacationParameters.guestGroup
        if (guests.adults > 0) {
            clickByTimes(landingPage.addAdultButton, guests.adults)
        }
        if (guests.children > 0) {
            clickByTimes(landingPage.addChildButton, guests.children)
        }
        if (guests.infants > 0) {
            clickByTimes(landingPage.addInfantButton, guests.infants)
        }
        if (guests.pets > 0) {
            clickByTimes(landingPage.addPetButton, guests.adults)
        }
    }
}