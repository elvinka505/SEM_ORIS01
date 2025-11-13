package com.oris_sem01.travelplanner.controller;

import com.oris_sem01.travelplanner.config.FreemarkerConfig;
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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/")
public class IndexServlet extends HttpServlet {
    private Configuration freemarkerConfig;

    @Override
    public void init() throws ServletException {
        freemarkerConfig = FreemarkerConfig.getConfig();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        resp.setContentType("text/html;charset=UTF-8");
        try {
            Template template = freemarkerConfig.getTemplate("home.ftl");
            PrintWriter writer = resp.getWriter();
            template.process(root, writer);
        } catch (TemplateException e) {
            throw new ServletException(e);
        }
    }
}
