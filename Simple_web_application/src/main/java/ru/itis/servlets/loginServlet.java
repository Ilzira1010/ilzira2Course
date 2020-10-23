package ru.itis.servlets;

import ru.itis.models.User;
import ru.itis.repositories.CookieRepositoryImpl;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/login")
public class loginServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/java_course_2";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "ilzira1010";

    private UsersRepository usersRepository;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            usersRepository = new UsersRepositoryJdbcImpl(connection);
        } catch (SQLException throwables) {
            throw new IllegalStateException(throwables);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/html/login.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("email");
        String pass = request.getParameter("password");

        Optional<User> currentUser = usersRepository.findByLogin(login, "registration");

        if (currentUser.isPresent()) {
            if (
                    currentUser.get().getEmail().equals(login) &&
                            currentUser.get().getPassword().equals(pass)
            ) {
                CookieRepositoryImpl cookieRepository = new CookieRepositoryImpl(response);
                cookieRepository.addCookie(UUID.randomUUID().toString(), currentUser.get().getId());
                response.sendRedirect("/users");
            } else {
                response.sendRedirect("/login");
            }
        } else {
            response.sendRedirect("/login");
        }


    }
}

