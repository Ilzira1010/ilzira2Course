package ru.itis.servlets;

import ru.itis.dto.SignInForm;
import ru.itis.dto.SignUpForm;
import ru.itis.services.signIn.SignInService;
import ru.itis.services.signUp.SignUpService;
import ru.itis.services.users.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private SignUpService signUpService;
    private SignInService signInService;
    private UsersService usersService;
    private String error;

    @Override
    public void init(ServletConfig config) throws ServletException {
        signUpService = (SignUpService) config.getServletContext().getAttribute("signUpService");
        signInService = (SignInService) config.getServletContext().getAttribute("signInService");
        usersService = (UsersService) config.getServletContext().getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("error", error);
        req.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        SignUpForm form = new SignUpForm();
        form.setNickname(req.getParameter("nickname"));
        form.setEmail(req.getParameter("email"));
        String email = form.getEmail();
        form.setPassword(req.getParameter("password"));

        SignInForm formIn = new SignInForm();
        formIn.setEmail(req.getParameter("email"));
        formIn.setPassword(req.getParameter("password"));

        String passwordAgain = req.getParameter("password-repeat");


        if (!passwordAgain.equals(form.getPassword())) {
            error = "Passwords don't match";
            resp.sendRedirect("/signUp");
        } else if (signInService.signIn(formIn)) {
            error = "User already exist";
            resp.sendRedirect("/signUp");
        }
        else if (signUpService.signUp(form)) {
            HttpSession session = req.getSession();
            session.setAttribute("authenticated", true);
            session.setAttribute("user", usersService.getUserByEmail(email).get());
            resp.sendRedirect("/main");
        } else {
            error = "Registration error";
            resp.sendRedirect("/signUp");
        }
    }
}
