package ru.itis.servlets;

import ru.itis.dto.SignUpForm;
import ru.itis.services.signUp.SignUpService;

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
    private String error;

    @Override
    public void init(ServletConfig config) throws ServletException {
        signUpService = (SignUpService) config.getServletContext().getAttribute("signUpService");
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
        form.setFirstName(req.getParameter("firstname"));
        form.setLastName(req.getParameter("lastname"));
        form.setEmail(req.getParameter("email"));
        form.setPassword(req.getParameter("password"));

        String passwordAgain = req.getParameter("password-repeat");


        if (!passwordAgain.equals(form.getPassword())) {
            error = "Passwords don't match";
            resp.sendRedirect("/signUp");
        } else if (signUpService.signUp(form)) {
            HttpSession session = req.getSession();
            session.setAttribute("authenticated", true);
            resp.sendRedirect("/");
        } else {
            error = "Registration error";
            resp.sendRedirect("/signUp");
        }
    }
}
