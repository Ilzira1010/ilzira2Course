package ru.itis.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.models.Category;
import ru.itis.models.Podcast;
import ru.itis.services.podcast.PodcastService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter("/searchAjax")
public class SearchFilter implements Filter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getMethod().equals("POST")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String input = servletRequest.getParameter("input");
        String category = servletRequest.getParameter("category");

        PodcastService podcastService = (PodcastService) req.getServletContext().getAttribute("podcastService");
        List<Podcast> podcasts = null;

        if (input != null) {
            if (category != null && !category.equals("0")) {
                podcasts = podcastService.findByCategory(input, Category.builder().id(Long.parseLong(category)).build());
            } else {
                podcasts = podcastService.find(input);
            }
        }

        String podcastAsString = objectMapper.writeValueAsString(podcasts);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println(podcastAsString);
    }

    @Override
    public void destroy() {

    }
}