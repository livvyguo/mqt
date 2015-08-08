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

    public MQReceiver(String group, String namesrv, String instance, String topic, String tags, MessageListenerConcurrently messageListenerConcurrently) throws MQClientException {
        init(group, namesrv, instance, topic, tags, messageListenerConcurrently);
    }

    private DefaultMQPushConsumer consumer;

    private void init(String group,String namesrv,String instance,String topic,String tags,MessageListenerConcurrently messageListenerConcurrently) throws MQClientException {
        consumer = new DefaultMQPushConsumer(
                group);
        consumer.setNamesrvAddr(namesrv);
        consumer.setInstanceName(instance);

        consumer.subscribe(topic, tags);

        consumer.registerMessageListener(messageListenerConcurrently);

        consumer.start();
    }

    public void shutdown() {
        if (consumer != null) {
            consumer.shutdown();
        }
    }
}
