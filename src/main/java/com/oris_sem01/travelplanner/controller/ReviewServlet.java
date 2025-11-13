package com.oris_sem01.travelplanner.controller;

import com.oris_sem01.travelplanner.config.DatabaseConfig;
import com.oris_sem01.travelplanner.config.FreemarkerConfig;
import com.oris_sem01.travelplanner.dao.impl.ReviewDaoImpl;
import com.oris_sem01.travelplanner.service.impl.ReviewServiceImpl;
import com.oris_sem01.travelplanner.model.Review;
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

@WebServlet("/reviews")
public class ReviewServlet extends HttpServlet {

    private ReviewServiceImpl reviewService;
    private Configuration freemarkerConfig;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConfig.getConnection();
            reviewService = new ReviewServiceImpl(new ReviewDaoImpl(connection));
            freemarkerConfig = FreemarkerConfig.getConfig();
        } catch (SQLException e) {
            throw new ServletException("Failed to initialize ReviewServlet", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("byTour".equals(action)) {
            listReviewsByTour(req, resp);
        } else {
            listReviews(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("create".equals(action)) {
            addReview(req, resp);
        } else if ("delete".equals(action)) {
            deleteReview(req, resp);
        }
    }

    private void listReviews(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var reviews = reviewService.getAll();

        Map<String, Object> root = new HashMap<>();
        root.put("reviews", reviews);

        resp.setContentType("text/html;charset=UTF-8");
        try {
            Template template = freemarkerConfig.getTemplate("reviews-list.ftl");
            PrintWriter writer = resp.getWriter();
            template.process(root, writer);
        } catch (TemplateException e) {
            throw new IOException(e);
        }
    }

    private void listReviewsByTour(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long tourId = Long.parseLong(req.getParameter("tourId"));
        var reviews = reviewService.getByTourId(tourId);

        Map<String, Object> root = new HashMap<>();
        root.put("reviews", reviews);
        root.put("tourId", tourId);

        resp.setContentType("text/html;charset=UTF-8");
        try {
            Template template = freemarkerConfig.getTemplate("reviews-by-tour.ftl");
            PrintWriter writer = resp.getWriter();
            template.process(root, writer);
        } catch (TemplateException e) {
            throw new IOException(e);
        }
    }

    private void addReview(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Review review = new Review();
        review.setTourId(Long.parseLong(req.getParameter("tourId")));
        review.setUserId(Long.parseLong(req.getParameter("userId")));
        review.setText(req.getParameter("text"));
        review.setRating(Integer.parseInt(req.getParameter("rating")));
        review.setCreatedDate(LocalDate.now());

        boolean success = reviewService.save(review);
        resp.sendRedirect("/reviews?action=byTour&tourId=" + review.getTourId() + "&success=" + (success ? "1" : "0"));
    }

    private void deleteReview(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long reviewId = Long.parseLong(req.getParameter("id"));
        boolean success = reviewService.delete(reviewId);
        resp.sendRedirect("/reviews?success=" + (success ? "1" : "0"));
    }
}
