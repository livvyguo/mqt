package org.lvy.mqt.action;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import org.apache.commons.lang3.StringUtils;
import org.lvy.mqt.mq.MQSender;
import org.lvy.mqt.ui.MessageTipDialog;
import org.lvy.mqt.ui.RocketMQTester;
import org.lvy.mqt.util.IDUtils;
import org.lvy.mqt.util.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by livvy on 15/8/8.
 */
public class SendMsgActionListener implements ActionListener {

    private RocketMQTester mqTester;

    public SendMsgActionListener(RocketMQTester mqTester) {
        this.mqTester = mqTester;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MQSender mqSender = mqTester.getMqSender();
        if (mqSender != null) {
            String tag = mqTester.getTxtMsgTag().getText();
            String topic = mqTester.getTxtMsgTopic().getText();
            String msg = mqTester.getTxtMsg().getText();
            if (StringUtils.isBlank(tag) || StringUtils.isBlank(topic) || StringUtils.isBlank(msg)) {
                new MessageTipDialog(this.mqTester,"提示","请您填写topic tag message").setVisible(true);
                return;
            }
            Message message = new Message(topic, tag, IDUtils.getId(), msg.getBytes());
            JTextArea callbackMsg = mqTester.getSendCallbackMsg();
            callbackMsg.append(Utils.getLineSeparator());
            try {
                SendResult result = mqSender.sendMessage(message);
                callbackMsg.append("[发送成功] " + result);
                callbackMsg.setCaretPosition(callbackMsg.getText().length());
            } catch (Exception ee) {
                callbackMsg.append("[发送失败] " + ee.getMessage());
                callbackMsg.setCaretPosition(callbackMsg.getText().length());
            }

        } else {
            new MessageTipDialog(this.mqTester,"提示","请您先确认收发地址").setVisible(true);
        }
    }
}
