package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import java.util.Map;

import static exercise.App.getUsers;
import exercise.Users;

public class SessionServlet extends HttpServlet {

    private Users users = getUsers();

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        if (request.getRequestURI().equals("/login")) {
            showLoginPage(request, response);
            return;
        }

        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        switch (request.getRequestURI()) {
            case "/login":
                login(request, response);
                break;
            case "/logout":
                logout(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showLoginPage(HttpServletRequest request,
                               HttpServletResponse response)
                 throws IOException, ServletException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(request, response);
    }

    private void login(HttpServletRequest request,
                               HttpServletResponse response)
                 throws IOException, ServletException {

        // BEGIN
        // Получение сессии
        HttpSession session = request.getSession();

        // Получаем пользователя по логину
        Map<String, String> user = users.findByEmail(request.getParameter("email"));
        if (user != null && request.getParameter("password").equals("password")) {
            // Вход в систему сводится к записи данных пользователя в сессию
            session.setAttribute("userId", user.get("id"));
            // Механизм работы флеш-сообщений тоже основан на сессии
            // Устанавливаем в сессию атрибут с текстом сообщения
            // Далее мы сможем получить эти данные в шаблонах
            session.setAttribute("flash", "Вы успешно вошли");
            // Выполняем редирект на главную страницу
            response.sendRedirect("/");
            return;
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
        // Передаём туда введенные данные пользователя
        request.setAttribute("user", user);
        // И сообщение об ошибке
        request.setAttribute("flash", "Неверные логин или пароль");
        response.setStatus(422);
        requestDispatcher.forward(request, response);


        // END
    }

    private void logout(HttpServletRequest request,
                               HttpServletResponse response)
                 throws IOException {

        // BEGIN
        // Для выхода пользователя из системы нужно удалить его данные из сессии
        HttpSession session = request.getSession();
        // Удаляем атрибут из сессии
        session.removeAttribute("userId");
        session.setAttribute("flash", "Вы успешно вышли");
        response.sendRedirect("/");
        // END
    }
}
