package util

import blocks.ListingDetailsBlocks
import blocks.ListingSortingBlocks
import blocks.VacationSearchBlocks
import pages.*

open class PageAndBlockInit {

    //Pages
    val airBnbLandingPage by lazy { AirBnbLandingPage() }
    val searchResultsPage by lazy { SearchResultsPage() }
    val translationPopUp by lazy { TranslationPopUp() }
    val listingPage by lazy { ListingPage() }
    val confirmationPage by lazy { ConfirmationPage() }

    //Blocks
    val vacationSearchBlocks by lazy { VacationSearchBlocks() }
    val listingSortingBlocks by lazy { ListingSortingBlocks() }
    val listingDetailsBlocks by lazy { ListingDetailsBlocks() }
}