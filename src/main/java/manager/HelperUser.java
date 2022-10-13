package manager;

import models.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void logOut() {
        wd.findElement(By.xpath("//button")).click();
    }

    public boolean isLoginBtnPresent() {
        wd.findElement(By.xpath("//*[.='LOGIN']"));
        return true;
    }

    public boolean isLogged() {
        List<WebElement> list = wd.findElements(By.xpath("//button"));

        return list.size() > 0;
    }

    public void openLoginRegistrationForm() {

        //  wd.findElement(By.xpath("//a[.='LOGIN']")).click();
//       wd.findElement(By.cssSelector("a.active")).click();
        //  click(By.cssSelector("a.active"));
        click(By.xpath("//a[@href='/login']"));

    }

    public void fillLoginRegistrationForm(String email, String password) {
        type(By.xpath("//input[@placeholder='Email']"), email); // fill email
        // type(By.cssSelector("input[placeholder$='Email']"), email); // fill email
        type(By.xpath("//input[@placeholder='Password']"), password); //fill password

        //fill email
//        WebElement inputMail = wd.findElement(By.xpath("//input[@placeholder='Email']"));
//        inputMail.click();
//        inputMail.clear();
//        inputMail.sendKeys(email);


        //fill password
//        WebElement inputPassword = wd.findElement(By.xpath("//input[@placeholder='Password']"));
//        inputPassword.click();
//        inputPassword.clear();
//        inputPassword.sendKeys(password);
    }

    public void fillLoginRegistrationForm(User user) {

        type(By.xpath("//input[contains(@placeholder,'Email')]"), user.getEmail()); // fill email
//        type(By.cssSelector("input[placeholder*='Email']"), user.getEmail());
        type(By.xpath("//input[@placeholder='Password']"), user.getPassword()); //fill password

    }

    public void submitLogin() {
        WebElement loginBtn = wd.findElement(By.xpath("//button[normalize-space()='Login']"));
        loginBtn.click();

    }


    public boolean isAlertPresent() {
        Alert alert = wd.switchTo().alert();

        if (alert == null) {
            return false;
        } else
            return true;
    }

    public boolean isErrorWrongFormat() {
        Alert alert = wd.switchTo().alert();
        String errorText = alert.getText();
        System.out.println(errorText);
        alert.accept();
        // click Cancel
//        alert.dismiss();
//        // type text
//        alert.sendKeys("Hello");

        return errorText.contains("Wrong email or password");
    }


    public void submitReg() {
        click(By.xpath("//button[last()]"));
    }

    public String getText() {
        return wd.findElement(By.xpath("//h1[contains(.,'No Contacts here!')]")).getText();
    }

    public void acceptAlert() {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

        wd.switchTo().alert().accept();


    }

    public boolean isNoContactsText() {
//        return wd.findElement(By.xpath("//h1[contains(.,'No Contacts here!')]"))
//                .getText().contains("No Contacts here");
        return new WebDriverWait(wd, Duration.ofSeconds(5))
                .until(ExpectedConditions
                        .textToBePresentInElement(wd.findElement
                                (By.xpath("//h1[contains(.,'No Contacts here!')]")), "No Contacts here!"));
    }

    public boolean isAlertWithErrorPresent(String message) {
        Alert alert = new WebDriverWait(wd, Duration.ofSeconds(5))
                .until(ExpectedConditions.alertIsPresent());

        if (alert != null && alert.getText().contains(message)) {
            alert.accept();
            return true;
        }
        return false;
    }

    public void login(User user) {
        openLoginRegistrationForm();
        fillLoginRegistrationForm(user);
        submitLogin();
    }
}
