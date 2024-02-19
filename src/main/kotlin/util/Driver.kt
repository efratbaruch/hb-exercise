package util

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

object DriverConst {
    const val DRIVER_NAME = "webdriver.chrome.driver"
    const val DRIVER_PATH = "C:\\Users\\efrat\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"
}

object Driver {

    val driver: WebDriver by lazy { setUp() }

    val wait = WebDriverWait(driver, Duration.ofSeconds(10))

    private fun setUp(): WebDriver {
        System.setProperty(DriverConst.DRIVER_NAME, DriverConst.DRIVER_PATH)

        val options = ChromeOptions()
        options.addArguments("--remote-allow-origins=*")
        options.addArguments("window-size=1200x600")
//        options.addArguments("--headless") // Use As needed

        return ChromeDriver(options)
    }
}