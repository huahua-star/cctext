package Consumer;

import Util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

public class Receiver1 {
    private final static String  QUEUE_NAME="test_queue_work";

    public static void main(String[] args) throws Exception {
        Connection connection= ConnectionUtil.getConnection();

        Channel channel=connection.createChannel();
        //同一时刻服务器只会发送一条消息给消费者
        channel.basicQos(1);
        //定义队列的消费者
        QueueingConsumer queueingConsumer=new QueueingConsumer(channel);
        //监听 队列
        channel.basicConsume(QUEUE_NAME,false,queueingConsumer);

        //获取消息
        while(true){
            QueueingConsumer.Delivery delivery=queueingConsumer.nextDelivery();
            String msg=new String(delivery.getBody());
            System.out.println("Receiver1:'"+msg+"'");
            //休眠
            Thread.sleep(10);
            //返回确认状态，表示使用自动确认模式
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }


    }
}
