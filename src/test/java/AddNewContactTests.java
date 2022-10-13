import models.Contact;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewContactTests extends TestBase {

    @BeforeMethod
    public void preCond() {
        if (!app.getHelperUser().isLogged())
            app.getHelperUser().login(new User().setEmail("toldo@mail.uz").setPassword("Ttoldo1$"));
    }

    @Test(invocationCount = 5)
    public void AddNewContactSuccessAllFields() {

        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        Contact contact = Contact.builder()
                .name("Gigi")
                .lastName("Buffon")
                .address("Parma")
                .phone("+39574123" + i)
                .description("Goalkeeper")
                .email("buffon@mail.it" + i)
                .build();
        logger.info("test statrs with data --->" + contact.toString());

//        User user = new User().setPassword("Ttoldo1$").setEmail("toldo@mail.uz");
//
//        app.getHelperUser().openLoginRegistrationForm();
//        app.getHelperUser().fillLoginRegistrationForm(user);
//        app.getHelperUser().submitLogin();
//        app.getHelperUser().pause(2000);

        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveButton();

        Assert.assertTrue(app.getHelperUser().isContactPresent(By.xpath("//div[@class='contact-item_card__2SOIM'][1]")));
        Assert.assertTrue(app.getHelperUser().isContactPresent(By.xpath("//a[.='CONTACTS']")));

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));


    }

    @Test
    public void AddNewContactSuccesWithoutOneField() {

        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        Contact contact = Contact.builder()
                .name("Oliver")
                .lastName("Kann")
                .address("Berlin")
                .phone("+49574123" + i)
                .email("kannOl@mail.de")
                .build();

        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveButtonWithTab();

        Assert.assertTrue(app.getHelperUser().isContactPresent(By.xpath("//div[@class='contact-item_card__2SOIM'][1]")));
        Assert.assertTrue(app.getHelperUser().isContactPresent(By.xpath("//a[.='CONTACTS']")));

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));


    }


    @Test
    public void AddNewContactNegativeWrongName() {

        Contact contact = Contact.builder()
                .lastName("Lupen")
                .address("Rio")
                .phone("+1574123")
                .email("jadi@mail.br")
                .build();

        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveButtonWithTab();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertFalse(app.getHelperUser().isContactPresent(By.xpath("//div[@class='contact-item_card__2SOIM'][1]")));


    }


}
