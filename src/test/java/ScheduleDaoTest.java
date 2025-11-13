import com.oris_sem01.travelplanner.config.DatabaseConfig;
import com.oris_sem01.travelplanner.dao.impl.ScheduleDaoImpl;
import com.oris_sem01.travelplanner.model.Schedule;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class ScheduleDaoTest {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            ScheduleDaoImpl scheduleDao = new ScheduleDaoImpl(connection);

            Schedule schedule = new Schedule();
            schedule.setTourId(1L);
            schedule.setStartDate(LocalDate.now());
            schedule.setEndDate(LocalDate.now().plusDays(5));
            schedule.setLocation("Москва");
            schedule.setDescription("Экскурсионный тур");
            schedule.setAvailableSeats(50);

            boolean result = scheduleDao.save(schedule);
            System.out.println(result ? "Расписание добавлено" : "Ошибка");

            scheduleDao.findAll().forEach(s -> System.out.println(
                    s.getId() + " " + s.getStartDate() + " " + s.getLocation()
            ));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
