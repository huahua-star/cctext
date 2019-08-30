package Productor;

import Util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
//路由模式  --  key模式
public class SendExchangeDirectExchangeText {

    private final static  String EXCHANGE_NAME="test_exchange_direct";

    public static void main(String[] args) throws Exception {
        //连接
        Connection connection= ConnectionUtil.getConnection();
        //通道
        Channel channel=connection.createChannel();
        //声明 交换机 （名字，类型）
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        //消息内容
        String message="direct 删除商品";
        channel.basicPublish(EXCHANGE_NAME,"delete",null,message.getBytes());

        System.out.println("message:'"+message+"'");
        //消息内容
        message="direct 添加商品";
        channel.basicPublish(EXCHANGE_NAME,"add",null,message.getBytes());

        System.out.println("message:'"+message+"'");
        channel.close();
        connection.close();

    }


}
