package com.oris_sem01.travelplanner.controller;

import com.oris_sem01.travelplanner.dao.impl.TourDaoImpl;
import com.oris_sem01.travelplanner.model.Tour;
import com.oris_sem01.travelplanner.service.impl.TourServiceImpl;
import com.oris_sem01.travelplanner.config.DatabaseConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet("/tours")
public class TourServlet extends HttpServlet {
    private TourServiceImpl tourService;
    private Configuration fmConfig;

    @Override
    public void init() throws ServletException {
        try {
            Connection conn = DatabaseConfig.getConnection();
            TourDaoImpl tourDao = new TourDaoImpl(conn);
            tourService = new TourServiceImpl(tourDao);

            fmConfig = new Configuration(Configuration.VERSION_2_3_31);
            fmConfig.setServletContextForTemplateLoading(getServletContext(), "templates");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Initialization failed", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            switch (action) {
                case "view":
                    viewTour(request, response, out);
                    break;
                case "add":
                    addTourForm(request, response, out);
                    break;
                case "edit":
                    editTourForm(request, response, out);
                    break;
                case "delete":
                    deleteTour(request, response, out);
                    break;
                default:
                    listTours(request, response, out);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h1>Ошибка: " + e.getMessage() + "</h1>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            switch (action) {
                case "add":
                    saveTour(request, response, out);
                    break;
                case "edit":
                    updateTour(request, response, out);
                    break;
                default:
                    listTours(request, response, out);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h1>Ошибка: " + e.getMessage() + "</h1>");
        }
    }

    private void listTours(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws Exception {
        List<Tour> tours = tourService.getAll();

        Map<String, Object> model = new HashMap<>();
        model.put("tours", tours);
        model.put("title", "Туры - Travel Planner");

        Template template = fmConfig.getTemplate("tours/list.ftl");
        template.process(model, out);
    }

    private void viewTour(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws Exception {
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/tours");
            return;
        }

        Long id = Long.parseLong(idStr);
        Optional<Tour> tour = tourService.getById(id);

        if (tour.isPresent()) {
            Map<String, Object> model = new HashMap<>();
            model.put("tour", tour.get());
            model.put("title", tour.get().getName() + " - Travel Planner");

            Template template = fmConfig.getTemplate("tours/view.ftl");
            template.process(model, out);
        } else {
            response.sendRedirect(request.getContextPath() + "/tours");
        }
    }

    private void addTourForm(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws Exception {
        Map<String, Object> model = new HashMap<>();
        model.put("title", "Добавить тур - Travel Planner");

        Template template = fmConfig.getTemplate("tours/add.ftl");
        template.process(model, out);
    }

    private void saveTour(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws Exception {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String priceStr = request.getParameter("price");

        if (name == null || name.isEmpty() || priceStr == null || priceStr.isEmpty()) {
            Map<String, Object> model = new HashMap<>();
            model.put("error", "Все поля обязательны!");
            model.put("title", "Добавить тур - Travel Planner");

            Template template = fmConfig.getTemplate("tours/add.ftl");
            template.process(model, out);
            return;
        }

        Tour tour = new Tour();
        tour.setName(name);
        tour.setDescription(description);
        tour.setPrice(new BigDecimal(priceStr));

        tourService.save(tour);
        response.sendRedirect(request.getContextPath() + "/tours");
    }

    private void editTourForm(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws Exception {
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/tours");
            return;
        }

        Long id = Long.parseLong(idStr);
        Optional<Tour> tour = tourService.getById(id);

        if (tour.isPresent()) {
            Map<String, Object> model = new HashMap<>();
            model.put("tour", tour.get());
            model.put("title", "Редактировать тур - Travel Planner");

            Template template = fmConfig.getTemplate("tours/edit.ftl");
            template.process(model, out);
        } else {
            response.sendRedirect(request.getContextPath() + "/tours");
        }
    }

    private void updateTour(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws Exception {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String priceStr = request.getParameter("price");

        Tour tour = new Tour();
        tour.setId(Long.parseLong(idStr));
        tour.setName(name);
        tour.setDescription(description);
        tour.setPrice(new BigDecimal(priceStr));

        tourService.update(tour);
        response.sendRedirect(request.getContextPath() + "/tours?action=view&id=" + idStr);
    }

    private void deleteTour(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws Exception {
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            tourService.delete(Long.parseLong(idStr));
        }
        response.sendRedirect(request.getContextPath() + "/tours");
    }
}
