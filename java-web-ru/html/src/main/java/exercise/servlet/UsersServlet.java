package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import java.nio.file.Path;
import java.nio.file.Files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        ObjectMapper objectMapper = new ObjectMapper();
        String content = Files.readString(Path.of("src/main/resources/users.json"));
        return objectMapper.readValue(content, new TypeReference<List<Map<String, String>>>() {
        });
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        List<Map<String, String>> users = getUsers();
        StringBuilder body = new StringBuilder();

        body.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Example application | Users</title>
                        <link rel=\"stylesheet\" href=\"mysite.css\">
                    </head>
                    <body>
                    <table>
                        <tr>
                """);

        for (Map<String, String> user : users) {
            body.append("<td>" + user.get("id") + "</td>\n")
                    .append("<td>\n<a href=\"/users/" + user.get("id") + "\">"
                            + user.get("firstName") + " " + user.get("lastName") + "</a>\n</td>\n");
        }
        body.append("""
                    </tr>
                    </table>
                    </body>
                </html>
                """);

        response.setContentType("text/html;charset=UTF-8");
        // При помощи метода setStatus() можно установить код ответа
        response.setStatus(200);

        PrintWriter out = response.getWriter();
        out.println(body.toString());

        // END
    }



    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        List<Map<String, String>> users = getUsers();
        StringBuilder body = new StringBuilder();

        body.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Example application | Concretny user</title>
                        <link rel=\"stylesheet\" href=\"mysite.css\">
                    </head>
                    <body>
                    <table>
                        <tr>
                """);

        int i = 0;
        for (Map<String, String> user : users) {
            if (user.get("id").equals(id)) {
                body.append("<td>" + user.get("id") + "</td>\n")
                        .append("<td>" + user.get("firstName") + " " + user.get("lastName") + "</td>\n")
                        .append("<td>" + user.get("email") + "</td>\n");
                break;
            }
            i++;
            if (i == users.size()) {
                System.out.println("Not found user");
                response.sendError(SC_NOT_FOUND);
                body.append("Not found");
            }
        }
        body.append("""
                    </tr>
                    </table>
                    </body>
                </html>
                """);

        response.setContentType("text/html;charset=UTF-8");
        // При помощи метода setStatus() можно установить код ответа
        response.setStatus(200);

        PrintWriter out = response.getWriter();
        out.println(body.toString());
        // END
    }
}
