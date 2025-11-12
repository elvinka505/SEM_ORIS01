import com.oris_sem01.travelplanner.dao.impl.UserDaoImpl;
import com.oris_sem01.travelplanner.model.User;
import com.oris_sem01.travelplanner.util.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class DaoTest {

    public static void main(String[] args) {
        // Получаем соединение с базой
        try (Connection connection = JdbcUtils.getConnection()) {
            // Создаем DAO, передавая соединение
            UserDaoImpl userDao = new UserDaoImpl(connection);

            // Тестируем findByEmail (пример)
            Optional<User> userOpt = userDao.findByEmail("angel@heaven.com");

            if (userOpt.isPresent()) {
                User user = userOpt.get();
                System.out.println("Пользователь найден: " + user.getEmail());
                System.out.println("Имя: " + user.getFirstName());
            } else {
                System.out.println("Пользователь не найден");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка подключения к базе");
        }
    }
}
