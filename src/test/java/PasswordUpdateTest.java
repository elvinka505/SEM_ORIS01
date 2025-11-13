import com.oris_sem01.travelplanner.util.JdbcUtils;
import com.oris_sem01.travelplanner.util.PasswordUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordUpdateTest {

    public static void testManualUpdate() {
        String testEmail = "safiyaa@yandex.ru";
        String newHashedPassword = PasswordUtils.hashPassword("test123");

        try (Connection conn = JdbcUtils.getConnection()) {
            // Обновляем пароль
            String sqlUpdate = "UPDATE users SET password_hash = ? WHERE email = ?";
            try (PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {
                psUpdate.setString(1, newHashedPassword);
                psUpdate.setString(2, testEmail);
                int rowsUpdated = psUpdate.executeUpdate();
                System.out.println("Обновлено строк: " + rowsUpdated);
            }

            // Проверяем обновление
            String sqlSelect = "SELECT password_hash FROM users WHERE email = ?";
            try (PreparedStatement psSelect = conn.prepareStatement(sqlSelect)) {
                psSelect.setString(1, testEmail);
                ResultSet rs = psSelect.executeQuery();
                if (rs.next()) {
                    String storedHash = rs.getString("password_hash");
                    System.out.println("Новый пароль в базе: " + storedHash);
                } else {
                    System.out.println("Пользователь не найден");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testManualUpdate();
    }
}
