package main.java.com.middleware;


import java.util.ArrayList;
import java.util.List;

public class TopicMessage implements MessageSubject {
    private List<MessageObserver> observers = new ArrayList<>();
    private String topic;

    public TopicMessage(String topic) {
        this.topic = topic;
    }

    @Override
    public void attach(MessageObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(MessageObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Message message) {
        for (MessageObserver observer : observers) {
            observer.update(message);  // 通知每个订阅者
        }
    }

    public void publishMessage(Message message) {
        System.out.println("发布消息到主题: " + topic);
        notifyObservers(message);
    }
}
