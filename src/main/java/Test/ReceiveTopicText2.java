package Test;

import Util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

public class ReceiveTopicText2 {
    private final static String QUEUE_NAME="test_queue_topic_2_test";
    private final static String EXCHANGE_NAME="test_exchange_topic_test";

    public static void main(String[] args) throws Exception {
        Connection connection= ConnectionUtil.getConnection();
        Channel channel=connection.createChannel();

        channel.basicQos(1);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"*.*");

        QueueingConsumer consumer=new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME,false,consumer);


        while(true){
            QueueingConsumer.Delivery delivery=consumer.nextDelivery();
            String msg=new String(delivery.getBody());
            System.out.println("TopicText1:'"+msg+"'");
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }




    }


}
