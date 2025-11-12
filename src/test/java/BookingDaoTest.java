import com.oris_sem01.travelplanner.config.DatabaseConfig;
import com.oris_sem01.travelplanner.dao.impl.BookingDaoImpl;
import com.oris_sem01.travelplanner.model.Booking;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class BookingDaoTest {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            BookingDaoImpl bookingDao = new BookingDaoImpl(connection);

            Booking booking = new Booking();
            booking.setUserId(1L);
            booking.setTourId(1L);
            booking.setBookingDate(LocalDate.now());
            booking.setStatus("pending");

            boolean result = bookingDao.save(booking);
            System.out.println(result ? "Бронирование добавлено" : "Ошибка");

            bookingDao.findAll().forEach(b -> System.out.println(b.getId() + " " + b.getStatus()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
