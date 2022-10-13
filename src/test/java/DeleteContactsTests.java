import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContactsTests extends TestBase {

    @BeforeMethod
    public void preCond() {
        if (!app.getHelperUser().isLogged())
            app.getHelperUser().login(new User().setEmail("toldo@mail.uz").setPassword("Ttoldo1$"));
     //   app.getHelperContact().addContact();

        app.getHelperContact().providerOfContacts();
    }

    @Test(priority = 3)
    public void DeleteOneContactSuccessHomeWork() {
        app.getHelperContact().openContactForm();
        app.getHelperContact().deleteContact();

        Assert.assertEquals(app.getHelperContact().countOfContacts() - 1, app.getHelperContact().countOfContacts() - 1);
        Assert.assertFalse(app.getHelperContact().isContactsListIsEmpty());
        Assert.assertEquals(app.getHelperContact().isContactDeleted(), app.getHelperContact().countOfContacts() - 1);

    }

    @Test(priority = 2)
    public void DeleteAllContactsSuccessHomeWork() {

        app.getHelperContact().deleteAllContact();

        Assert.assertEquals(app.getHelperUser().getText(), "No Contacts here!");
        Assert.assertTrue(app.getHelperContact().isContactsListIsEmpty());
    }

    @Test(priority = 1)
    public void DeleteOneContactSuccessClassWork() {
       // app.getHelperContact().removeOneContact();

        Assert.assertEquals(app.getHelperContact().removeOneContact(), 0);
    }

    @Test(priority = 4)
    public void DeleteAllContactsSuccessClassWork() {

        app.getHelperContact().removeAllContacts();

        Assert.assertTrue(app.getHelperContact().isNoContactHere());

    }
}
