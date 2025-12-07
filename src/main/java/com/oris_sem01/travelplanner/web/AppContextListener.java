package com.oris_sem01.travelplanner.web;

import com.oris_sem01.travelplanner.config.FreemarkerConfig;
import com.oris_sem01.travelplanner.repository.jdbc.*;
import com.oris_sem01.travelplanner.service.*;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();

        // Инициализируем Freemarker
        FreemarkerConfig.getConfig(ctx);

        // Репозитории
        JdbcUserRepository userRepo = new JdbcUserRepository();
        JdbcTourRepository tourRepo = new JdbcTourRepository();
        JdbcBookingRepository bookingRepo = new JdbcBookingRepository();
        JdbcReviewRepository reviewRepo = new JdbcReviewRepository();
        JdbcScheduleRepository scheduleRepo = new JdbcScheduleRepository();

        // Сервисы
        UserService userService = new UserService(userRepo);
        TourService tourService = new TourService(tourRepo);
        BookingService bookingService = new BookingService(bookingRepo);
        ReviewService reviewService = new ReviewService(reviewRepo);
        ScheduleService scheduleService = new ScheduleService(scheduleRepo);

        // Кладём в контекст
        ctx.setAttribute("userService", userService);
        ctx.setAttribute("tourService", tourService);
        ctx.setAttribute("bookingService", bookingService);
        ctx.setAttribute("reviewService", reviewService);
        ctx.setAttribute("scheduleService", scheduleService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // можно закрыть пул, если хочешь
        // ConnectionPool.closePool();
    }
}
