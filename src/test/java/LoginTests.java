import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        logger.info("starts test if authorizated user");
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logOut();
            logger.info("test was needed in logout status");
        }
    }


    @AfterMethod
    public void post() {
        app.getHelperContact().returnHomePage();
    }


    @Test
    public void loginSuccessModel() {
//        User user =new User();
//        user.setEmail("noa");
//        user.setPassword("Nnoa");

        User user = new User().setEmail("noa@gmail.com").setPassword("Nnoa12345$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());

    }

    @Test
    public void loginSuccess() {


        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("noa@gmail.com", "Nnoa12345$");
        logger.info("user with email--> noa@gmail.com and password --> Nnoa12345$");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert passed ---> 'is logged'");


    }


    @Test
    public void loginNegativeWrongEmailFormat() {

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(new User().setEmail("noagmail.com").setPassword("Nnoa12345$"));
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isAlertPresent());
        Assert.assertTrue(app.getHelperUser().isErrorWrongFormat());


    }

    @Test(enabled = false)
    public void loginNegativeWrongPasswordFormat() {


    }

    @Test(dataProvider = "dataLogin")
    public void loginSuccessDataProvider() {


        User user = new User().setEmail("noa@gmail.com").setPassword("Nnoa12345$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());

    }

    @DataProvider
    public Iterator<Object[]> dataLogin(){

        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"noa@gmail.com", "Nnoa12345$"});
        return list.iterator();
    }
}