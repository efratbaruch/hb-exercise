package blocks

import org.openqa.selenium.Keys
import org.openqa.selenium.support.ui.ExpectedConditions
import util.ClickUtils.clickByTimes
import util.Driver.wait
import util.PageAndBlockInit
import util.VacationParameters

class VacationSearchBlocks : PageAndBlockInit() {

    //todo: naming of block method
    fun searchForStay(vacationParameters: VacationParameters) {
        inputDestination(vacationParameters)

        chooseVacationDates(vacationParameters)

        airBnbLandingPage.guestSelectionButton.click()
        addGuests(vacationParameters)

        airBnbLandingPage.searchButton.click()
    }

    private fun inputDestination(vacationParameters: VacationParameters) {
        wait.until(ExpectedConditions.elementToBeClickable(airBnbLandingPage.destinationSearchTextField))
        airBnbLandingPage.destinationSearchTextField.click()
        airBnbLandingPage.destinationSearchTextField.sendKeys(vacationParameters.location, Keys.RETURN)
    }

    private fun chooseVacationDates(vacationParameters: VacationParameters) {
        try {
            wait.until(ExpectedConditions.visibilityOf(airBnbLandingPage.littleSearch))
            airBnbLandingPage.littleSearch.click()
        } catch (e: Exception){
            println("Little search was not shown" + e.message)
        }

        wait.until(ExpectedConditions.elementToBeClickable(airBnbLandingPage.checkInButton))
        airBnbLandingPage.checkInButton.click()
        airBnbLandingPage.getStartDateElement(vacationParameters.startDate).click()
        airBnbLandingPage.getEndDateElement(vacationParameters.startDate, vacationParameters.vacationLength).click()
    }

    // todo: find a more elegant solution. should consider a solution that knows to subtract guests as well
    private fun addGuests(vacationParameters: VacationParameters) {
        val guests = vacationParameters.guestGroup
        if (guests.adults > 0) {
            clickByTimes(airBnbLandingPage.addAdultButton, guests.adults)
        }
        if (guests.children > 0) {
            clickByTimes(airBnbLandingPage.addChildButton, guests.children)
        }
        if (guests.infants > 0) {
            clickByTimes(airBnbLandingPage.addInfantButton, guests.infants)
        }
        if (guests.pets > 0) {
            clickByTimes(airBnbLandingPage.addPetButton, guests.adults)
        }
    }
}