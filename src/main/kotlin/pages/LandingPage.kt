package pages

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import util.DateLocatorUtil
import util.Driver.driver
import util.ElementLocator
import java.time.LocalDate

object AirBnbLandingLocators{
    // Todo: improve locators
    const val DESTINATION_SEARCH_CSS = "input[data-testid='structured-search-input-field-query']"
    const val CHECK_IN_BUTTON_CSS = "//*[@id=\"search-tabpanel\"]/div[1]/div[3]/div[1]/div"
    const val CHECK_OUT_BUTTON_XPATH = "//*[@id=\"search-tabpanel\"]/div[1]/div[3]/div[3]/div"
    const val GUESTS_BUTTON_CSS = "div[data-testid='structured-search-input-field-guests-button']"
    const val SEARCH_BUTTON_CSS = "button[data-testid='structured-search-input-search-button']"
    const val ADD_ADULT_BUTTON_CSS = "button[data-testid='stepper-adults-increase-button']"
    const val ADD_CHILD_BUTTON_CSS = "button[data-testid='stepper-children-increase-button']"
    const val ADD_INFANT_BUTTON_CSS = "button[data-testid='stepper-infants-increase-button']"
    const val ADD_PET_BUTTON_CSS = "button[data-testid='stepper-pets-increase-button']"
    const val LITTLE_SEARCH_CSS = "div[data-testid='little-search']"
}

class AirBnbLandingPage () {

    val destinationSearchTextField : WebElement
        get() = ElementLocator().findStableElement(By.cssSelector(AirBnbLandingLocators.DESTINATION_SEARCH_CSS))

    val checkInButton : WebElement
        get() = driver.findElement(By.xpath(AirBnbLandingLocators.CHECK_IN_BUTTON_CSS))

    val checkOutButton : WebElement
        get() = driver.findElement(By.xpath(AirBnbLandingLocators.CHECK_OUT_BUTTON_XPATH))

    val guestSelectionButton : WebElement
        get() = driver.findElement(By.cssSelector(AirBnbLandingLocators.GUESTS_BUTTON_CSS))

    val searchButton : WebElement
        get() = driver.findElement(By.cssSelector(AirBnbLandingLocators.SEARCH_BUTTON_CSS))

    val addAdultButton : WebElement
        get() = driver.findElement(By.cssSelector(AirBnbLandingLocators.ADD_ADULT_BUTTON_CSS))

    val addChildButton: WebElement
        get() = driver.findElement(By.cssSelector(AirBnbLandingLocators.ADD_CHILD_BUTTON_CSS))

    val addInfantButton: WebElement
        get() = driver.findElement(By.cssSelector(AirBnbLandingLocators.ADD_INFANT_BUTTON_CSS))

    val addPetButton: WebElement
        get() = driver.findElement(By.cssSelector(AirBnbLandingLocators.ADD_PET_BUTTON_CSS))

    fun getStartDateElement(date: LocalDate = LocalDate.now()): WebElement{
        return driver.findElement(DateLocatorUtil().getDateByLocator(date, 0))
    }

    fun getEndDateElement(date: LocalDate, daysToAdd: Long): WebElement{
        return driver.findElement(DateLocatorUtil().getDateByLocator(date, daysToAdd))
    }

    val littleSearch: WebElement
        get() = driver.findElement(By.cssSelector(AirBnbLandingLocators.LITTLE_SEARCH_CSS))
}