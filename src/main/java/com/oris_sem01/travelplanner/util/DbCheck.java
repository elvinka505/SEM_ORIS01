package com.oris_sem01.travelplanner.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbCheck {

    public static void main(String[] args) {
        try (Connection conn = JdbcUtils.getConnection()) {
            System.out.println("Подключение успешно!");

            var ps = conn.prepareStatement("SELECT column_name FROM information_schema.columns WHERE table_name = 'users'");
            ResultSet rs = ps.executeQuery();
            System.out.println("Поля в таблице users:");
            while (rs.next()) {
                System.out.println(rs.getString("column_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
