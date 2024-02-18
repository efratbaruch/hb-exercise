package util

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

object Driver {

    val driver : WebDriver by lazy {setUp()}

    val wait = WebDriverWait(driver, Duration.ofSeconds(10))

    private fun setUp() : WebDriver {
        //Todo: send path and driver through an argument
        // Move driver locally
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\efrat\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe")
        val options = ChromeOptions()
        options.addArguments("--remote-allow-origins=*")
        options.addArguments("window-size=1200x600")
//        options.addArguments("--headless") // As needed
        return ChromeDriver(options)
    }
}