package pages

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import util.Driver.driver
import util.UrlUtils
import java.net.URI

object ConfirmationPageConst {
    const val PAGE_ANCHOR_CSS = "div[data-testid='transition-layer']"
    const val PAGE_URL_PATH = "/book/stays/"
    const val ADULTS_QUERY_PARAM = "numberOfAdults"
}

class ConfirmationPage() {

    val pageAnchor: WebElement
        get() = driver.findElement(By.cssSelector(ConfirmationPageConst.PAGE_ANCHOR_CSS))

    val pageUrl: String
        get() = driver.currentUrl

    val pageUri: URI
        get() = URI.create(pageUrl)

    fun isConfirmationPageUrl(): Boolean {
        return UrlUtils().isPageUrlPathCorrect(ConfirmationPageConst.PAGE_URL_PATH, pageUri)
    }

    // This method treats only number of adults but can be expanded to check all vacation params
    fun isAdultNumberCorrectOnUrl(numOfAdultGuests: String): Boolean {
        return UrlUtils().isQueryParamCorrect(pageUri, ConfirmationPageConst.ADULTS_QUERY_PARAM, numOfAdultGuests)
    }
}