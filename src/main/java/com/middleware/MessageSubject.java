package main.java.com.middleware;

public interface MessageSubject {
    void attach(MessageObserver observer);  // 添加观察者
    void detach(MessageObserver observer);  // 移除观察者
    void notifyObservers(Message message);  // 通知所有观察者
}