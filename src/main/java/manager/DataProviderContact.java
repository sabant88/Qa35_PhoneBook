package manager;

import models.Contact;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderContact {

    @DataProvider
     public Iterator<Object[]> dataContact() throws IOException {

        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.csv")));
        String line = reader.readLine();

        while(line!=null){
            String[] split = line.split(",");
            list.add(new Object[]{
                    Contact.builder()
                            .name(split[0])
                            .lastName(split[1])
                            .address(split[2])
                            .phone(split[3])
                            .description(split[4])
                            .email(split[5])
                            .build()

            });

            line=reader.readLine();

        }

        return list.iterator();
    }
}
