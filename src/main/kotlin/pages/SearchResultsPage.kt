package pages

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import util.Driver.driver
import util.RatedElement
import util.RatingExtractor

object SearchResultsPageLocators {
    const val LISTING_CONTAINER_CSS = "div[data-testid='card-container']"
}

class SearchResultsPage() {

    val listingElements: List<WebElement>
        get() = driver.findElements(By.cssSelector(SearchResultsPageLocators.LISTING_CONTAINER_CSS))

    // todo: should this be here or in block?
    fun chooseTheHighestRatedListing(listingElements: List<WebElement>) {
        val ratedElements = getRatedListings(listingElements)
        val sortedRatedElements = ratedElements.sortedBy { it.rating }

        sortedRatedElements.last().element.click()
    }

    private fun getRatedListings(elements: List<WebElement>): List<RatedElement> {
        val ratedElements: MutableList<RatedElement> = mutableListOf()

        for (element in elements) {
            val rate = RatingExtractor().extractRatingFromListingInfo(element.text)
            val ratedElement = RatedElement(element, rate)
            ratedElements.add(ratedElement)
        }

        return ratedElements
    }
}

