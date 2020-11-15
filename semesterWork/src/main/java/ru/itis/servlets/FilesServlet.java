package ru.itis.servlets;

import ru.itis.models.FileInfo;
import ru.itis.services.files.FilesService;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/uploaded/files")
public class FilesServlet extends HttpServlet {

    private FilesService filesService;

    @Override
    public void init(ServletConfig config) {
        this.filesService = (FilesService) config.getServletContext().getAttribute("filesUploadService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        String fileId = req.getParameter("id");
        FileInfo fileInfo = filesService.getFileInfo(Long.parseLong(fileId));
        response.setContentType(fileInfo.getType());
        response.setContentLength(fileInfo.getSize().intValue());
        response.setHeader("Content-Disposition", "filename=\"" + fileInfo.getOriginalFileName() + "\"");
        filesService.writeFileFromStorage(Long.parseLong(fileId), response.getOutputStream());
        response.flushBuffer();
    }

}
