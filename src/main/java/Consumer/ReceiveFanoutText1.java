package Consumer;

import Util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

public class ReceiveFanoutText1 {
    private final static String QUEUE_NAME="test_queue_work1";

    private final static String EXCHANGE_NAME="test_exchange_fanout";

    public static void main(String[] args) throws Exception {
        //获取到连接以及mq通道
        Connection connection= ConnectionUtil.getConnection();

        Channel channel=connection.createChannel();

        //声明队列  queue: 队列名称 durable： 是否持久化  exclusive：是否排外的 autoDelete：是否自动删除  arguments:什么时候删除
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //绑定交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

        //同一时刻服务器只发一条消息给消费者
        channel.basicQos(1);

        //定义队列的消费者
        QueueingConsumer consumer =new QueueingConsumer(channel);

        //监听队列，手动返回完成 标志
        channel.basicConsume(QUEUE_NAME,false,consumer);

        //获取消息
        while (true){
            //获得消息
            QueueingConsumer.Delivery delivery=consumer.nextDelivery();
            String message=new String(delivery.getBody());
            System.out.println("Receive : '"+message+"'");
            Thread.sleep(10);

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }


    }





}
