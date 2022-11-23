package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {

    @DataProvider
    public Iterator<Object[]> dataLogin() {

        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"noa@gmail.com", "Nnoa12345$"});
        list.add(new Object[]{"noa@gmail.com", "Nnoa12345$"});
        list.add(new Object[]{"noa@gmail.com", "Nnoa12345$"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> dataModel() {
      List<Object[]>  list1 = new ArrayList<>();
      list1.add(new Object[]{ new User().setEmail("noa@gmail.com").setPassword("Nnoa12345$")});
      list1.add(new Object[]{ new User().setEmail("toldo@mail.uz").setPassword("Ttoldo1$")});
      return list1.iterator();
    }

}