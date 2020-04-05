package com.lyx.ganon.common.async;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * A {@link ThreadPoolTaskExecutor} which is timed
 *
 * @author David Held
 */
public class TimedThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
    private final MeterRegistry registry;
    private final String name;
    private final Iterable<Tag> tags;
    private final Timer timer;

    public TimedThreadPoolTaskExecutor(MeterRegistry registry, String name, Iterable<Tag> tags) {
        this.registry = registry;
        this.name = name;
        this.tags = tags;
        this.timer = registry.timer(name, tags);
    }

    @Override
    public void initialize() {
        super.initialize();
        new ThreadPoolTaskExecutorMetrics(this, name, tags).bindTo(registry);
    }

    @Override
    public void execute(Runnable task) {
        super.execute(timer.wrap(task));
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        super.execute(timer.wrap(task), startTimeout);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return super.submit(timer.wrap(task));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(timer.wrap(task));
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        return super.submitListenable(timer.wrap(task));
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        return super.submitListenable(timer.wrap(task));
    }
}
