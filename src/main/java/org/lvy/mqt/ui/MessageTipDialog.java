package org.lvy.mqt.ui;

import sun.plugin2.message.Message;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by livvy on 15/8/8.
 */
public class MessageTipDialog extends JDialog {

    private int width = 300;
    private int height = 200;

    public MessageTipDialog(JFrame frame,String title,String tips) {
        super(frame, title, true);
        Container pane = getContentPane();
        pane.setLayout(new GridLayout(1,1));
        Label label = new Label(tips,Label.CENTER);
        label.setBounds(10,10,width - 10,height - 10);
        label.setLocation(10,10);
        pane.add(label);
        setBounds(120, 120, width, height);
        setResizable(false);
        setLocationRelativeTo(frame);
    }


}
