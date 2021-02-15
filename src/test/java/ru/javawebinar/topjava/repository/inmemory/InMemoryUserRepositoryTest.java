package ru.javawebinar.topjava.repository.inmemory;

import junit.framework.TestCase;
import org.junit.Test;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.List;

public class InMemoryUserRepositoryTest extends TestCase {

    UserRepository userRepository = new InMemoryUserRepository();

    public void setUp() {
        userRepository.save(new User(null,"Azat", "azbur@mail.ru","qwerty", 2000, true, null));
        userRepository.save(new User(null,"Elina", "elina@mail.ru","qwerty", 2000, true, null));
        userRepository.save(new User(null,"Elina", "damir@mail.ru","qwerty", 2000, true, null));
        userRepository.save(new User(null,"Roman", "roman@mail.ru","qwerty", 2000, true, null));
        userRepository.save(new User(null,"Igor", "igor@mail.ru","qwerty", 2000, true, null));
    }

    @Test
    public void testGetAll() {
        List<User> userList = userRepository.getAll();
        userList.forEach(System.out::println);
    }

    @Test
    public void testGetByEmail() {
        User user = userRepository.getByEmail("roman@mail.ru");
        System.out.println(user);
    }

    @Test
    public void testDelete() {
        userRepository.delete(1);
    }
}