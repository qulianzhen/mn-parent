package com.mn.mnutil;

import org.springframework.lang.NonNull;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 线程池工具类
 * @Author:Mloong
 * @Date :create in 2020/9/18-1:48
 * @Version 1.0
 * @Modified By:
 */
public class ThreadPoolUtil {
    private static volatile ThreadPoolUtil mInstance;


    /*
    * 如果池中任务数 < corePoolSize -> 放入立即执行
    * 如果池中任务数 > corePoolSize -> 放入队列等待
    * 队列满 -> 新建线程立即执行
    * 执行中的线程 > maxPoolSize -> 触发handler（RejectedExecutionHandler）异常
    *
    *
    * */
    //核心线程池的数量，同时能够执行的线程数量
    private int corePoolSize;
    //最大线程池数量，表示当缓冲队列满的时候能继续创建的新线程的最大数量
    private int maxPoolSize;
    //存活时间
    /*当线程空闲时间达到keepAliveTime，该线程会退出:
    * 1、线程为什么会空闲 2、线程为什么要退出
    * 核心线程数10，最大线程数30，keepAliveTime是3秒
    * 随着任务数量不断上升，线程池会不断的创建线程，直到到达核心线程数10，就不创建线程了，
    * 这时多余的任务通过加入阻塞队列来运行，
    * 当超出阻塞队列长度+核心线程数时，
    * 这时不得不扩大线程个数来满足当前任务的运行，这时就需要创建新的线程了（最大线程数起作用），上限是最大线程数30
    * 那么超出核心线程数10并小于最大线程数30的可能新创建的这20个线程相当于是“借”的，如果这20个线程空闲时间超过keepAliveTime，就会被退出
    * (设置为0就会立即退出，但是如果任务数频繁超出核心线程数，立即 退出反而不是好事，需要评估)
    * */
    private long keepAliveTime = 5;
    private TimeUnit unit = TimeUnit.MINUTES;
    private ThreadPoolExecutor executor;


    private ThreadPoolUtil() {
        //给corePoolSize赋值：当前设备可用处理器核心数*2 + 1,能够让cpu的效率得到最大程度执行（有研究论证的）
        corePoolSize = Runtime.getRuntime().availableProcessors() * 2 + 1;
        maxPoolSize = corePoolSize;
        executor = new ThreadPoolExecutor(
                //当某个核心任务执行完毕，会依次从缓冲队列中取出等待任务
                corePoolSize,
                // 然后new LinkedBlockingQueue<Runnable>(),然后maximumPoolSize,但是它的数量是包含了corePoolSize的
                maxPoolSize,
                //表示的是maximumPoolSize当中等待任务的存活时间
                keepAliveTime,
                unit,
                //缓冲队列，用于存放等待任务，Linked的先进先出
                new LinkedBlockingQueue<Runnable>(),
                new DefaultThreadFactory(Thread.NORM_PRIORITY, "MN-WORKER-thread-pool-"),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    public static ThreadPoolUtil getInstance() {
        if (mInstance == null) {
            synchronized (ThreadPoolUtil.class) {
                if (mInstance == null) {
                    mInstance = new ThreadPoolUtil();
                }
            }
        }
        return mInstance;
    }


    /**
     * 执行任务
     *
     * @param runnable
     */
    public void execute(Runnable runnable) {
        if (executor == null) {
            executor = new ThreadPoolExecutor(
                    corePoolSize,
                    maxPoolSize,
                    keepAliveTime,
                    unit,
                    new LinkedBlockingQueue<Runnable>(),
                    new DefaultThreadFactory(Thread.NORM_PRIORITY, "MN-WORKER-thread-pool-"),
                    new ThreadPoolExecutor.AbortPolicy());
        }
        if (runnable != null) {
            executor.execute(runnable);
        }
    }


    /**
     * 移除任务
     *
     * @param runnable
     */
    public void remove(Runnable runnable) {
        if (runnable != null) {
            executor.remove(runnable);
        }
    }


    private static class DefaultThreadFactory implements ThreadFactory {

        //线程池的计数
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        //线程的计数
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final String namePrefix;
        private final int threadPriority;

        DefaultThreadFactory(int threadPriority, String threadNamePrefix) {
            this.threadPriority = threadPriority;
            this.group = Thread.currentThread().getThreadGroup();
            this.namePrefix = threadNamePrefix + poolNumber.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(@NonNull Runnable r) {
            Thread thread = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            // 返回True该线程就是守护线程
            // 守护线程应该永远不去访问固有资源，如：数据库、文件等。因为它会在任何时候甚至在一个操作的中间发生中断。
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            thread.setPriority(threadPriority);
            return thread;
        }
    }
}


