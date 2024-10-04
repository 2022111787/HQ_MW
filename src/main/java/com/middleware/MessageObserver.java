package com.middleware;


public interface MessageObserver {
    void update(Message message);  // 接收到消息后的更新方法
}
