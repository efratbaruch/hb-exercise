package tests

import org.openqa.selenium.support.ui.ExpectedConditions
import org.testng.Assert
import org.testng.annotations.Test
import util.Driver.driver
import util.Driver.wait
import util.GuestGroup
import util.PageAndBlockInit
import util.UrlUtils
import util.VacationParameters
import java.time.LocalDate

object TestVacationSearchConst {
    const val URL = "https://www.airbnb.com"
}

class TestVacationSearch : PageAndBlockInit() {

    // Normally, I would have divided this test scenario to smaller tests to improve stability.
    @Test
    fun testVacationSearch() {
        // Arrange: Navigate to page & prepare vacation data for test
        UrlUtils().openPage(TestVacationSearchConst.URL)

        var vacationParameters =
            VacationParameters(
                "london",
                LocalDate.now().plusDays(1),
                1,
                GuestGroup(2, 1)
            )
        val secondVacationParameters =
            VacationParameters(
                "london",
                LocalDate.now().plusDays(8),
                1,
                GuestGroup(2, 0, 0, 0)
            )

        // Act: Search for a Stay
        vacationSearchBlocks.searchForStay(vacationParameters)

        // Act: Select a Listing
        searchResultsBlocks.clickHighestRateListing()

        // Act: Confirm Booking Details
        translationPopUp.closeTranslationPopUp()
        wait.until(ExpectedConditions.visibilityOf(listingPage.guestsButton))
        Assert.assertTrue(listingDetailsBlocks.isListingDatesCorrect(vacationParameters))
        Assert.assertTrue(listingDetailsBlocks.isNumOfGuestsCorrect(vacationParameters.guestGroup))

        // Act: Adjust and Verify Guest Count
        listingPage.guestsButton.click()
        listingDetailsBlocks.changeNumOfGuests(vacationParameters.guestGroup, secondVacationParameters.guestGroup)
        Assert.assertTrue(listingDetailsBlocks.isNumOfGuestsCorrect(secondVacationParameters.guestGroup))
        vacationParameters.guestGroup = secondVacationParameters.guestGroup

        // Act: Change Booking Dates and confirm changes. If the change failed the previous dates
        // are reselected.
        if (listingDetailsBlocks.changeDatesIfAvailableAndVerify(secondVacationParameters)) {
            vacationParameters = secondVacationParameters
        } else {
            listingDetailsBlocks.changeDatesIfAvailableAndVerify(vacationParameters)
        }

        // Act: Reserve and Validate
        listingPage.clickReserveButton()
        wait.until(ExpectedConditions.visibilityOf(confirmationPage.pageAnchor))
        Assert.assertTrue(confirmationPage.isConfirmationPageUrl())
        Assert.assertTrue(confirmationPage.isAdultNumberCorrectOnUrl(vacationParameters.guestGroup.adults.toString()))

        driver.quit()
    }
}