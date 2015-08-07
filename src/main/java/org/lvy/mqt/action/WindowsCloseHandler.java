package org.lvy.mqt.action;

import org.lvy.mqt.mq.MQReceiver;
import org.lvy.mqt.mq.MQSender;
import org.lvy.mqt.ui.RocketMQTester;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by livvy on 15/8/7.
 */
public class WindowsCloseHandler extends WindowAdapter {

    private RocketMQTester mqTester;

    public WindowsCloseHandler(RocketMQTester mqTester) {
        this.mqTester = mqTester;
    }

    public void windowClosing(WindowEvent e) {
        try {
            MQSender mqSender = mqTester.getMqSender();
            if (mqSender != null) {
                mqSender.shutdown();
            }

            MQReceiver mqReceiver = mqTester.getMqReceiver();
            if (mqReceiver !=null) {
                mqReceiver.shutdown();
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }
        super.windowClosing(e);
        System.exit(0);
    }
}
