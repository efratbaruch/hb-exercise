package pages

import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.WebElement
import util.Driver.driver

object TranslationPopUpLocators {
    const val TRANSLATION_ANNOUNCE_CLOSE_BUTTON_CSS = "button[aria-label='Close']"
}

class TranslationPopUp {

    val closeButton: WebElement
        get() = driver.findElement(By.cssSelector(TranslationPopUpLocators.TRANSLATION_ANNOUNCE_CLOSE_BUTTON_CSS))

    // On most of the listings a translation pop up appears and this method checks if that is the case,
    // and handles it.
    fun closeTranslationPopUp() {
        // Wait for pop-up to appear on screen. Implicit wait is not possible since active window is the background screen
        Thread.sleep(3000)

        // Get all windows
        val windowHandles = driver.windowHandles

        // Switch to the pop-up window and close it
        if (windowHandles.size > 1) {
            try {
                driver.switchTo().window(windowHandles.last())
                closeButton.click()
            } catch (e: NoSuchElementException) {
                println(e.message + " Translation popup not presented")
            }
        }
    }
}