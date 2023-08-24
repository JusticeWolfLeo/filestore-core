package maxima.ru.filemanager;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Данный класс служит для тестового стартового контейнера, он создаёт всё необходимое окружение для тестов.
 * При создании теста, где необходимо проверить работу репозитория, сервиса или контроллера. Делаем по аналогии
 * с {@link maxima.ru.filemanager.repository.FileRepositoryTests}. extends от данного класса и далее пишем нужный тест
 *
 * Все необхолимые конфигурации пишем в тестовый конфиг application-test.properties
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = FileManagerApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
    locations = "classpath:application-test.properties")
public class BasicTestContainer {
  @Autowired
  protected MockMvc mvc;
}