package org.github.tung;

import net.datafaker.Faker;
import org.github.tung.entity.Address;
import org.github.tung.entity.Person;
import org.github.tung.entity.PhoneNumber;
import org.github.tung.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Random rd = new Random();
        Faker faker = new Faker();
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person p = new Person();
            Address address = new Address();
            List<PhoneNumber> phoneNumbers = new ArrayList<>();

            p.setFirstName(faker.name().firstName());
            p.setLastName(faker.name().lastName());
            p.setAge(faker.number().numberBetween(18, 60));
            address.setStreetAddress(faker.address().streetAddress());
            address.setCity(faker.address().city());
            address.setState(faker.address().state());
            address.setPostalCode(Integer.parseInt(faker.address().postcode()));
            p.setAddress(address);

            int n = rd.nextInt(3);
            if(n > 0){
                for (int j = 0; j < n; j++) {
                    phoneNumbers.add(new PhoneNumber(
                            faker.options().option("home", "fax", "work"),
                            faker.phoneNumber().phoneNumber()
                    ));
                }
                p.setPhoneNumbers(phoneNumbers);
                people.add(p);
            }
        }
        people.forEach(System.out::println);
        JsonUtil.toJsonAndWrite2File(people, "json/people.json");
    }
}