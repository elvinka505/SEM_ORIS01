package com.oris_sem01.travelplanner.config;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import java.io.File;

public class FreemarkerConfig {
    private static Configuration cfg;

    public static Configuration getConfig() {
        if (cfg == null) {
            try {
                cfg = new Configuration(Configuration.VERSION_2_3_31);

                // ✅ ПРАВИЛЬНО: используем физический путь к файлам
                String templatePath = System.getProperty("user.dir") + "/src/main/resources/templates";
                System.out.println("📂 Freemarker шаблоны ищу в: " + templatePath);

                File templateDir = new File(templatePath);
                if (!templateDir.exists()) {
                    System.err.println("❌ Папка шаблонов не найдена: " + templatePath);
                    // Альтернатива - классфейл
                    cfg.setClassLoaderForTemplateLoading(
                            FreemarkerConfig.class.getClassLoader(),
                            "templates"
                    );
                } else {
                    cfg.setDirectoryForTemplateLoading(templateDir);
                    System.out.println("✅ Папка найдена!");
                }

                cfg.setDefaultEncoding("UTF-8");
                cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            } catch (Exception e) {
                System.err.println("❌ Ошибка при инициализации Freemarker:");
                e.printStackTrace();
            }
        }
        return cfg;
    }
}
