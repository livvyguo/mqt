package org.lvy.mqt.ui;

import org.lvy.mqt.action.ConfirmSendAndRecvActionListener;
import org.lvy.mqt.action.SendMsgActionListener;
import org.lvy.mqt.action.WindowsCloseHandler;
import org.lvy.mqt.mq.MQReceiver;
import org.lvy.mqt.mq.MQSender;
import org.lvy.mqt.util.ConfigUtils;

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

    public MQSender getMqSender() {
        return mqSender;
    }

    public void setMqSender(MQSender mqSender) {
        this.mqSender = mqSender;
    }

    public MQReceiver getMqReceiver() {
        return mqReceiver;
    }

    public void setMqReceiver(MQReceiver mqReceiver) {
        this.mqReceiver = mqReceiver;
    }

    public JTextArea getRecvMsgArea() {
        return recvMsgArea;
    }

    public void setRecvMsgArea(JTextArea recvMsgArea) {
        this.recvMsgArea = recvMsgArea;
    }

    public JScrollPane getRecvMsgPanelArea() {
        return recvMsgPanelArea;
    }

    public void setRecvMsgPanelArea(JScrollPane recvMsgPanelArea) {
        this.recvMsgPanelArea = recvMsgPanelArea;
    }

    private MQSender mqSender;

    private MQReceiver mqReceiver;


    public RocketMQTester() throws HeadlessException {
        super();
        int width = ConfigUtils.getIntConfig("default.screen.width");
        int height = ConfigUtils.getIntConfig("default.screen.height");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(width, height);
        this.setResizable(false);
        setLocation((screenSize.width - width) / 2, (screenSize.height - height) / 2);
        this.getContentPane().setLayout(new FlowLayout());
        this.setTitle("RocketMQ Tester");
        initRocketMQSenderAddr();
        initRocketMQRecvAddr();
        initOperateBtn();
        initRocketMQSenderInfo();
        initCallbackMsg();
        initRecvMsg();
        initRecvMsgDiplayer();
        this.addWindowListener(new WindowsCloseHandler(this));
    }

    private void initRecvMsgDiplayer() {
        recvMsgPanelArea.setPreferredSize(getDisplayInfoPreferredSize());
        recvMsgPanelArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        recvMsgPanelArea.setBorder(BorderFactory.createTitledBorder("接收结果"));
        recvMsgPanelArea.setLayout(new ScrollPaneLayout());
        recvMsgArea.setColumns(68);
        recvMsgArea.setRows(7);
        recvMsgArea.setText("");
        recvMsgArea.setLineWrap(true);
        recvMsgPanelArea.setViewportView(recvMsgArea);
        this.add(recvMsgPanelArea);
    }

    private void initRecvMsg() {
        JPanel recvPanel = new JPanel();
        recvPanel.setLayout(new GridLayout(1, 5));
        recvPanel.setBounds(20, 50, 850, 300);
        Dimension size = new Dimension(800, 50);
        recvPanel.setMinimumSize(size);
        recvPanel.setSize(size);
        recvPanel.setPreferredSize(size);
        recvPanel.setBorder(BorderFactory.createTitledBorder("接收信息"));

        lblRecvMsgTag.setBounds(20, 20, 80, 20);
        recvPanel.add(lblRecvMsgTopic);

        txtRecvMsgTopic.setBounds(150, 20, 80, 20);
        txtRecvMsgTopic.setText("msg-test-topic");
        recvPanel.add(txtRecvMsgTopic);


        lblRecvMsgTag.setBounds(281, 20, 40, 20);
        recvPanel.add(lblRecvMsgTag);


        txtRecvMsgTag.setBounds(305, 20, 80, 20);
        txtRecvMsgTag.setText("msg-test-tag");
        recvPanel.add(txtRecvMsgTag);

        btnBeginRecv.addActionListener(new ConfirmSendAndRecvActionListener(this));
        recvPanel.add(btnBeginRecv);
        this.add(recvPanel);
    }

    private void initOperateBtn() {
        JPanel btnRecvPanel = new JPanel();
        btnRecvPanel.setPreferredSize(getOperPreferredSize());
        btnRecvPanel.setBorder(BorderFactory.createTitledBorder("操作"));
        btnRecvPanel.setLayout(new GridLayout(3, 1));
        btnRecvPanel.setLocation(10, 10);
        btnRecvPanel.setBounds(10, 10, 200, 200);

        btnConfirmAddr.setBounds(10, 10, 180, 20);
        btnConfirmAddr.addActionListener(new ConfirmSendAndRecvActionListener(this));
        btnRecvPanel.add(btnConfirmAddr);

//        btnModifyAddr.setBounds(10, 10, 180, 20);
//        btnRecvPanel.add(btnModifyAddr);

//        btnBeginRecv.setBounds(10, 10, 180, 20);
//        btnRecvPanel.add(btnBeginRecv);

        this.add(btnRecvPanel);
    }



    private void initCallbackMsg() {
        callbackPanel.setPreferredSize(getDisplayInfoPreferredSize());
        callbackPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        callbackPanel.setBorder(BorderFactory.createTitledBorder("发送结果"));
        callbackPanel.setLayout(new ScrollPaneLayout());
        sendCallbackMsg.setColumns(68);
        sendCallbackMsg.setRows(7);
        sendCallbackMsg.setText("");
        sendCallbackMsg.setLineWrap(true);
        callbackPanel.setViewportView(sendCallbackMsg);
        this.add(callbackPanel);
    }

    private Dimension getDisplayInfoPreferredSize() {
        return new Dimension(800, 200);
    }

    private void initRocketMQSenderAddr() {
        JPanel senderAddrPanel = new JPanel();
        senderAddrPanel.setPreferredSize(getAddrPreferredSize());
        senderAddrPanel.setLayout(new GridLayout(3,2));
        senderAddrPanel.setBorder(BorderFactory.createTitledBorder("发送地址")); //设置面板边框，实现分组框的效果，此句代码为关键代码
        senderAddrPanel.setLocation(10,10);

        lblMqSenderNamesrvAddr.setBounds(20, 20, 130, 20);
        senderAddrPanel.add(lblMqSenderNamesrvAddr);

        txtMqSenderNamesrvAddr.setBounds(150, 20, 130, 20);
        txtMqSenderNamesrvAddr.setText(ConfigUtils.getConfig("default.sender.addr"));
        senderAddrPanel.add(txtMqSenderNamesrvAddr);

        lblMqSenderInstanceName.setBounds(20, 50, 130, 20);
        senderAddrPanel.add(lblMqSenderInstanceName);
        txtMqSenderInstanceName.setBounds(150, 50, 130, 20);
        txtMqSenderInstanceName.setText(ConfigUtils.getConfig("default.sender.instance"));
        senderAddrPanel.add(txtMqSenderInstanceName);
        lblMqSenderGroupName.setBounds(20, 80, 130, 20);
        senderAddrPanel.add(lblMqSenderGroupName);
        txtMqSenderGroupName.setBounds(150, 80, 130, 20);
        txtMqSenderGroupName.setText(ConfigUtils.getConfig("default.sender.group"));
        senderAddrPanel.add(txtMqSenderGroupName);
        this.add(senderAddrPanel);
    }

    private Dimension getAddrPreferredSize() {
        return new Dimension(330, 130);
    }

    private Dimension getOperPreferredSize() {
        return new Dimension(130, 130);
    }


    private void initRocketMQRecvAddr() {
        JPanel recvAddrPanel = new JPanel();
        recvAddrPanel.setPreferredSize(getAddrPreferredSize());
        recvAddrPanel.setLayout(new GridLayout(3,2));
        recvAddrPanel.setBorder(BorderFactory.createTitledBorder("接收地址")); //设置面板边框，实现分组框的效果，此句代码为关键代码
        recvAddrPanel.setLocation(10,10);

        lblMqRecvNamesrvAddr.setBounds(20, 20, 130, 20);
        recvAddrPanel.add(lblMqRecvNamesrvAddr);

        txtMqRecvNamesrvAddr.setBounds(150, 20, 130, 20);
        txtMqRecvNamesrvAddr.setText(ConfigUtils.getConfig("default.recv.addr"));
        recvAddrPanel.add(txtMqRecvNamesrvAddr);

        lblMqRecvInstanceName.setBounds(20, 50, 130, 20);
        recvAddrPanel.add(lblMqRecvInstanceName);
        txtMqRecvInstanceName.setBounds(150, 50, 130, 20);
        txtMqRecvInstanceName.setText(ConfigUtils.getConfig("default.recv.instance"));
        recvAddrPanel.add(txtMqRecvInstanceName);
        lblMqRecvGroupName.setBounds(20, 80, 130, 20);
        recvAddrPanel.add(lblMqRecvGroupName);
        txtMqRecvGroupName.setBounds(150, 80, 130, 20);
        txtMqRecvGroupName.setText(ConfigUtils.getConfig("default.recv.group"));
        recvAddrPanel.add(txtMqRecvGroupName);
        this.add(recvAddrPanel);
    }
    
    

    private void initRocketMQSenderInfo() {
        JPanel senderAddrPanel = new JPanel();
        senderAddrPanel.setLayout(new GridLayout(1,7));
        Dimension size = new Dimension(800, 50);
        senderAddrPanel.setMinimumSize(size);
        senderAddrPanel.setSize(size);
        senderAddrPanel.setPreferredSize(size);
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
        btnSendMsg.addActionListener(new SendMsgActionListener(this));
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

    private JTextArea recvMsgArea = new JTextArea();
    private JScrollPane recvMsgPanelArea = new JScrollPane();

    private JButton btnBeginRecv = new JButton("接收");
    private JButton btnConfirmAddr = new JButton("确认收发地址");
    private JButton btnModifyAddr = new JButton("修改收发地址");


    private JLabel lblRecvMsgTopic = new JLabel("Topic:");
    private JTextField txtRecvMsgTopic = new JTextField();
    private JLabel lblRecvMsgTag = new JLabel("Tag:");
    private JTextField txtRecvMsgTag = new JTextField();

    private boolean isInitSender = false;
    private boolean isInitRecv = false;
    private boolean isReadyToSendAndRecv = false;

    public boolean isReadyToSendAndRecv() {

        return isReadyToSendAndRecv;
    }

    public void setIsReadyToSendAndRecv(boolean isReadyToSendAndRecv) {
        this.isReadyToSendAndRecv = isReadyToSendAndRecv;
    }


    public boolean isInitRecv() {
        return isInitRecv;
    }

    public void setIsInitRecv(boolean isInitRecv) {
        this.isInitRecv = isInitRecv;
    }

    public boolean isInitSender() {
        return isInitSender;
    }

    public void setIsInitSender(boolean isInitSender) {
        this.isInitSender = isInitSender;
    }

    public JTextField getTxtMqSenderNamesrvAddr() {
        return txtMqSenderNamesrvAddr;
    }

    public void setTxtMqSenderNamesrvAddr(JTextField txtMqSenderNamesrvAddr) {
        this.txtMqSenderNamesrvAddr = txtMqSenderNamesrvAddr;
    }

    public JTextField getTxtMqSenderGroupName() {
        return txtMqSenderGroupName;
    }

    public void setTxtMqSenderGroupName(JTextField txtMqSenderGroupName) {
        this.txtMqSenderGroupName = txtMqSenderGroupName;
    }

    public JTextField getTxtMqSenderInstanceName() {
        return txtMqSenderInstanceName;
    }

    public void setTxtMqSenderInstanceName(JTextField txtMqSenderInstanceName) {
        this.txtMqSenderInstanceName = txtMqSenderInstanceName;
    }

    public JLabel getLblMqSenderNamesrvAddr() {
        return lblMqSenderNamesrvAddr;
    }

    public void setLblMqSenderNamesrvAddr(JLabel lblMqSenderNamesrvAddr) {
        this.lblMqSenderNamesrvAddr = lblMqSenderNamesrvAddr;
    }

    public JLabel getLblMqSenderInstanceName() {
        return lblMqSenderInstanceName;
    }

    public void setLblMqSenderInstanceName(JLabel lblMqSenderInstanceName) {
        this.lblMqSenderInstanceName = lblMqSenderInstanceName;
    }

    public JLabel getLblMqSenderGroupName() {
        return lblMqSenderGroupName;
    }

    public void setLblMqSenderGroupName(JLabel lblMqSenderGroupName) {
        this.lblMqSenderGroupName = lblMqSenderGroupName;
    }

    public JTextField getTxtMqRecvNamesrvAddr() {
        return txtMqRecvNamesrvAddr;
    }

    public void setTxtMqRecvNamesrvAddr(JTextField txtMqRecvNamesrvAddr) {
        this.txtMqRecvNamesrvAddr = txtMqRecvNamesrvAddr;
    }

    public JTextField getTxtMqRecvGroupName() {
        return txtMqRecvGroupName;
    }

    public void setTxtMqRecvGroupName(JTextField txtMqRecvGroupName) {
        this.txtMqRecvGroupName = txtMqRecvGroupName;
    }

    public JTextField getTxtMqRecvInstanceName() {
        return txtMqRecvInstanceName;
    }

    public void setTxtMqRecvInstanceName(JTextField txtMqRecvInstanceName) {
        this.txtMqRecvInstanceName = txtMqRecvInstanceName;
    }

    public JLabel getLblMqRecvNamesrvAddr() {
        return lblMqRecvNamesrvAddr;
    }

    public void setLblMqRecvNamesrvAddr(JLabel lblMqRecvNamesrvAddr) {
        this.lblMqRecvNamesrvAddr = lblMqRecvNamesrvAddr;
    }

    public JLabel getLblMqRecvInstanceName() {
        return lblMqRecvInstanceName;
    }

    public void setLblMqRecvInstanceName(JLabel lblMqRecvInstanceName) {
        this.lblMqRecvInstanceName = lblMqRecvInstanceName;
    }

    public JLabel getLblMqRecvGroupName() {
        return lblMqRecvGroupName;
    }

    public void setLblMqRecvGroupName(JLabel lblMqRecvGroupName) {
        this.lblMqRecvGroupName = lblMqRecvGroupName;
    }

    public JLabel getLblMsgTopic() {
        return lblMsgTopic;
    }

    public void setLblMsgTopic(JLabel lblMsgTopic) {
        this.lblMsgTopic = lblMsgTopic;
    }

    public JTextField getTxtMsgTopic() {
        return txtMsgTopic;
    }

    public void setTxtMsgTopic(JTextField txtMsgTopic) {
        this.txtMsgTopic = txtMsgTopic;
    }

    public JLabel getLblMsgTag() {
        return lblMsgTag;
    }

    public void setLblMsgTag(JLabel lblMsgTag) {
        this.lblMsgTag = lblMsgTag;
    }

    public JTextField getTxtMsgTag() {
        return txtMsgTag;
    }

    public void setTxtMsgTag(JTextField txtMsgTag) {
        this.txtMsgTag = txtMsgTag;
    }

    public JLabel getLblMsg() {
        return lblMsg;
    }

    public void setLblMsg(JLabel lblMsg) {
        this.lblMsg = lblMsg;
    }

    public JTextField getTxtMsg() {
        return txtMsg;
    }

    public void setTxtMsg(JTextField txtMsg) {
        this.txtMsg = txtMsg;
    }

    public JButton getBtnSendMsg() {
        return btnSendMsg;
    }

    public void setBtnSendMsg(JButton btnSendMsg) {
        this.btnSendMsg = btnSendMsg;
    }

    public JTextArea getSendCallbackMsg() {
        return sendCallbackMsg;
    }

    public void setSendCallbackMsg(JTextArea sendCallbackMsg) {
        this.sendCallbackMsg = sendCallbackMsg;
    }

    public JScrollPane getCallbackPanel() {
        return callbackPanel;
    }

    public void setCallbackPanel(JScrollPane callbackPanel) {
        this.callbackPanel = callbackPanel;
    }

    public JButton getBtnBeginRecv() {
        return btnBeginRecv;
    }

    public void setBtnBeginRecv(JButton btnBeginRecv) {
        this.btnBeginRecv = btnBeginRecv;
    }

    public JButton getBtnConfirmAddr() {
        return btnConfirmAddr;
    }

    public void setBtnConfirmAddr(JButton btnConfirmAddr) {
        this.btnConfirmAddr = btnConfirmAddr;
    }

    public JButton getBtnModifyAddr() {
        return btnModifyAddr;
    }

    public void setBtnModifyAddr(JButton btnModifyAddr) {
        this.btnModifyAddr = btnModifyAddr;
    }

    public JLabel getLblRecvMsgTopic() {
        return lblRecvMsgTopic;
    }

    public void setLblRecvMsgTopic(JLabel lblRecvMsgTopic) {
        this.lblRecvMsgTopic = lblRecvMsgTopic;
    }

    public JTextField getTxtRecvMsgTopic() {
        return txtRecvMsgTopic;
    }

    public void setTxtRecvMsgTopic(JTextField txtRecvMsgTopic) {
        this.txtRecvMsgTopic = txtRecvMsgTopic;
    }

    public JLabel getLblRecvMsgTag() {
        return lblRecvMsgTag;
    }

    public void setLblRecvMsgTag(JLabel lblRecvMsgTag) {
        this.lblRecvMsgTag = lblRecvMsgTag;
    }

    public JTextField getTxtRecvMsgTag() {
        return txtRecvMsgTag;
    }

    public void setTxtRecvMsgTag(JTextField txtRecvMsgTag) {
        this.txtRecvMsgTag = txtRecvMsgTag;
    }
}
