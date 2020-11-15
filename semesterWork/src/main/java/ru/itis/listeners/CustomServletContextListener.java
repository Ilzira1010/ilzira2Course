package ru.itis.listeners;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.repositories.FilesRepository;
import ru.itis.repositories.FilesRepositoryImpl;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryJdbcImpl;
import ru.itis.services.files.FilesService;
import ru.itis.services.files.FilesServiceImpl;
import ru.itis.services.signIn.SignInService;
import ru.itis.services.signIn.SignInServiceImpl;
import ru.itis.services.signUp.SignUpService;
import ru.itis.services.signUp.SignUpServiceImpl;
import ru.itis.services.users.UsersService;
import ru.itis.services.users.UsersServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CustomServletContextListener implements ServletContextListener {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/semester_work";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "ilzira1010";
    private static final String DB_DRIVER = "org.postgresql.Driver";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        FilesRepository filesRepository = new FilesRepositoryImpl(dataSource);
        SignUpService signUpService = new SignUpServiceImpl(usersRepository);
        SignInService signInService = new SignInServiceImpl(usersRepository);
        FilesService filesService = new FilesServiceImpl(filesRepository);
        UsersService usersService = new UsersServiceImpl(usersRepository);
        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("filesUploadService", filesService);
        servletContext.setAttribute("usersService", usersService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

