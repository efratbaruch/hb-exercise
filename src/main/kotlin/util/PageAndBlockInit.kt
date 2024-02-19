package util

import blocks.ListingDetailsBlocks
import blocks.SearchResultsBlocks
import blocks.VacationSearchBlocks
import pages.*

open class PageAndBlockInit {

    // Here I instantiate all the pages and blocks as needed - it means that only if a class uses
    // the instance it will be created. Once created the same instance is returned to all.

    //Pages
    val landingPage by lazy { LandingPage() }
    val searchResultsPage by lazy { SearchResultsPage() }
    val translationPopUp by lazy { TranslationPopUp() }
    val listingPage by lazy { ListingPage() }
    val confirmationPage by lazy { ConfirmationPage() }

    //Blocks
    val vacationSearchBlocks by lazy { VacationSearchBlocks() }
    val searchResultsBlocks by lazy { SearchResultsBlocks() }
    val listingDetailsBlocks by lazy { ListingDetailsBlocks() }
}