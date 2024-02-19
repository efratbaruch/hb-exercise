package util

import util.Driver.driver
import java.net.URI

class UrlUtils {

    fun openPage(url: String) {
        driver.get(url)
    }

    fun isPageUrlPathCorrect(expectedPath: String, url: URI): Boolean {
        val urlPath = url.path
        return urlPath.contains(expectedPath)
    }

    fun isQueryParamCorrect(url: URI, queryParam: String, queryValue: String): Boolean {
        val queryParams = url.query.split("&")

        for (param in queryParams) {
            val keyValue = param.split("=")
            if (keyValue[0] == queryParam) {
                return (keyValue[1] == queryValue)
            }
        }
        return false
    }
}