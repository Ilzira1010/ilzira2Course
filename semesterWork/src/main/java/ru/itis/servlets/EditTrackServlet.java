package ru.itis.servlets;

import ru.itis.models.Podcast;
import ru.itis.services.podcast.PodcastService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class EditTrackServlet extends HttpServlet {

    private PodcastService podcastService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        podcastService = (PodcastService) config.getServletContext().getAttribute("podcastService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        req.setAttribute("podcast", podcastService.getById(id).get());
        req.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        long id = Long.parseLong(req.getParameter("id"));
        String title = req.getParameter("title");
        title = title == null ? "My Podcast" : title;
        Podcast podcast = Podcast.builder().title(title).build();
        podcastService.update(podcast, id);
        resp.sendRedirect("/profile");
    }
}
