package com.oris_sem01.travelplanner.controller;

import com.oris_sem01.travelplanner.config.DatabaseConfig;
import com.oris_sem01.travelplanner.config.FreemarkerConfig;
import com.oris_sem01.travelplanner.dao.impl.TourDaoImpl;
import com.oris_sem01.travelplanner.model.Tour;
import com.oris_sem01.travelplanner.service.impl.TourServiceImpl;
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

@WebServlet("/tours/*")
public class TourServlet extends HttpServlet {
    private TourServiceImpl tourService;
    private Configuration freemarkerConfig;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConfig.getConnection();
            tourService = new TourServiceImpl(new TourDaoImpl(connection));
            freemarkerConfig = FreemarkerConfig.getConfig();
        } catch (Exception e) {
            throw new ServletException("Ошибка инициализации", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                listTours(request, response);
            } else {
                viewTour(request, response, pathInfo.substring(1));
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            saveTour(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listTours(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<Tour> tours = tourService.getAll();
        Map<String, Object> data = new HashMap<>();
        data.put("tours", tours);

        Template template = freemarkerConfig.getTemplate("tours/list.ftl");
        PrintWriter out = response.getWriter();
        template.process(data, out);
    }

    private void viewTour(HttpServletRequest request, HttpServletResponse response, String tourId)
            throws Exception {
        Optional<Tour> tour = tourService.getById(Long.parseLong(tourId));
        Map<String, Object> data = new HashMap<>();

        if (tour.isPresent()) {
            data.put("tour", tour.get());
            Template template = freemarkerConfig.getTemplate("tours/view.ftl");
            PrintWriter out = response.getWriter();
            template.process(data, out);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void saveTour(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String destination = request.getParameter("destination");
        String price = request.getParameter("price");

        if (name != null && !name.isEmpty() && price != null && !price.isEmpty()) {
            Tour tour = new Tour();
            tour.setName(name);
            tour.setDescription(description);
            tour.setDestination(destination);
            tour.setPrice(new BigDecimal(price));

            tourService.save(tour);
            response.sendRedirect("/travelplanner/tours");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
