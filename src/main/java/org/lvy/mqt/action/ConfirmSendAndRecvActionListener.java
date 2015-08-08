package org.lvy.mqt.action;

import com.alibaba.rocketmq.client.exception.MQClientException;
import org.apache.commons.lang3.StringUtils;
import org.lvy.mqt.mq.MQReceiver;
import org.lvy.mqt.mq.MQSender;
import org.lvy.mqt.ui.MessageTipDialog;
import org.lvy.mqt.ui.RocketMQTester;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by livvy on 15/8/7.
 */
public class ConfirmSendAndRecvActionListener implements ActionListener {

    private RocketMQTester rocketMQTester;

    public ConfirmSendAndRecvActionListener(RocketMQTester rocketMQTester) {
        this.rocketMQTester = rocketMQTester;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RocketMQTester mqTester = getRocketMQTester();
        if (!getRocketMQTester().isReadyToSendAndRecv()) {

            String recvGroup = mqTester.getTxtMqRecvGroupName().getText();
            String recvInstance = mqTester.getTxtMqRecvInstanceName().getText();
            String recvNamesrv = mqTester.getTxtMqRecvNamesrvAddr().getText();

            String group = mqTester.getTxtMqSenderGroupName().getText();
            String instance = mqTester.getTxtMqSenderInstanceName().getText();
            String namesrv = mqTester.getTxtMqSenderNamesrvAddr().getText();
            if (StringUtils.isBlank(recvGroup) || StringUtils.isBlank(recvInstance) || StringUtils.isBlank(recvNamesrv) ||
                    StringUtils.isBlank(group) || StringUtils.isBlank(instance) || StringUtils.isBlank(namesrv) ) {
                new MessageTipDialog(mqTester, "提示", "请将收发地址填写完整").setVisible(true);
                return;
            }

            try {
                MQSender mqSender = new MQSender(group, namesrv, instance);
                mqTester.setMqSender(mqSender);
                mqTester.getSendCallbackMsg().append("[发送端启动成功]");
                swapDisplayStats(mqTester, "修改收发地址", true, false);
            } catch (MQClientException e1) {
                String errorMessage = e1.getErrorMessage();
                mqTester.getSendCallbackMsg().append("[发送端启动异常] "+errorMessage);
            }
        } else {
            //以前启动  现在销毁
            swapDisplayStats(mqTester, "确认收发地址", false, true);
            MQSender mqSender = mqTester.getMqSender();
            if (mqSender != null) {
                mqSender.shutdown();
            }
            mqTester.setMqSender(null);

            MQReceiver mqReceiver = mqTester.getMqReceiver();
            if (mqReceiver != null) {
                mqReceiver.shutdown();
            }
            mqTester.setMqReceiver(null);
        }
    }

    private void swapDisplayStats(RocketMQTester mqTester, String tips, boolean isReadyToSendAndRecv, boolean enabled) {
        mqTester.getBtnConfirmAddr().setText(tips);
        mqTester.setIsReadyToSendAndRecv(isReadyToSendAndRecv);
        mqTester.getTxtMqRecvGroupName().setEnabled(enabled);
        mqTester.getTxtMqRecvInstanceName().setEnabled(enabled);
        mqTester.getTxtMqRecvNamesrvAddr().setEnabled(enabled);
        mqTester.getTxtMqSenderGroupName().setEnabled(enabled);
        mqTester.getTxtMqSenderInstanceName().setEnabled(enabled);
        mqTester.getTxtMqSenderNamesrvAddr().setEnabled(enabled);
    }

    public RocketMQTester getRocketMQTester() {
        return rocketMQTester;
    }
}
