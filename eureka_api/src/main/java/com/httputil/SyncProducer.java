package com.httputil;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendCallback;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 发送同步消息
 * */
@Component
public class SyncProducer  {
    private static final Logger logger = LoggerFactory.getLogger(SyncProducer.class);

    public static void onMessage(String tagsName, String msg) {
        switch (tagsName) {
            case "ITEM_TEMPLATE_SAVE":
                System.out.println("MQ一声笑");
                break;
            default:
                break;
        }

    }

    public static void main(String[] args) throws Exception {
        sada();



    }

    public static void onMessage(String tagsName, String msg, MessageExt messageExt) {
        onMessage(tagsName, msg);
    }


    public static void sada()throws Exception {
        //1、创建消息生成对象，并制定生产者组名
        DefaultMQProducer producer=new  DefaultMQProducer("group1");
        //2、制定NameServer地址
        producer.setNamesrvAddr("localhost:9876");
        //3、启动  producer
        producer.start();

        for (int i = 0; i <10 ; i++) {
            //4、创建消息对象，指定Topic、tog和消息内容
            /**
             * 参数1  消息主题Topic
             * 参数2  消息tog
             * 参数3  消息内容
             * */
            Message message =new Message("group1","ITEM_TEMPLATE_SAVE",("ht"+i).getBytes());
            //5、发送消息
            SendResult result = producer.send(message);
            //发送消息状态
            SendStatus sendStatus = result.getSendStatus();
            //消息ID
            String msgId = result.getMsgId();
            //消息队列ID
            int queueId = result.getMessageQueue().getQueueId();
            System.out.println("发送消息状态:"+sendStatus+"消息ID:"+msgId+"消息队列ID:"+queueId);
            //睡眠1秒
            TimeUnit.SECONDS.sleep(1);

        }
        //关闭生产者
        producer.shutdown();
    }

    //消费者
    public  static void sds() throws MQClientException {
        // Instantiate with specified consumer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name");

        // Specify name server addresses.
        consumer.setNamesrvAddr("localhost:9876");

        // Subscribe one more more topics to consume.
        consumer.subscribe("group1", "*");
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {


                for (MessageExt messageExt : msgs) {
                    String msgBody = new String(messageExt.getBody());
                    onMessage(messageExt.getTags(), msgBody, messageExt);

                }
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        //Launch the consumer instance.
        consumer.start();

        System.out.printf("Consumer Started.%n");

    }

    /**
    *异步发送消息
    *
    */
    public void sdssss() throws Exception {

        //1、创建消息生成对象，并制定生产者组名
        DefaultMQProducer producer=new  DefaultMQProducer("group1");
        //2、制定NameServer地址
        producer.setNamesrvAddr("localhost:9876");
        //3、启动  producer
        producer.start();

        for (int i = 0; i <10 ; i++) {
            //4、创建消息对象，指定Topic、tog和消息内容
            /**
             * 参数1  消息主题Topic
             * 参数2  消息tog
             * 参数3  消息内容
             * */
            Message message =new Message("group1","ITEM_TEMPLATE_SAVE",("ht"+i).getBytes());
            //5、发送异步消息
            producer.send(message, new SendCallback() {
                /**
                 * 发送成功回调函数
                 * */
                @Override
                public void onSuccess(SendResult sendResult) {

                }
                /**
                 * 发送失败回调函数
                 * */
                @Override
                public void onException(Throwable throwable) {

                }
            });

            //睡眠1秒
            TimeUnit.SECONDS.sleep(1);

        }
        //关闭生产者
        producer.shutdown();


    }
}
