package Consumer;

import Util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

public class ReceiveFanoutText2 {
    private final static String QUEUE_NAME="test_queue_work2";
    private final static String EXCHANGE_NAME="test_exchange_fanout";

    public static void main(String[] args) throws Exception {
        //获取到连接以及mq通道
        Connection connection= ConnectionUtil.getConnection();
        Channel channel=connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定转换机
        /**
         * exchangeBind 参数
         * queue 队列名称
         * exchange 交换器名称
         * routingKey 路由key
         * arguments 其它的一些参数
         */

        /**
         * 队列绑定 queueBind 参数
         * queue 队列名称
         * exchange 交换器名称
         * routingKey 路由key
         * arguments 其它的一些参数
         *
         */
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

        channel.basicQos(1);

        //定义队列的消费者
        QueueingConsumer consumer=new QueueingConsumer(channel);
        //监听队列，手动返回完成
        channel.basicConsume(QUEUE_NAME,false,consumer);
        while(true){
            QueueingConsumer.Delivery delivery=consumer.nextDelivery();
            String message=new String(delivery.getBody());
            System.out.println("Received2 '"+message+"'");
            Thread.sleep(10);

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }
    }
}
