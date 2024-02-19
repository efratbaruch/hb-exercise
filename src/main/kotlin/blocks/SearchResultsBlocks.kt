package blocks

import org.openqa.selenium.WebElement
import util.PageAndBlockInit
import util.RatedElement
import util.RatingExtractor

class SearchResultsBlocks : PageAndBlockInit() {

    fun clickHighestRateListing() {
        // Thread.sleep is used here to give the page a chance to load all the listings.
        // Unfortunately I did not manage to do so by implicit wait.
        Thread.sleep(2000)
        val listings = searchResultsPage.listingElements
        chooseTheHighestRatedListing(listings).click()
    }

    // Sort the Rated elements list and returns the highest rated element.
    private fun chooseTheHighestRatedListing(listingElements: List<WebElement>): WebElement {
        val ratedElements = getRatedListings(listingElements)
        val sortedRatedElements = ratedElements.sortedBy { it.rating }

        return sortedRatedElements.last().element
    }

    // Extracts the rate from all listing elements and returns a list of the element with its rate.
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