package ru.itis.servlets;

import ru.itis.services.podcast.PodcastService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteTrackServlet extends HttpServlet {

    private PodcastService podcastService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        podcastService = (PodcastService) config.getServletContext().getAttribute("podcastService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        podcastService.deleteById(id);
        resp.sendRedirect("/profile");
    }
}
