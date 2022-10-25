package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.security.auth.message.config.ClientAuthConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        PrintWriter out = response.getWriter();
        String parameter = request.getParameter("search");
        if (parameter == null || parameter.isEmpty()) {
            out.println(getCompanies().stream()
                    .map(a -> a + "\n")
                    .collect(Collectors.joining()));
        } else {
            String filter = getCompanies().stream()
                    .filter(c -> c.contains(parameter))
                    .map(a -> a + "\n")
                    .collect(Collectors.joining());
            if (filter.length() == 0) {
                out.println("Companies not found");
                return;
            }
            out.println(filter);
        }
        // END
    }
}
