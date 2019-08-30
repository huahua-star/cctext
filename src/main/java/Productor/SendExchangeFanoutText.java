package Productor;

import Util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class SendExchangeFanoutText {
    // 订阅模式（广播 ）
    private final static String EXCHANGE_NAME="test_exchange_fanout";

    public static void main(String[] args) throws Exception {
        Connection connection=ConnectionUtil.getConnection();

        Channel channel=connection.createChannel();

        //声明exchange  交换机  及类型 为fanout
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        //消息内容
        String message="fanout Hello World!";
        channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());

        System.out.println("message:'"+message+"'");

        channel.close();
        connection.close();

    }
}
