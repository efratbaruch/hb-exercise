package pages

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import util.DateSelectionLocatorUtil
import util.Driver.driver
import util.ElementLocator
import java.time.LocalDate

object LandingPageLocators {
    const val DESTINATION_SEARCH_CSS = "input[data-testid='structured-search-input-field-query']"
    const val CHECK_IN_BUTTON_XPATH = "//*[@id=\"search-tabpanel\"]/div[1]/div[3]/div[1]/div"
    const val GUESTS_BUTTON_CSS = "div[data-testid='structured-search-input-field-guests-button']"
    const val SEARCH_BUTTON_CSS = "button[data-testid='structured-search-input-search-button']"
    const val ADD_ADULT_BUTTON_CSS = "button[data-testid='stepper-adults-increase-button']"
    const val ADD_CHILD_BUTTON_CSS = "button[data-testid='stepper-children-increase-button']"
    const val ADD_INFANT_BUTTON_CSS = "button[data-testid='stepper-infants-increase-button']"
    const val ADD_PET_BUTTON_CSS = "button[data-testid='stepper-pets-increase-button']"
    const val LITTLE_SEARCH_CSS = "div[data-testid='little-search']"
}

class LandingPage() {

    val destinationSearchTextField: WebElement
        get() = ElementLocator().findStableElement(By.cssSelector(LandingPageLocators.DESTINATION_SEARCH_CSS))

    val checkInButton: WebElement
        get() = driver.findElement(By.xpath(LandingPageLocators.CHECK_IN_BUTTON_XPATH))

    val guestSelectionButton: WebElement
        get() = driver.findElement(By.cssSelector(LandingPageLocators.GUESTS_BUTTON_CSS))

    val searchButton: WebElement
        get() = driver.findElement(By.cssSelector(LandingPageLocators.SEARCH_BUTTON_CSS))

    val addAdultButton: WebElement
        get() = driver.findElement(By.cssSelector(LandingPageLocators.ADD_ADULT_BUTTON_CSS))

    val addChildButton: WebElement
        get() = driver.findElement(By.cssSelector(LandingPageLocators.ADD_CHILD_BUTTON_CSS))

    val addInfantButton: WebElement
        get() = driver.findElement(By.cssSelector(LandingPageLocators.ADD_INFANT_BUTTON_CSS))

    val addPetButton: WebElement
        get() = driver.findElement(By.cssSelector(LandingPageLocators.ADD_PET_BUTTON_CSS))

    fun getStartDateElement(date: LocalDate = LocalDate.now()): WebElement {
        return driver.findElement(DateSelectionLocatorUtil().getDateByLocator(date, 0))
    }

    fun getEndDateElement(date: LocalDate, daysToAdd: Long): WebElement {
        return driver.findElement(DateSelectionLocatorUtil().getDateByLocator(date, daysToAdd))
    }

    val littleSearch: WebElement
        get() = driver.findElement(By.cssSelector(LandingPageLocators.LITTLE_SEARCH_CSS))
}