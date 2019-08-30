package Test;

import Util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class ReceiverExchangeTopicText {
    private final static String EXCHANGE_NAME="test_exchange_topic_test";
    private final static String QUEUE_NAME1="test_queue_topic_1_test";
    private final static String QUEUE_NAME2="test_queue_topic_2_test";
    public static void main(String[] args) throws Exception {
        Connection connection= ConnectionUtil.getConnection();
        Channel channel= connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");
        channel.queueDeclare(QUEUE_NAME1,false,false,false,null);
        channel.queueDeclare(QUEUE_NAME2,false,false,false,null);
        String msg="topic Hello World";
        channel.basicPublish(EXCHANGE_NAME,"topic.1",null,msg.getBytes());
        System.out.println("msg："+msg);
        msg="two Hello World";
        channel.basicPublish(EXCHANGE_NAME,"two.1",null,msg.getBytes());
        System.out.println("msg："+msg);
        channel.close();
        connection.close();
    }

}
