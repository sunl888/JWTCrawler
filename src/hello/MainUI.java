package hello;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Sunlong on 2017/6/23.
 */

public class MainUI extends JFrame implements ActionListener {

    private JButton confirm, cancel;
    private JTextField stuNum;
    private JTextField idCard;
    private JLabel jLabel1, jLabel2;

    public static void main(String[] args) {
        MainUI mainUI = new MainUI();
    }

    MainUI() {
        //创建组件
        confirm = new JButton("登錄");
        confirm.setBounds(116, 200, 70, 30);
        confirm.addActionListener(this);

        cancel = new JButton("取消");
        cancel.setBounds(220, 200, 70, 30);
        cancel.addActionListener(this);

        stuNum = new JTextField(10);
        stuNum.setBounds(150, 80, 170, 30);

        idCard = new JTextField(10);
        idCard.setBounds(150, 140, 170, 30);

        jLabel1 = new JLabel("學號：  ");
        jLabel1.setBounds(70, 80, 100, 30);

        jLabel2 = new JLabel("身份證：");
        jLabel2.setBounds(70, 140, 100, 30);
        add(confirm);
        add(cancel);

        add(stuNum);

        add(jLabel1);
        add(jLabel2);

        add(idCard);
        this.setLayout(null);
        this.setTitle("JWT找回密碼");
        this.setSize(430, 360);
        //设置窗口相对于指定组件的位置，如果组件当前未显示，或者 c 为 null，则此窗口将置于屏幕的中央。
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirm) {

            //JOptionPane.showMessageDialog(null,"正在查询...","提示消息",JOptionPane.WARNING_MESSAGE);
            this.dispose();//关闭当前页面
            new LoginJWC(stuNum.getText(), idCard.getText());
        }

        if(e.getSource() == cancel) {
            System.exit(0);
        }
    }
}