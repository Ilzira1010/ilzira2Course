package ru.itis.servlets;

import ru.itis.dto.UserDto;
import ru.itis.models.Category;
import ru.itis.models.Podcast;
import ru.itis.models.User;
import ru.itis.services.category.CategoryService;
import ru.itis.services.podcast.PodcastService;
import ru.itis.utils.Constants;
import ru.itis.utils.FileSaver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/create")
@MultipartConfig
public class PodcastCreaterServlet extends HttpServlet {

    private CategoryService categoryService;
    private PodcastService podcastService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        categoryService = (CategoryService) config.getServletContext().getAttribute("categoryService");
        podcastService = (PodcastService) config.getServletContext().getAttribute("podcastService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryService.getAll();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/WEB-INF/jsp/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Part img = req.getPart("image");
        Part track = req.getPart("track");
        String title = req.getParameter("title");
        String category = req.getParameter("category");
        String imgName = FileSaver.save(img, Constants.UPLOAD_DIR_IMG);
        String trackName = FileSaver.save(track, Constants.UPLOAD_DIR_TRACK);
        UserDto user = (UserDto) req.getSession().getAttribute("user");
        podcastService.safe(Podcast.builder()
        .img(imgName)
        .title(title)
        .track(trackName)
        .category(categoryService.getById(Integer.parseInt(category)).get())
        .user(User.builder().id(user.getId()).build())
                .build());
        resp.sendRedirect("/profile");
    }
}
