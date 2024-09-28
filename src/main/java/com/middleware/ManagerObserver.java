package main.java.com.middleware;


public class ManagerObserver implements MessageObserver {
    private String name;

    public ManagerObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(Message message) {
        System.out.println(name + " 接收到消息: " + message.getContent());
        // 在此处理接收到的消息，如审批培训申请、课程确认等
    }

    @Override
    public String toString() {
        return name;
    }
}

