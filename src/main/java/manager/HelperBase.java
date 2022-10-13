package manager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HelperBase {

    WebDriver wd;

    Logger logger= LoggerFactory.getLogger(HelperBase.class);

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void type(By locator,  String text){
        if(text!=null){
        WebElement el = wd.findElement(locator);
        el.click();
        el.clear();
        el.sendKeys(text);
    }}

    public void click(By locator){
        wd.findElement(locator).click();
    }

    public boolean isElementPresent(By locator){
        return wd.findElements(locator).size()>0;
    }

    public boolean isContactPresent(By locator) {

//        List<WebElement> list = wd.findElements(locator);
//        return  list.size()>0;

          return wd.findElements(locator).size()>0;
    }
    }

