package ru.itis.servlets;

import ru.itis.dto.UserDto;
import ru.itis.models.Podcast;
import ru.itis.services.podcast.PodcastService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private PodcastService podcastService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        podcastService = (PodcastService) config.getServletContext().getAttribute("podcastService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = (UserDto) req.getSession().getAttribute("user");
        List<Podcast> podcasts = podcastService.getUserPodcasts(userDto);
        req.setAttribute("podcasts", podcasts);
        req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
