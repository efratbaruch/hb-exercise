package util

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import util.Driver.driver

class ElementLocator() {

    fun findStableElement(locator : By, maxAttempt: Int = 5): WebElement{
        var count : Int = 0
        do {
            try {
                return driver.findElement(locator)
            }catch (e: Exception){
                count++
            }
        } while(count<=maxAttempt)
        return driver.findElement(locator)
    }
}