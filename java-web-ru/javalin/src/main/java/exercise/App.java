package exercise;

// Импортируем зависимости, необходимые для работы приложения
import io.javalin.Javalin;
import io.javalin.plugin.rendering.template.JavalinThymeleaf;

import org.thymeleaf.TemplateEngine;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import exercise.controllers.RootController;
import exercise.controllers.ArticleController;

import static io.javalin.apibuilder.ApiBuilder.*;

public final class App {

    private static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "5000");
        return Integer.valueOf(port);
    }

    // Javalin поддерживает работу с шаблонизатором thymeleaf
    private static TemplateEngine getTemplateEngine() {
        // Создаём инстанс движка шаблонизатора
        TemplateEngine templateEngine = new TemplateEngine();
        // Добавляем к нему диалекты
        templateEngine.addDialect(new LayoutDialect());
        templateEngine.addDialect(new Java8TimeDialect());
        // Настраиваем преобразователь шаблонов, так, чтобы обрабатывались
        // шаблоны в директории /templates/
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        // Добавляем преобразователь шаблонов к движку шаблонизатора
        templateEngine.addTemplateResolver(templateResolver);

        return templateEngine;
    }

    // Метод добавляет маршруты в переданное приложение
    private static void addRoutes(Javalin app) {
        // Для GET-запроса на маршрут / будет выполняться
        // обработчик welcome в контроллере RootController
        app.get("/", RootController.welcome);

        // При помощи методов routes() и path() маршруты можно группировать

        // BEGIN
        app.routes(() -> {
                    path("articles", () -> {
                        // GET /articles
                        get(ArticleController.listArticles);
                        path("{id}", () -> {
                            //GET /articles/{id}
                            get(ArticleController.showArticle);
                            path("edit", () -> {
                                //GET /articles/{id}/edit
                                get(ArticleController.editArticle);
                                //POST /articles/{id}/edit
                                patch(ArticleController.updateArticle);
                            });
                            path("delete", () -> {
                                //GET /articles/{id}/delete
                                get(ArticleController.deleteArticle);
                                //POST /articles/{id}/delete
                                delete(ArticleController.destroyArticle);
                            });
                        });
                        // GET /articles/new
                        get("new", ArticleController.newArticle);
                        // POST /articles
                        post(ArticleController.createArticle);
                    });
                });
        // END
    }

    public static Javalin getApp() {

        // Создаём приложение
        Javalin app = Javalin.create(config -> {
            // Включаем логгирование
            config.enableDevLogging();
            // Подключаем настроенный шаблонизатор к фреймворку
            JavalinThymeleaf.configure(getTemplateEngine());
        });

        // Добавляем маршруты в приложение
        addRoutes(app);

        // Обработчик before запускается перед каждым запросом
        // Устанавливаем атрибут ctx для запросов
        app.before(ctx -> {
            ctx.attribute("ctx", ctx);
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(getPort());
    }
}
