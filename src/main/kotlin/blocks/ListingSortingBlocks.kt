package blocks

import org.openqa.selenium.support.ui.WebDriverWait
import util.Driver.driver
import util.PageAndBlockInit
import java.time.Duration

class ListingSortingBlocks : PageAndBlockInit() {

    //todo: change class name

    fun clickHighestRateListing(){
        // todo add comment why sleep and not implicit
        Thread.sleep(2000)
        val listings = searchResultsPage.listingElements
        searchResultsPage.chooseTheHighestRatedListing(listings)
    }
}