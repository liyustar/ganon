package com.lyx.ganon.common.async;

import io.micrometer.core.instrument.FunctionCounter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.Arrays.asList;

/**
 * Monitors the status of {@link ThreadPoolTaskExecutor} pools. Does not record timings on operations executed in the
 * {@link ExecutorService}, as this requires the instance to be wrapped. Timings are provided separately by wrapping
 * the executor service with {@link TimedThreadPoolTaskExecutor}.
 *
 * @author David Held
 */
public class ThreadPoolTaskExecutorMetrics implements MeterBinder {
    /**
     * Returns a new {@link ThreadPoolTaskExecutor} with recorded metrics.
     *
     * @param registry The registry to bind metrics to.
     * @param name     The name prefix of the metrics.
     * @param tags     Tags to apply to all recorded metrics.
     * @return The instrumented executor, proxied.
     */
    public static ThreadPoolTaskExecutor monitor(MeterRegistry registry, String name, Iterable<Tag> tags) {
        return new TimedThreadPoolTaskExecutor(registry, name, tags);
    }

    /**
     * Returns a new {@link ThreadPoolTaskExecutor} with recorded metrics.
     *
     * @param registry The registry to bind metrics to.
     * @param name     The name prefix of the metrics.
     * @param tags     Tags to apply to all recorded metrics.
     * @return The instrumented executor, proxied.
     */
    public static Executor monitor(MeterRegistry registry, String name, Tag... tags) {
        return monitor(registry, name, asList(tags));
    }

    private final ThreadPoolTaskExecutor executor;
    private final String name;
    private final Iterable<Tag> tags;

    public ThreadPoolTaskExecutorMetrics(ThreadPoolTaskExecutor executor, String name, Iterable<Tag> tags) {
        this.name = name;
        this.tags = tags;
        this.executor = executor;
    }

    @Override
    public void bindTo(MeterRegistry registry) {
        if (executor == null) {
            return;
        }
        monitor(registry, executor.getThreadPoolExecutor());
    }

    private void monitor(MeterRegistry registry, ThreadPoolExecutor tp) {
        FunctionCounter.builder(name + ".completed", tp, ThreadPoolExecutor::getCompletedTaskCount)
                .tags(tags)
                .description("The approximate total number of tasks that have completed execution")
                .register(registry);

        Gauge.builder(name + ".active", tp, ThreadPoolExecutor::getActiveCount)
                .tags(tags)
                .description("The approximate number of threads that are actively executing tasks")
                .register(registry);

        Gauge.builder(name + ".queued", tp, tpRef -> tpRef.getQueue().size())
                .tags(tags)
                .description("The approximate number of threads that are queued for execution")
                .register(registry);

        Gauge.builder(name + ".pool", tp, ThreadPoolExecutor::getPoolSize)
                .tags(tags)
                .description("The current number of threads in the pool")
                .register(registry);
    }
}