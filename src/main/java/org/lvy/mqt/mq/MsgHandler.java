package org.lvy.mqt.mq;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import org.lvy.mqt.ui.RocketMQTester;
import org.lvy.mqt.util.Utils;

import java.util.List;

/**
 * Created by livvy on 15/8/7.
 */
public class MsgHandler implements MessageListenerConcurrently {

    private RocketMQTester mqTester;

    public MsgHandler(RocketMQTester mqTester) {
        this.mqTester = mqTester;
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        System.out.println(Thread.currentThread().getName()
                + " Receive New Messages: " + msgs.size());
        MessageExt msg = msgs.get(0);
        mqTester.getRecvMsgArea().append(Utils.getLineSeparator());
        mqTester.getRecvMsgArea().append("[接收消息] [topic: "
                + msg.getTopic()
                + " ,tags: " + msg.getTags()
                + " ,messages " + new String(msg.getBody())
        );
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
