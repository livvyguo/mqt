package org.lvy.mqt.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by livvy on 15/8/6.
 */
public class RocketMQTester extends JFrame {
    private int width = 840;
    private int height = 700;
    public RocketMQTester() throws HeadlessException {
        super();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        this.setSize(width, height);
        setLocation((screenSize.width - width) / 2, (screenSize.height - height) / 2);
        this.getContentPane().setLayout(new FlowLayout());
        this.setTitle("RocketMQ Tester");
        initRocketMQSenderAddr();
        initRocketMQRecvAddr();
        initBtnRecv();
        initRocketMQSenderInfo();
        initCallbackMsg();


        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
    }

    private void initBtnRecv() {

    }

    private void initCallbackMsg() {

        callbackPanel.setPreferredSize(new Dimension(800, 200));
        callbackPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        callbackPanel.setBorder(BorderFactory.createTitledBorder("发送结果"));
        callbackPanel.setLayout(new ScrollPaneLayout());
        sendCallbackMsg.setColumns(68);
        sendCallbackMsg.setRows(7);
        sendCallbackMsg.setText("msg");
        sendCallbackMsg.setLineWrap(true);
        callbackPanel.setViewportView(sendCallbackMsg);
        this.add(callbackPanel);
    }

    private void initRocketMQSenderAddr() {
        JPanel senderAddrPanel = new JPanel();
        senderAddrPanel.setLayout(new GridLayout(3,2));
        senderAddrPanel.setBorder(BorderFactory.createTitledBorder("发送地址")); //设置面板边框，实现分组框的效果，此句代码为关键代码
        senderAddrPanel.setLocation(10,10);

        lblMqSenderNamesrvAddr.setBounds(20, 20, 130, 20);
        senderAddrPanel.add(lblMqSenderNamesrvAddr);

        txtMqSenderNamesrvAddr.setBounds(150, 20, 130, 20);
        txtMqSenderNamesrvAddr.setText("192.168.36.190:9876");
        senderAddrPanel.add(txtMqSenderNamesrvAddr);

        lblMqSenderInstanceName.setBounds(20, 50, 130, 20);
        senderAddrPanel.add(lblMqSenderInstanceName);
        txtMqSenderInstanceName.setBounds(150, 50, 130, 20);
        txtMqSenderInstanceName.setText("testProducerInst");
        senderAddrPanel.add(txtMqSenderInstanceName);
        lblMqSenderGroupName.setBounds(20, 80, 130, 20);
        senderAddrPanel.add(lblMqSenderGroupName);
        txtMqSenderGroupName.setBounds(150, 80, 130, 20);
        txtMqSenderGroupName.setText("testProducerGroup");
        senderAddrPanel.add(txtMqSenderGroupName);
        this.add(senderAddrPanel);
    }


    private void initRocketMQRecvAddr() {
        JPanel recvAddrPanel = new JPanel();
        recvAddrPanel.setLayout(new GridLayout(3,2));
        recvAddrPanel.setBorder(BorderFactory.createTitledBorder("接收地址")); //设置面板边框，实现分组框的效果，此句代码为关键代码
        recvAddrPanel.setLocation(10,10);

        lblMqRecvNamesrvAddr.setBounds(20, 20, 130, 20);
        recvAddrPanel.add(lblMqRecvNamesrvAddr);

        txtMqRecvNamesrvAddr.setBounds(150, 20, 130, 20);
        txtMqRecvNamesrvAddr.setText("192.168.36.190:9876");
        recvAddrPanel.add(txtMqRecvNamesrvAddr);

        lblMqRecvInstanceName.setBounds(20, 50, 130, 20);
        recvAddrPanel.add(lblMqRecvInstanceName);
        txtMqRecvInstanceName.setBounds(150, 50, 130, 20);
        txtMqRecvInstanceName.setText("testRevcInst");
        recvAddrPanel.add(txtMqRecvInstanceName);
        lblMqRecvGroupName.setBounds(20, 80, 130, 20);
        recvAddrPanel.add(lblMqRecvGroupName);
        txtMqRecvGroupName.setBounds(150, 80, 130, 20);
        txtMqRecvGroupName.setText("testRecvGroup");
        recvAddrPanel.add(txtMqRecvGroupName);
        this.add(recvAddrPanel);
    }
    
    

    private void initRocketMQSenderInfo() {
        JPanel senderAddrPanel = new JPanel();
        senderAddrPanel.setLayout(new GridLayout(1,7));
        senderAddrPanel.setBounds(20, 50, 650, 300);
        senderAddrPanel.setBorder(BorderFactory.createTitledBorder("发送信息")); //设置面板边框，实现分组框的效果，此句代码为关键代码

        lblMsgTopic.setBounds(20, 20, 80, 20);
        senderAddrPanel.add(lblMsgTopic);


        txtMsgTopic.setBounds(150, 20, 80, 20);
        txtMsgTopic.setText("msg-test-topic");
        senderAddrPanel.add(txtMsgTopic);


        lblMsgTag.setBounds(281, 20, 40, 20);
        senderAddrPanel.add(lblMsgTag);


        txtMsgTag.setBounds(305, 20, 80, 20);
        txtMsgTag.setText("msg-test-tag");
        senderAddrPanel.add(txtMsgTag);



        lblMsg.setBounds(480, 20, 100, 20);
        senderAddrPanel.add(lblMsg);


        txtMsg.setBounds(520, 20, 100, 20);
        txtMsg.setText("test-data");
        senderAddrPanel.add(txtMsg);


        btnSendMsg.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String text = RocketMQTester.this.txtMqSenderGroupName.getText();
            }
        });
        senderAddrPanel.add(btnSendMsg);
        this.add(senderAddrPanel);
    }



    private JTextField txtMqSenderNamesrvAddr = new JTextField();
    private JTextField txtMqSenderGroupName = new JTextField();
    private JTextField txtMqSenderInstanceName = new JTextField();
    private JLabel lblMqSenderNamesrvAddr = new JLabel("NamesrvAddr:");
    private JLabel lblMqSenderInstanceName = new JLabel("InstanceName:");
    private JLabel lblMqSenderGroupName = new JLabel("GroupName:");


    private JTextField txtMqRecvNamesrvAddr = new JTextField();
    private JTextField txtMqRecvGroupName = new JTextField();
    private JTextField txtMqRecvInstanceName = new JTextField();
    private JLabel lblMqRecvNamesrvAddr = new JLabel("NamesrvAddr:");
    private JLabel lblMqRecvInstanceName = new JLabel("InstanceName:");
    private JLabel lblMqRecvGroupName = new JLabel("GroupName:");



    private JLabel lblMsgTopic = new JLabel("Topic:");
    private JTextField txtMsgTopic = new JTextField();
    private JLabel lblMsgTag = new JLabel("Tag:");
    private JTextField txtMsgTag = new JTextField();
    private JLabel lblMsg = new JLabel("Msg:");
    private JTextField txtMsg = new JTextField();
    private JButton btnSendMsg = new JButton("发送");


    private JTextArea sendCallbackMsg = new JTextArea();
    private JScrollPane callbackPanel = new JScrollPane();

    private JButton btnBeginRecv = new JButton("开始接收消息");

}
