package util

class RatingExtractor {

    fun extractRatingFromListingInfo(listingInfo: String) : Float {
        val ratingStr = findRatingText(listingInfo)
        return convertRatingToFloat(ratingStr)
    }

    private fun findRatingText (text : String) : String{
        val pattern = Regex("""\b\d+\.\d+\b""")
        val matchResult = pattern.find(text)
        val rating = matchResult?.value ?: "0"
        return rating
    }

    private fun convertRatingToFloat(strRating: String): Float {
        return try {
            strRating.toFloat()
        } catch (e: NumberFormatException) {
            return 0f
        }
    }
}