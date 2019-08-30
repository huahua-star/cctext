package Consumer;

import Util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

public class Receiver {
    private final static String QUEUE_NAME = "q_text_1";
    //private final static String QUEUE_NAME="test_work_queue";


    public static void main(String[] args) throws Exception {
        // 获取到连接以及mq通道
        Connection connection= ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();
        //设置同一时刻 服务器只会发一条消息给消费者
        //channel.basicQos(1);

        //定义队列的消费者
        QueueingConsumer consumer=new QueueingConsumer(channel);
        //监听队列 ，false 表示手动返回完成状态，true 表示自动
        channel.basicConsume(QUEUE_NAME,true,consumer);
        while(true){
            QueueingConsumer.Delivery delivery=consumer.nextDelivery();
            String msg=new String(delivery.getBody());
            System.out.println("Received :'"+msg+"'");
        }

     }

}
//休眠一秒
            //Thread.sleep(1000);
// 使用自动确认模式
//channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);

