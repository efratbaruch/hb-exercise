package pages

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import util.Driver.driver

object SearchResultsPageLocators {
    const val LISTING_CONTAINER_CSS = "div[data-testid='card-container']"
}

class SearchResultsPage() {

    val listingElements: List<WebElement>
        get() = driver.findElements(By.cssSelector(SearchResultsPageLocators.LISTING_CONTAINER_CSS))
}

