package org.lvy.mqt.action;

import org.lvy.mqt.ui.RocketMQTester;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by livvy on 15/8/7.
 */
public class ConfirmActionListener implements ActionListener {

    private RocketMQTester rocketMQTester;

    public ConfirmActionListener(RocketMQTester rocketMQTester) {
        this.rocketMQTester = rocketMQTester;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!getRocketMQTester().isReadyToSendAndRecv()) {
            //禁用
        } else {
            //放开
        }
    }

    public RocketMQTester getRocketMQTester() {
        return rocketMQTester;
    }
}
