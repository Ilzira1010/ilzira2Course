package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.models.User;
import ru.itis.services.users.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UsersAjaxServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private UsersService usersService;

    @Override
    public void init(ServletConfig config) {
        usersService = (UsersService) config.getServletContext().getAttribute("usersService")   ;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/userAjax.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = objectMapper.readValue(req.getReader(), User.class);
        usersService.addUser(user);

        List<User> users = usersService.getAll();
        String usersAsJson = objectMapper.writeValueAsString(users);

        resp.setContentType("application/json");
        resp.getWriter().println(usersAsJson);
    }

}
