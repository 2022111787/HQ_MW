package main.java.com.middleware;



import java.util.HashMap;
import java.util.Map;

public class MessageManager {
    private Map<String, TopicMessage> topics = new HashMap<>();

    public TopicMessage getTopic(String topicName) {
        return topics.computeIfAbsent(topicName, k -> new TopicMessage(topicName));
    }

    public void subscribe(String topicName, MessageObserver observer) {
        TopicMessage topic = getTopic(topicName);
        topic.attach(observer);
        System.out.println(observer + " 订阅了主题: " + topicName);
    }

    public void unsubscribe(String topicName, MessageObserver observer) {
        TopicMessage topic = getTopic(topicName);
        topic.detach(observer);
        System.out.println(observer + " 取消订阅了主题: " + topicName);
    }

    public void publish(String topicName, Message message) {
        TopicMessage topic = getTopic(topicName);
        topic.publishMessage(message);
    }
}

