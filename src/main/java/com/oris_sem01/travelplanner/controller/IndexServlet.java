package com.oris_sem01.travelplanner.controller;

import com.oris_sem01.travelplanner.config.FreemarkerConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("")
public class IndexServlet extends HttpServlet {
    private Configuration freemarkerConfig;

    @Override
    public void init() throws ServletException {
        freemarkerConfig = FreemarkerConfig.getConfig();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            System.out.println("📄 Загружаю главную страницу...");

            Map<String, Object> data = new HashMap<>();

            // Проверяем авторизацию
            Object user = request.getSession().getAttribute("user");
            if (user != null) {
                System.out.println("👤 Пользователь авторизован");
                data.put("user", user);
            }

            // Загружаем шаблон
            Template template = freemarkerConfig.getTemplate("home.ftl");
            PrintWriter out = response.getWriter();
            template.process(data, out);

            System.out.println("✅ Главная страница загружена");
        } catch (Exception e) {
            System.err.println("❌ Ошибка при загрузке главной: " + e.getMessage());
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
