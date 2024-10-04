package com.middleware;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertTrue;

public class ResponseTimeTest {

    private MessageManager messageManager;
    private long startTime;
    private long endTime;
    private final int messageCount = 100; // 要发送的消息数量

    @Before
    public void setUp() {
        // 初始化 MessageManager
        messageManager = new MessageManager();
    }

    @Test
    public void testConcurrentMessageResponseTime() throws InterruptedException {
        // 创建一个观察者（根据你的实现）
        MessageObserver observer = new MessageObserver() {
            @Override
            public void update(Message message) {
                // 处理接收到的消息
            }
        };

        String topicName = "testTopic";
        messageManager.subscribe(topicName, observer); // 订阅消息

        CountDownLatch latch = new CountDownLatch(messageCount); // 创建计数器
        ExecutorService executorService = Executors.newFixedThreadPool(10); // 创建线程池

        startTime = System.currentTimeMillis();

        for (int i = 0; i < messageCount; i++) {
            final int messageIndex = i;
            executorService.submit(() -> {
                Message message = new Message("消息 #" + messageIndex);
                messageManager.publish(topicName, message);
                latch.countDown(); // 每发布一条消息，计数器减一
            });
        }

        latch.await(); // 等待所有消息发布完成
        endTime = System.currentTimeMillis();

        long responseTime = endTime - startTime;
        System.out.println("发送 " + messageCount + " 条消息的响应时间: " + responseTime + " 毫秒");

        // 确保响应时间在预期范围内
        assertTrue("响应时间应在500毫秒内", responseTime < 500);

        executorService.shutdown(); // 关闭线程池
    }
}
