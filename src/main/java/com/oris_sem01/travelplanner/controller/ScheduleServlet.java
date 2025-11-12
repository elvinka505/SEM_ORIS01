package com.oris_sem01.travelplanner.controller;

import com.oris_sem01.travelplanner.config.DatabaseConfig;
import com.oris_sem01.travelplanner.config.FreemarkerConfig;
import com.oris_sem01.travelplanner.dao.impl.ScheduleDaoImpl;
import com.oris_sem01.travelplanner.service.impl.ScheduleServiceImpl;
import com.oris_sem01.travelplanner.model.Schedule;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/schedules")
public class ScheduleServlet extends HttpServlet {

    private ScheduleServiceImpl scheduleService;
    private Configuration freemarkerConfig;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConfig.getConnection();
            scheduleService = new ScheduleServiceImpl(new ScheduleDaoImpl(connection));
            freemarkerConfig = FreemarkerConfig.getConfig();
        } catch (SQLException e) {
            throw new ServletException("Failed to initialize ScheduleServlet", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("view".equals(action)) {
            viewSchedule(req, resp);
        } else if ("byTour".equals(action)) {
            listSchedulesByTour(req, resp);
        } else {
            listSchedules(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "create":
                addSchedule(req, resp);
                break;
            case "update":
                editSchedule(req, resp);
                break;
            case "delete":
                deleteSchedule(req, resp);
                break;
        }
    }

    private void listSchedules(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var schedules = scheduleService.getAll();

        Map<String, Object> root = new HashMap<>();
        root.put("schedules", schedules);

        resp.setContentType("text/html;charset=UTF-8");
        try {
            Template template = freemarkerConfig.getTemplate("schedules-list.ftl");
            PrintWriter writer = resp.getWriter();
            template.process(root, writer);
        } catch (TemplateException e) {
            throw new IOException(e);
        }
    }

    private void viewSchedule(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long scheduleId = Long.parseLong(req.getParameter("id"));
        var schedule = scheduleService.getById(scheduleId);

        Map<String, Object> root = new HashMap<>();
        root.put("schedule", schedule.orElse(null));

        resp.setContentType("text/html;charset=UTF-8");
        try {
            Template template = freemarkerConfig.getTemplate("schedule-view.ftl");
            PrintWriter writer = resp.getWriter();
            template.process(root, writer);
        } catch (TemplateException e) {
            throw new IOException(e);
        }
    }

    private void listSchedulesByTour(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long tourId = Long.parseLong(req.getParameter("tourId"));
        var schedules = scheduleService.getByTourId(tourId);

        Map<String, Object> root = new HashMap<>();
        root.put("schedules", schedules);
        root.put("tourId", tourId);

        resp.setContentType("text/html;charset=UTF-8");
        try {
            Template template = freemarkerConfig.getTemplate("schedules-by-tour.ftl");
            PrintWriter writer = resp.getWriter();
            template.process(root, writer);
        } catch (TemplateException e) {
            throw new IOException(e);
        }
    }

    private void addSchedule(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Schedule schedule = new Schedule();
        schedule.setTourId(Long.parseLong(req.getParameter("tourId")));
        schedule.setStartDate(LocalDate.parse(req.getParameter("startDate")));
        schedule.setEndDate(LocalDate.parse(req.getParameter("endDate")));
        schedule.setLocation(req.getParameter("location"));
        schedule.setDescription(req.getParameter("description"));
        schedule.setAvailableSeats(Integer.parseInt(req.getParameter("availableSeats")));

        boolean success = scheduleService.save(schedule);
        resp.sendRedirect("/schedules?success=" + (success ? "1" : "0"));
    }

    private void editSchedule(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long scheduleId = Long.parseLong(req.getParameter("id"));
        var existingSchedule = scheduleService.getById(scheduleId);

        if (existingSchedule.isPresent()) {
            Schedule schedule = existingSchedule.get();
            schedule.setLocation(req.getParameter("location"));
            schedule.setDescription(req.getParameter("description"));
            schedule.setAvailableSeats(Integer.parseInt(req.getParameter("availableSeats")));

            boolean success = scheduleService.update(schedule);
            resp.sendRedirect("/schedules?id=" + scheduleId + "&success=" + (success ? "1" : "0"));
        }
    }

    private void deleteSchedule(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long scheduleId = Long.parseLong(req.getParameter("id"));
        boolean success = scheduleService.delete(scheduleId);
        resp.sendRedirect("/schedules?success=" + (success ? "1" : "0"));
    }
}
