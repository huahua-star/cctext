package Util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class ConnectionUtil {
    public static Connection getConnection() throws IOException {
        //定义连接工厂
        ConnectionFactory factory=new ConnectionFactory();
        //设置服务地址
        factory.setHost("localhost");
        //端口
        factory.setPort(5672);
        //设置账号信息  ，用户名 密码 vhost  虚拟机名字
        factory.setVirtualHost("testHost");
        factory.setUsername("admin");
        factory.setPassword("admin");
        //通过工程获取连接
        Connection connection=factory.newConnection();
        System.out.println("111");
        return connection;
    }


}
