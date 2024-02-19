package util

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import util.Driver.driver

class ElementLocator() {

    // This method purpose is an attempt to prevent the StaleElementReferenceException that sometimes
    // is thrown at the beginning of the test scenario.
    fun findStableElement(locator: By, maxAttempt: Int = 5): WebElement {
        var count = 0
        do {
            try {
                return driver.findElement(locator)
            } catch (e: Exception) {
                count++
            }
        } while (count <= maxAttempt)
        return driver.findElement(locator)
    }
}