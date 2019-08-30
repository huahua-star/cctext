package Productor;

import Util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.tools.Tracer;

public class SendMsgText {
    private final static  String QUEUE_NAME="q_text_1";

    public static void main(String[] args) throws Exception{
        //获取连接 以及 mq通道
        Connection connection= ConnectionUtil.getConnection();
        //从连接中 创建通道
        Channel channel=connection.createChannel();

        //声明创建队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //消息内容
        String message="hello world";
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        //关闭通道和连接
        channel.close();
        connection.close();

    }
}
