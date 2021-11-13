package com.chern.libraryapp.servlet;

import com.chern.libraryapp.model.Book;
import com.chern.libraryapp.service.BookService;
import com.chern.libraryapp.service.impl.BookServiceImpl;
import com.chern.libraryapp.service.util.DueMailer;
import com.chern.libraryapp.service.util.ReturnMailer;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@WebServlet("/books")
public class BooksServlet extends HttpServlet {

    private final BookService bookService = new BookServiceImpl();


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String key = "org.quartz.impl.StdSchedulerFactory.KEY";
        ServletContext servletContext = config.getServletContext();
        StdSchedulerFactory factory = (StdSchedulerFactory) servletContext.getAttribute(key);
        try {
            System.out.println("work");
            Scheduler scheduler = factory.getScheduler("MyQuartzScheduler");
            scheduler.start();
            JobDetail dueJob = JobBuilder.newJob(DueMailer.class)
                    .build();
            JobDetail returnJob = JobBuilder.newJob(ReturnMailer.class)
                    .build();

            Trigger dueTrigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                    .withIdentity("DueTrigger", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInHours(24)
                            .repeatForever())
                    .build();

            Trigger returnTrigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                    .withIdentity("ReturnTrigger", "group2")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInHours(24)
                            .repeatForever())
                    .build();

            scheduler.scheduleJob(dueJob, dueTrigger);
            scheduler.scheduleJob(returnJob, returnTrigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("bookAmount", bookService.getAllBooks().size());
        List<Book> books = bookService.getBooksAfter(0);
        req.setAttribute("bookList", books);
        getServletContext().getRequestDispatcher("/pages/books.jsp").forward(req, resp);
    }

}
