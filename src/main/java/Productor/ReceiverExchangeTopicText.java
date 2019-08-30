package Productor;

import Util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
//通配符模式
public class ReceiverExchangeTopicText {
    private final static String EXCHANGE_NAME="test_exchange_topic";

    public static void main(String[] args) throws Exception {
        Connection connection= ConnectionUtil.getConnection();
        Channel channel= connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");
        String msg="topic Hello World";
        channel.basicPublish(EXCHANGE_NAME,"topic.1",null,msg.getBytes());
        System.out.println("msg："+msg);
        msg="topic Hello World";
        channel.basicPublish(EXCHANGE_NAME,"two.1",null,msg.getBytes());
        System.out.println("msg："+msg);
        channel.close();
        connection.close();
    }

}
