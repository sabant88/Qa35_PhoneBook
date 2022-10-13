package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class HelperContact extends HelperBase {
    public HelperContact(WebDriver wd) {
        super(wd);
    }



    public void openAddContactForm() {
        click(By.xpath("//a[.='ADD']"));
    }


    public void fillContactForm(Contact contact) {
        type(By.cssSelector("input[placeholder='Name']"), contact.getName());
        type(By.cssSelector("input[placeholder='Last Name']"), contact.getLastName());
        type(By.cssSelector("input[placeholder='email']"), contact.getEmail());
        type(By.cssSelector("input[placeholder='Phone']"), contact.getPhone());
        type(By.cssSelector("input[placeholder='Address']"), contact.getAddress());
        type(By.cssSelector("input[placeholder='description']"), contact.getDescription());
    }

    public void saveButton() {
        click(By.xpath("//b"));
    }

    public void saveButtonWithTab() {
        WebElement element = wd.findElement(By.cssSelector("input[placeholder='description']"));
        element.sendKeys(Keys.TAB);
        click(By.xpath("//b"));
    }

    public void returnHomePage() {
        click(By.xpath("//a[.='HOME']"));
        pause(2000);
    }

    public boolean isContactAddedByName(String name) {
        List<WebElement> list = wd.findElements(By.cssSelector("h2"));
        for (WebElement el : list) {
            if (el.getText().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean isContactAddedByPhone(String phone) {
        List<WebElement> list = wd.findElements(By.cssSelector("h3"));
        for (WebElement el : list) {
            if (el.getText().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAddPageStillDisplayed() {
        return wd.findElements(By.cssSelector("a.active[href='/add']")).size() > 0;
    }

    public void openContactForm() {
        click(By.xpath("//a[.='CONTACTS']"));
    }

    public void deleteContact() {
        click(By.cssSelector(".contact-item_card__2SOIM h3"));
        click(By.xpath("//button[.='Remove']"));
        pause(1000);
    }

    public int isContactDeleted() {
        return wd.findElements(By.xpath("//div[@class='contact-page_leftdiv__yhyke']")).size() - 1;

    }

    public int countOfContacts() {
        return wd.findElements(By.xpath("//div[@class='contact-page_leftdiv__yhyke']")).size();
    }

    public boolean isContactsListIsEmpty() {
        return wd.findElements(By.cssSelector(".contact-item_card__2SOIM h3")).isEmpty();
    }

    public void deleteAllContact() {

        while (wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size() != 0)
            deleteContact();
        pause(1000);
    }


    public void addContact() {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);

        openAddContactForm();
        fillContactForm(Contact.builder()
                .name("Gigi")
                .lastName("Buffon")
                .address("Parma")
                .phone("+39574123" + i)
                .description("Goalkeeper")
                .email("buffon@mail.it" + i)
                .build());
        saveButton();
    }

    public int removeOneContact() {
        int before = countOfContacts();

        if (!isContactsListIsEmpty())
            deleteContact();

        int after = countOfContacts();
        return before - after;
    }


    public void removeAllContacts() {
        List<WebElement> list = wd.findElements(By.cssSelector(".contact-item_card__2SOIM h3"));
        for (int i = 0; i < list.size(); i++) {

//            while (wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size()!=0)
            deleteContact();

        }
    }

    public boolean isNoContactHere() {

        return new WebDriverWait(wd, Duration.ofSeconds(5))
                .until(ExpectedConditions.textToBePresentInElement
                        (wd.findElement( By.cssSelector(".contact-page_message__2qafk h1")),"No Contacts here!"));
    }


    public void providerOfContacts(){
        Random random = new Random();

        if(countOfContacts()<4){
            for (int i = 0; i <3 ; i++) {

                int index = random.nextInt(100)+100;
                Contact contact = Contact.builder()
                        .name("Gigi")
                        .lastName("Buffon")
                        .address("Parma")
                        .phone("+39574123" + index)
                        .description("Goalkeeper")
                        .email("buffon@mail.it" + index)
                        .build();

               openAddContactForm();
               fillContactForm(contact);
               saveButton();
               pause(1000);
               logger.info("Provider added contact --> " + contact.toString());
            }

        }
    }
}

