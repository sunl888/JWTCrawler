package hello;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Sunlong on 2017/6/23.
 */

public class MainUI extends JFrame implements ActionListener {

    private final JLabel title;
    private JButton confirm, cancel;
    private JTextField stuNum, idCard;
    private JLabel jLabel1, jLabel2;
    private final int LEFT = 50;// 到左边界的距离
    private final int TOP = 130;
    private final int BUTTONWIDTH = 80;
    private final int TITLEWIDTH = 50;// 标题宽度
    private final int FILEDWIDTH = 150;// 字段宽度
    private final int HEIGHT = 30;// 字段高度
    private final int DISTANCE = 10; // 两列之间的距离

    public static void main(String[] args) {
        MainUI mainUI = new MainUI();
    }

    MainUI() {
        title = new JLabel("学生信息查询");
        title.setBounds(LEFT*2+20,10,200,100);
        title.setFont(new Font("黑体",Font.BOLD,24));
        add(title);

        jLabel1 = new JLabel("学号：  ");
        jLabel1.setBounds(LEFT*2, TOP, TITLEWIDTH, HEIGHT);
        jLabel2 = new JLabel("身份证：");
        jLabel2.setBounds(LEFT*2, TOP+70, TITLEWIDTH*2, HEIGHT);
        add(jLabel1);
        add(jLabel2);

        stuNum = new JTextField("",10);
        stuNum.setBounds(LEFT*2+TITLEWIDTH, TOP, FILEDWIDTH, HEIGHT);
        idCard = new JTextField("",10);
        idCard.setBounds(LEFT*2+TITLEWIDTH, TOP+70, FILEDWIDTH, HEIGHT);
        add(stuNum);
        add(idCard);

        confirm = new JButton("登录");
        confirm.setBounds(LEFT*2+20, TOP+140, BUTTONWIDTH, HEIGHT);
        confirm.addActionListener(this);
        cancel = new JButton("取消");
        cancel.setBounds(LEFT*2+BUTTONWIDTH+30,TOP+140, BUTTONWIDTH, HEIGHT);
        cancel.addActionListener(this);
        add(confirm);
        add(cancel);

        this.setLayout(null);
        this.setTitle("学生信息抓取");
        this.setSize(420,400);
        //设置窗口相对于指定组件的位置，如果组件当前未显示，或者 c 为 null，则此窗口将置于屏幕的中央。
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirm) {
            if ("".equals(stuNum.getText()) || "".equals(idCard.getText())) {
                JOptionPane.showMessageDialog(null, "学号或身份证为空！", "提示消息", JOptionPane.WARNING_MESSAGE);
                this.stuNum.requestFocus();//设置当前对象的stuNum字段焦点
            } else {
                this.dispose();//关闭当前页面
                new LoginJWC(stuNum.getText().toUpperCase(), idCard.getText().toUpperCase());
            }
        }
        if (e.getSource() == cancel) {
            System.exit(0);
        }
    }
}