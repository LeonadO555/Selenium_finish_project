package util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Scroll {

    public static void scrollToPageEnd(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView({behavior: 'smooth',block: 'center'});", element);
    }
}


// этот метод поместить на страницу, где есть scroll
//public void scrollToChangePasswordButton() {
//    ScrollUtils.scrollToElement(driver, changePasswordButton); //передаем драйвер и элемент, до которого нужно доскроллить
//    getWait().forVisibility(changePasswordButton)
//}