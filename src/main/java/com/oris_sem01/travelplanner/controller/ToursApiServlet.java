package com.oris_sem01.travelplanner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oris_sem01.travelplanner.dao.impl.TourDaoImpl;
import com.oris_sem01.travelplanner.model.Tour;
import com.oris_sem01.travelplanner.util.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/api/tours")
public class ToursApiServlet extends HttpServlet {

    private TourDaoImpl tourDao;
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            tourDao = new TourDaoImpl(conn);
        } catch (Exception e) {
            throw new ServletException("Failed to init ToursApiServlet", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            List<Tour> tours = tourDao.getAll();
            resp.setContentType("application/json;charset=UTF-8");
            mapper.writeValue(resp.getWriter(), tours);
        } catch (Exception e) {
            resp.setStatus(500);
            resp.getWriter().write("{\"error\":\"Server error\"}");
        }
    }
}
