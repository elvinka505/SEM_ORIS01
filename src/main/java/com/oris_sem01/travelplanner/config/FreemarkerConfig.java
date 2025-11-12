package com.oris_sem01.travelplanner.config;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerConfig {
    private static Configuration cfg;

    public static Configuration getConfig() {
        if (cfg == null) {
            cfg = new Configuration(Configuration.VERSION_2_3_31);

            // Загрузка шаблонов из папки resources/templates
            cfg.setClassLoaderForTemplateLoading(
                    FreemarkerConfig.class.getClassLoader(), "templates");

            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        }
        return cfg;
    }
}
