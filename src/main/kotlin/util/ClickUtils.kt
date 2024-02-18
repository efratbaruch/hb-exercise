package util

import org.openqa.selenium.WebElement

object ClickUtils {

    fun clickByTimes(element: WebElement, numOfClicks: Int){
        for (i in 1..numOfClicks){
            element.click()
        }
    }
}