package Consumer;

import Util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

public class ReceiverDirectText1 {
    private final static String QUEUE_NAME="test_queue_direct_1";
    private final static String EXCHANGE_NAME="test_exchange_direct";

    public static void main(String[] args) throws Exception {
        Connection connection= ConnectionUtil.getConnection();
        Channel channel=connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定交换机   第三个参数 是 key
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"update");
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"delete");
        //同一时刻 服务器发一个
        channel.basicQos(1);

        //定义消费者
        QueueingConsumer consumer=new QueueingConsumer(channel);

        channel.basicConsume(QUEUE_NAME,false,consumer);

        while(true){
            QueueingConsumer.Delivery delivery=consumer.nextDelivery();
            String body=new String(delivery.getBody());
            System.out.println("Receiver1:direct："+body);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }

    }
}
