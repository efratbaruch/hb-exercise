package pages

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import util.Driver.driver

object ListingPageLocators {
    const val CHECK_IN_DATE_CSS = "div[data-testid='change-dates-checkIn']"
    const val CHECK_OUT_DATE_CSS = "div[data-testid='change-dates-checkOut']"
    const val GUEST_BUTTON_ID = "GuestPicker-book_it-trigger"
    const val DECREASE_CHILD_GUEST_CSS =
        "button[data-testid='GuestPicker-book_it-form-children-stepper-decrease-button']"
    const val INCREASE_CHILD_GUEST_CSS =
        "button[data-testid='GuestPicker-book_it-form-infants-stepper-increase-button']"
    const val CLEAR_DATE_BUTTON_XPATH = "//button[contains(text(), 'Clear dates')]"
    const val CONFIRM_BUTTON_CSS = "button[data-testid='homes-pdp-cta-btn']"
}

class ListingPage() {

    val checkInDate: WebElement
        get() = driver.findElement(By.cssSelector(ListingPageLocators.CHECK_IN_DATE_CSS))

    val checkOutDate: WebElement
        get() = driver.findElement(By.cssSelector(ListingPageLocators.CHECK_OUT_DATE_CSS))

    val guestsButton: WebElement
        get() = driver.findElement(By.id(ListingPageLocators.GUEST_BUTTON_ID))

    val removeChildGuestButton: WebElement
        get() = driver.findElement(By.cssSelector(ListingPageLocators.DECREASE_CHILD_GUEST_CSS))

    val addChildGuestButton: WebElement
        get() = driver.findElement(By.cssSelector(ListingPageLocators.INCREASE_CHILD_GUEST_CSS))

    val clearDatesButton: WebElement
        get() = driver.findElement(By.xpath(ListingPageLocators.CLEAR_DATE_BUTTON_XPATH))

    val reserveButton: WebElement
        get() = driver.findElement(By.cssSelector(ListingPageLocators.CONFIRM_BUTTON_CSS))

    // The reserve button was sometimes not intractable. This problem is solved by using js to click.
    fun clickReserveButton() {
        (driver as JavascriptExecutor).executeScript("arguments[0].click();", reserveButton)
    }
}