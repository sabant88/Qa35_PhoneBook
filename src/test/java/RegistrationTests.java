import models.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void preCond() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logOut();

        }

    }

    @AfterMethod
    public void post(){
        app.getHelperContact().returnHomePage();
        app.getHelperContact().pause(2000);

    }


    @Test
    public  void RegistrationSuccess(){
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);

        User user = new User().setEmail("tolldo" + i+ "@mail.uz").setPassword("Ttoldo1$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitReg();

        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertEquals(app.getHelperUser().getText(), "No Contacts here!");
        Assert.assertTrue(app.getHelperUser().isElementPresent(By.cssSelector("button")));
        Assert.assertTrue(app.getHelperUser().isNoContactsText());
    }

    @Test
    public  void RegistrationNegativeWrongEmailWithoutDog(){

        User user = new User().setEmail("toldomail.uz").setPassword("Ttoldo1$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitReg();

        Assert.assertFalse(app.getHelperUser().isLogged());
     //   Assert.assertTrue(app.getHelperUser().isAlertPresent());
        Assert.assertTrue(app.getHelperUser().isAlertWithErrorPresent("Wrong email or password format"));


    }

    @Test
    public  void RegistrationNegativeWrongPassword(){

        User user = new User().setEmail("toldo101@mail.uz").setPassword("toldooooo");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitReg();

        Assert.assertFalse(app.getHelperUser().isLogged());
     //   Assert.assertTrue(app.getHelperUser().isAlertPresent());
        Assert.assertTrue(app.getHelperUser().isAlertWithErrorPresent("Wrong email or password format"));



    }

    @Test
    public  void RegistrationNegativeAlreadyExists(){

        User user = new User().setEmail("toldo@mail.uz").setPassword("Ttoldo1$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitReg();

        Assert.assertFalse(app.getHelperUser().isLogged());
     //  Assert.assertTrue(app.getHelperUser().isAlertPresent());
        Assert.assertTrue(app.getHelperUser().isAlertWithErrorPresent("User already exist"));


    }


}
