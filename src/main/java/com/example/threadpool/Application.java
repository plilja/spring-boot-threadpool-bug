package com.example.threadpool;

import io.micrometer.core.instrument.MeterRegistry;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@EnableScheduling
@SpringBootApplication
public class Application {
    private final MeterRegistry meterRegistry;

    public Application(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    QueuedThreadPool threadPool() {
        QueuedThreadPool threadPool = new QueuedThreadPool();
        threadPool.setMinThreads(10);
        threadPool.setMaxThreads(50);
        meterRegistry.gauge("queuedThreadPoolSize", List.of(), threadPool, QueuedThreadPool::getThreads);
        return threadPool;
    }

    @Bean
    JettyServletWebServerFactory jettyServletWebServerFactory(QueuedThreadPool queuedThreadPool) {
        JettyServletWebServerFactory jettyServletWebServerFactory = new JettyServletWebServerFactory();
        jettyServletWebServerFactory.setThreadPool(queuedThreadPool);
        return jettyServletWebServerFactory;
    }

}
