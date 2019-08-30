package Consumer;

import Util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

public class Receiver2 {
    private final static String QUEUE_NAME="test_queue_work";

    public static void main(String[] args) throws Exception {
        Connection connection= ConnectionUtil.getConnection();

        Channel channel=connection.createChannel();
        channel.basicQos(1);
        QueueingConsumer queueingConsumer=new QueueingConsumer(channel);

        channel.basicConsume(QUEUE_NAME,false,queueingConsumer);

        while(true){
            QueueingConsumer.Delivery delivery=queueingConsumer.nextDelivery();
            String msg=new String(delivery.getBody());
            System.out.println("Receiver2"+msg+"'");
            // 休眠1 s
            Thread.sleep(1000);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }

    }

}
