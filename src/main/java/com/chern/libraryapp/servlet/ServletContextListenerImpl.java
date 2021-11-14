package com.chern.libraryapp.servlet;

import com.chern.libraryapp.service.util.DueMailer;
import com.chern.libraryapp.service.util.ReturnMailer;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    private static final Logger log = Logger.getLogger(ServletContextListenerImpl.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String key = "org.quartz.impl.StdSchedulerFactory.KEY";
        ServletContext servletContext = sce.getServletContext();
        StdSchedulerFactory factory = (StdSchedulerFactory) servletContext.getAttribute(key);
        try {
            System.out.println("work");
            Scheduler scheduler = factory.getScheduler("MyQuartzScheduler");
            scheduler.start();
            JobDetail dueJob = JobBuilder.newJob(DueMailer.class)
                    .build();
            JobDetail returnJob = JobBuilder.newJob(ReturnMailer.class)
                    .build();

            Trigger dueTrigger =  TriggerBuilder.newTrigger()
                    .withIdentity("DueTrigger", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInHours(24)
                            .repeatForever())
                    .build();

            Trigger returnTrigger =  TriggerBuilder.newTrigger()
                    .withIdentity("ReturnTrigger", "group2")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInHours(24)
                            .repeatForever())
                    .build();

            scheduler.scheduleJob(dueJob, dueTrigger);
            scheduler.scheduleJob(returnJob, returnTrigger);
        } catch (SchedulerException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
