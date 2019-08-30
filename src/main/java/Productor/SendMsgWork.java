package Productor;

import Util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

public class SendMsgWork {
    private  final static  String QUEUE_NAME="test_queue_work";

    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection= ConnectionUtil.getConnection();

        Channel channel =connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        for (int i=0;i<100;i++)
        {
            //消息内容
            String msg="发送消息;"+(i+1);
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            System.out.println(msg);

            Thread.sleep(i*10);
        }
        channel.close();
        connection.close();
    }
}
