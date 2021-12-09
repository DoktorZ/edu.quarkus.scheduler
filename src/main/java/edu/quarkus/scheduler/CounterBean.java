package edu.quarkus.scheduler;

import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.scheduler.Scheduled;
import io.quarkus.scheduler.ScheduledExecution;

import org.jboss.logging.Logger;

@ApplicationScoped
public class CounterBean {

    private static final Logger LOG = Logger.getLogger(CounterBean.class);

    private AtomicInteger counter = new AtomicInteger();

    public int get() {
        return counter.get();
    }

    @Scheduled(every = "10s")
    void increment() {
        counter.incrementAndGet();
        LOG.info(counter.get());
    }

    @Scheduled(cron = "0 15 10 * * ?")
    void cronJob(ScheduledExecution execution) {
        counter.incrementAndGet();
        LOG.info(counter.get());
        LOG.info("Scheduled fire time: " + execution.getScheduledFireTime());
    }

    @Scheduled(cron = "{cron.expr}")
    void cronJobWithExpressionInConfig() {
        counter.incrementAndGet();
        LOG.info(counter.get());
        LOG.info("Cron expression configured in application.properties");
    }
}
