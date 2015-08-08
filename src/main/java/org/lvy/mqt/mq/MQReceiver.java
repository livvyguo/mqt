package org.lvy.mqt.mq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Created by livvy on 15/8/7.
 * 消息接收类
 */
public class MQReceiver {


    private DefaultMQPushConsumer consumer;

    public void init(String group,String namesrv,String instance,String topic,String tags,MessageListenerConcurrently messageListenerConcurrently) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(
                group);
        consumer.setNamesrvAddr(namesrv);
        consumer.setInstanceName(instance);

        /**
         * 订阅指定topic下tags分别等于TagA或TagC或TagD 可以多个
         */
        consumer.subscribe(topic, tags);

        consumer.registerMessageListener(messageListenerConcurrently);
        /**
         * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
         */
        consumer.start();
    }

    public void shutdown() {
        if (consumer != null) {
            consumer.shutdown();
        }
    }
}
