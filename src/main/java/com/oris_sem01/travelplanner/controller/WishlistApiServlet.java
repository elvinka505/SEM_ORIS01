package com.oris_sem01.travelplanner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oris_sem01.travelplanner.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;

@WebServlet(name="WishlistApiServlet", urlPatterns = {"/api/wishlist/toggle"})
public class WishlistApiServlet extends HttpServlet {

    // ВАЖНО: подставь реальный пул/получение соединения из ConnectionPool/Db utils
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("user") == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("{\"error\":\"unauthorized\"}");
            return;
        }
        User u = (User) session.getAttribute("user");
        long userId = u.getId();

        // read body
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = req.getReader()) {
            String s;
            while ((s = br.readLine()) != null) sb.append(s);
        }

        ObjectMapper mapper = new ObjectMapper();
        long tourId;
        try {
            tourId = mapper.readTree(sb.toString()).get("tourId").asLong();
        } catch (Exception ex) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"bad_request\"}");
            return;
        }

        // toggle logic using JDBC
        try (Connection conn = com.oris_sem01.travelplanner.util.ConnectionPool.getConnection()) {
            String checkSql = "SELECT 1 FROM user_tour_wishlist WHERE user_id = ? AND tour_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(checkSql)) {
                ps.setLong(1, userId);
                ps.setLong(2, tourId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // exists -> delete
                        String del = "DELETE FROM user_tour_wishlist WHERE user_id = ? AND tour_id = ?";
                        try (PreparedStatement d = conn.prepareStatement(del)) {
                            d.setLong(1, userId);
                            d.setLong(2, tourId);
                            d.executeUpdate();
                        }
                        resp.getWriter().write("{\"inWishlist\":false, \"message\":\"Removed\"}");
                        return;
                    }
                }
            }
            // not exists -> insert
            String insert = "INSERT INTO user_tour_wishlist(user_id,tour_id,added_at) VALUES (?,?,CURRENT_TIMESTAMP)";
            try (PreparedStatement ins = conn.prepareStatement(insert)) {
                ins.setLong(1, userId);
                ins.setLong(2, tourId);
                ins.executeUpdate();
            }
            resp.getWriter().write("{\"inWishlist\":true, \"message\":\"Added\"}");
        } catch (SQLException ex) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\":\"server_error\"}");
            ex.printStackTrace();
        }
    }
}
