import com.oris_sem01.travelplanner.dao.impl.TourDaoImpl;
import com.oris_sem01.travelplanner.model.Tour;
import com.oris_sem01.travelplanner.config.DatabaseConfig;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class TourDaoTest {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            TourDaoImpl tourDao = new TourDaoImpl(connection);

            Tour newTour = new Tour();
            newTour.setName("Тестовый тур");
            newTour.setDescription("Описание тестового тура");
            newTour.setPrice(BigDecimal.valueOf(1000));

            boolean result = tourDao.save(newTour);
            System.out.println(result ? "Тур добавлен" : "Ошибка");

            tourDao.findAll().forEach(t -> System.out.println(t.getName() + " " + t.getPrice()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
