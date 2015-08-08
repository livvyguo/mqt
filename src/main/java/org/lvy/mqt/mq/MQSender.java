package org.lvy.mqt.mq;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

/**
 * Created by livvy on 15/8/6.
 * 消息发送类
 */
public class MQSender {
    DefaultMQProducer producer = null;

    public MQSender(String groupName,String namesrvAddr,String instanceName) throws MQClientException {
        init(groupName, namesrvAddr, instanceName);
    }

    private void init(String groupName,String namesrvAddr,String instanceName) throws MQClientException {
        producer = new DefaultMQProducer(groupName);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setInstanceName(instanceName);
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
