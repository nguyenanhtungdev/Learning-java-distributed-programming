
import iuh.fit.com.exercise_2.model.Group;
import iuh.fit.com.exercise_2.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;

import java.util.HashSet;
import java.util.List;

public class main {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence
                .createEntityManagerFactory("mariadb")
                .createEntityManager();
        EntityTransaction tr = entityManager.getTransaction();
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            Group group = new Group();
            User user = new User();
            String groupName = faker.company().name();
            String userName = faker.internet().username();
            String emailAddress= faker.internet().emailAddress();
            String password = faker.internet().password();
            group.setName(groupName);
            user.setUsername(userName);
            user.setEmail(emailAddress);
            user.setPassword(password);

            user.setGroups(new HashSet<>(List.of(group)));
            tr.begin();
            entityManager.persist(user);
            entityManager.persist(group);
            tr.commit();
        }
    }
}
