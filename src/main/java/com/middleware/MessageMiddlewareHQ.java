package com.middleware;

public class MessageMiddlewareHQ {
    public static void main(String[] args) {
        // 创建消息管理器
        MessageManager messageManager = new MessageManager();

        // 创建观察者（订阅者）
        ManagerObserver manager = new ManagerObserver("公司经理");
        ManagerObserver instructor = new ManagerObserver("讲师");
        ManagerObserver student = new ManagerObserver("学员");
        ManagerObserver executor = new ManagerObserver("执行人");
        ManagerObserver fieldman = new ManagerObserver("现场工作人员");

        // 公司经理订阅培训申请通知
        messageManager.subscribe("培训申请", manager);

        // 讲师订阅课程信息通知
        messageManager.subscribe("课程信息", instructor);

        // 学员订阅报名确认通知
        messageManager.subscribe("报名确认", student);

        // 签到确认通知
        messageManager.subscribe("李忠忠签到确认", fieldman);
        messageManager.subscribe("李月月签到确认", fieldman);

        // 执行人订阅发布培训课程通知
        messageManager.subscribe("新课程python发布", executor);

        // 发布消息
        messageManager.publish("培训申请", new Message("新的培训申请：技术培训"));
        messageManager.publish("课程信息", new Message("新的课程：Java编程"));
        messageManager.publish("课程信息", new Message("新的课程：python编程"));
        messageManager.publish("报名确认", new Message("学员张三报名成功"));

        messageManager.publish("李忠忠签到确认", new Message("李忠忠签到成功"));
        messageManager.publish("李月月签到确认", new Message("李月月签到成功"));

        messageManager.publish("新课程python发布", new Message("新课程python发布成功"));

        // 取消订阅
        messageManager.unsubscribe("培训申请", manager);
    }
}
