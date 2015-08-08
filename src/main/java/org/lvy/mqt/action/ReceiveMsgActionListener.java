package org.lvy.mqt.action;

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

    }
}
