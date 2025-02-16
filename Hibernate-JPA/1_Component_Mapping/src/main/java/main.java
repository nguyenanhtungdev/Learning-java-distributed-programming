import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import model.Address;
import model.Person;


public class main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("mariadb");

        Faker faker = new Faker();

        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();

            IntStream.range(0, 10).forEach(x -> {
                Address address = new Address();
                address.setCity(faker.address().city());
                address.setZipcode(faker.address().zipCode());
                address.setStreet(faker.address().streetAddress());

                Person person = new Person();
                person.setName(faker.name().fullName());
                person.setAddress(address);

                em.persist(person);
            });

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}