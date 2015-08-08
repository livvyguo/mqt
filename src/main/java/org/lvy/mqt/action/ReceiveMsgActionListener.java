package org.lvy.mqt.action;

import com.alibaba.rocketmq.client.exception.MQClientException;
import org.apache.commons.lang3.StringUtils;
import org.lvy.mqt.mq.MQReceiver;
import org.lvy.mqt.mq.MsgHandler;
import org.lvy.mqt.ui.MessageTipDialog;
import org.lvy.mqt.ui.RocketMQTester;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by livvy on 15/8/8.
 */
public class ReceiveMsgActionListener implements ActionListener {

    private RocketMQTester mqTester;

    public ReceiveMsgActionListener(RocketMQTester mqTester) {
        this.mqTester = mqTester;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!mqTester.isInitRecv()) {
            String topic = mqTester.getTxtRecvMsgTopic().getText();
            String tags = mqTester.getTxtRecvMsgTag().getText();
            if (!mqTester.isReadyToSendAndRecv()) {
                new MessageTipDialog(this.mqTester, "提示", "请先确认收发地址").setVisible(true);
                return;
            }
            if (StringUtils.isBlank(topic) || StringUtils.isBlank(tags)) {
                new MessageTipDialog(this.mqTester, "提示", "请填写topic和tags").setVisible(true);
                return;
            }
            String mqGroup = mqTester.getTxtMqRecvGroupName().getText();
            String mqInstance = mqTester.getTxtMqRecvInstanceName().getText();
            String mqNamesrv = mqTester.getTxtMqRecvNamesrvAddr().getText();
            MsgHandler msgHandler = new MsgHandler(mqTester);
            try {
                MQReceiver receiver = new MQReceiver(mqGroup, mqNamesrv, mqInstance, topic, tags, msgHandler);
                mqTester.setMqReceiver(receiver);
                mqTester.getRecvMsgArea().append("[消息接收端启动成功]");

                swapStats("停止", true, false);
                return;
            } catch (MQClientException e1) {
                mqTester.getRecvMsgArea().append("[消息接收端启动失败] " + e1);
            }
        } else {
            MQReceiver mqReceiver = mqTester.getMqReceiver();
            if (mqReceiver != null) {
                mqReceiver.shutdown();
            }
            mqTester.setMqReceiver(null);
            swapStats("接收", false, true);
        }
    }

    private void swapStats(String tips, boolean isInitRecv, boolean enabled) {
        mqTester.getBtnBeginRecv().setText(tips);
        mqTester.setIsInitRecv(isInitRecv);
        mqTester.getTxtRecvMsgTag().setEnabled(enabled);
        mqTester.getTxtRecvMsgTopic().setEnabled(enabled);
    }
}
