package org.lvy.mqt.mq;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

import java.util.concurrent.TimeUnit;

/**
 * Created by livvy on 15/8/6.
 * 消息发送类
 */
public class MQSender {
    DefaultMQProducer producer = null;

    public MQSender(String groupName,String namesrvAddr,String instanceName) throws MQClientException {
        init(groupName, namesrvAddr, instanceName);
    }

    public void init(String groupName,String namesrvAddr,String instanceName) throws MQClientException {

        producer = new DefaultMQProducer("ProducerGroupName");
        producer.setNamesrvAddr("192.168.36.190:9876");
        producer.setInstanceName("Producer");
        /**
         * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
         * 注意：切记不可以在每次发送消息时，都调用start方法
         */
        producer.start();

    }

    public SendResult sendMessage(Message msg) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        return producer.send(msg);
    }

    public void shutdown() {
        if (producer != null) {
            producer.shutdown();
        }
    }
}
