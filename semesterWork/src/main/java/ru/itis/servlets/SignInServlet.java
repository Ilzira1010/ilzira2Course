package ru.itis.servlets;

import ru.itis.dto.SignInForm;
import ru.itis.services.signIn.SignInService;
import ru.itis.services.signUp.SignUpService;
import ru.itis.services.users.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private SignInService signInService;
    private UsersService usersService;
    private String error;

    @Override
    public void init(ServletConfig config) throws ServletException {
        signInService = (SignInService) config.getServletContext().getAttribute("signInService");
        usersService = (UsersService) config.getServletContext().getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("error", error);
        req.getRequestDispatcher("WEB-INF/jsp/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignInForm form = new SignInForm();

        form.setEmail(req.getParameter("email"));
        String email = form.getEmail();
        form.setPassword(req.getParameter("password"));

        if (signInService.signIn(form)) {
            HttpSession session = req.getSession();
            session.setAttribute("authenticated", true);
            session.setAttribute("user", usersService.getUserByEmail(email).get());
            resp.sendRedirect("/main");
        } else {
            error = "Authorization error";
            resp.sendRedirect("/signIn");
        }
    }
}