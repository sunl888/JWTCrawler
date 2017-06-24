package hello;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Sunlong on 2017/6/25.
 */

public class StudentInfoUI extends JFrame implements ActionListener {
    private JLabel stuNum,stuName,department,major,classes,familyName,place,birth;// 學號，姓名，系別，專業,班級，民族，籍貫，出生日期
    private JLabel politicalStatus,idcard,phone,examineeNumber,jwtPassword;// 政治面貌，身份證，手機號碼, 高考考生號，教務處密碼
    private JLabel stuNumValue,stuNameValue,departmentValue,majorValue,classesValue,familyNameValue,placeValue,birthValue;
    private JLabel politicalStatusValue,idcardValue,phoneValue,examineeNumberValue,jwtPasswordValue;
    private JButton reQuery;
    private JLabel title;
    private final int LEFT = 60;// 到左边界的距离
    private final int TOP = 100;
    private final int TITLEWIDTH = 50;// 标题宽度
    private final int FILEDWIDTH = 150;// 字段宽度
    private final int HEIGHT = 40;// 字段高度
    private final int DISTANCE = 10; // 两列之间的距离

    public StudentInfoUI(){
        this.setLayout(null);
        title = new JLabel("学生信息查询");
        title.setBounds(LEFT*3,10,200,100);
        title.setFont(new Font("黑体",Font.BOLD,24));
        add(title);

        stuNum = new JLabel("学号：");
        stuNum.setBounds(LEFT,TOP,TITLEWIDTH,HEIGHT);
        add(stuNum);
        stuNumValue = new JLabel("");
        stuNumValue.setBounds(LEFT+TITLEWIDTH,TOP,FILEDWIDTH,HEIGHT);
        add(stuNumValue);
        stuName = new JLabel("姓名：");
        stuName.setBounds(LEFT+TITLEWIDTH+FILEDWIDTH+DISTANCE,TOP,TITLEWIDTH,HEIGHT);
        add(stuName);
        stuNameValue = new JLabel("");
        stuNameValue .setBounds(LEFT+TITLEWIDTH*2+FILEDWIDTH+DISTANCE,TOP,FILEDWIDTH,HEIGHT);
        add(stuNameValue );

        department = new JLabel("系别：");
        department.setBounds(LEFT,TOP+50,TITLEWIDTH,HEIGHT);
        add(department);
        departmentValue = new JLabel("");
        departmentValue.setBounds(LEFT+TITLEWIDTH,TOP+50,FILEDWIDTH,HEIGHT);
        add(departmentValue);
        major = new JLabel("专业：");
        major.setBounds(LEFT+TITLEWIDTH+FILEDWIDTH+DISTANCE,TOP+50,TITLEWIDTH,HEIGHT);
        add(major);
        majorValue = new JLabel("");
        majorValue.setBounds(LEFT+TITLEWIDTH*2+FILEDWIDTH+DISTANCE,TOP+50,FILEDWIDTH,HEIGHT);
        add(majorValue);

        classes = new JLabel("班级：");
        classes.setBounds(LEFT,TOP+100,TITLEWIDTH*2,HEIGHT);
        add(classes);
        classesValue = new JLabel("");
        classesValue.setBounds(LEFT+TITLEWIDTH,TOP+100,FILEDWIDTH,HEIGHT);
        add(classesValue);
        familyName = new JLabel("民族：");
        familyName.setBounds(LEFT+TITLEWIDTH+FILEDWIDTH+DISTANCE,TOP+100,TITLEWIDTH,HEIGHT);
        add(familyName);
        familyNameValue = new JLabel("");
        familyNameValue.setBounds(LEFT+TITLEWIDTH*2+FILEDWIDTH+DISTANCE,TOP+100,FILEDWIDTH,HEIGHT);
        add(familyNameValue);

        place = new JLabel("籍贯：");
        place.setBounds(LEFT,TOP+150,TITLEWIDTH,HEIGHT);
        add(place);
        placeValue = new JLabel("");
        placeValue.setBounds(LEFT+TITLEWIDTH,TOP+150,FILEDWIDTH,HEIGHT);
        add(placeValue);
        birth = new JLabel("出生日期：");
        birth.setBounds(LEFT+TITLEWIDTH+FILEDWIDTH+DISTANCE,TOP+150,TITLEWIDTH*2,HEIGHT);
        add(birth);
        birthValue = new JLabel("");
        birthValue.setBounds(LEFT+TITLEWIDTH*2+FILEDWIDTH+DISTANCE+20,TOP+150,FILEDWIDTH,HEIGHT);
        add(birthValue);

        politicalStatus = new JLabel("政治面貌：");
        politicalStatus.setBounds(LEFT,TOP+200,TITLEWIDTH+20,HEIGHT);
        add(politicalStatus);
        politicalStatusValue = new JLabel("");
        politicalStatusValue.setBounds(LEFT+TITLEWIDTH+20,TOP+200,FILEDWIDTH,HEIGHT);
        add(politicalStatusValue);
        idcard = new JLabel("身份证：");
        idcard.setBounds(LEFT+TITLEWIDTH+FILEDWIDTH+DISTANCE,TOP+200,TITLEWIDTH*2-20,HEIGHT);
        add(idcard);
        idcardValue = new JLabel("");
        idcardValue.setBounds(LEFT+TITLEWIDTH*2+FILEDWIDTH+DISTANCE,TOP+200,FILEDWIDTH*2,HEIGHT);
        add(idcardValue);

        phone = new JLabel("电话号码：");
        phone.setBounds(LEFT,TOP+250,TITLEWIDTH+20,HEIGHT);
        add(phone);
        phoneValue = new JLabel("");
        phoneValue.setBounds(LEFT+TITLEWIDTH+20,TOP+250,FILEDWIDTH,HEIGHT);
        add(phoneValue);
        examineeNumber = new JLabel("考生号：");
        examineeNumber.setBounds(LEFT+TITLEWIDTH+FILEDWIDTH+DISTANCE,TOP+250,TITLEWIDTH*2-20,HEIGHT);
        add(examineeNumber);
        examineeNumberValue = new JLabel("");
        examineeNumberValue.setBounds(LEFT+TITLEWIDTH*2+FILEDWIDTH+DISTANCE,TOP+250,FILEDWIDTH*2,HEIGHT);
        add(examineeNumberValue);

        jwtPassword = new JLabel("教务处密码：");
        jwtPassword.setBounds(LEFT,TOP+300,TITLEWIDTH*2,HEIGHT);
        add(jwtPassword);
        jwtPasswordValue = new JLabel("");
        jwtPasswordValue.setBounds(LEFT+TITLEWIDTH*2,TOP+300,FILEDWIDTH*2,HEIGHT);
        add(jwtPasswordValue);
        
        reQuery = new JButton("重新查询");
        reQuery.setBounds(LEFT+TITLEWIDTH*2+FILEDWIDTH,TOP+300,FILEDWIDTH-30,HEIGHT);
        reQuery.addActionListener(this);
        reQuery.setFocusable(false);
        add(reQuery);

        this.setSize(480,500);
        //TODO 这里必须要先设置窗口的尺寸才可以居中
        setCenter(this);//or this.setLocationRelativeTo(null);
        this.setResizable(false);//不显示最大化按钮
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    //窗口居中
    private void setCenter(JFrame form){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width-form.getWidth())/2;
        int y = (screenSize.height-form.getHeight())/2;
        form.setLocation(x,y);
    }

    public void actionPerformed(ActionEvent event){
        if(event.getSource() ==reQuery){
            this.dispose();
            new MainUI();
        }
    }

    protected void refreshData(String[] data)
    {
        stuNumValue.setText(data[1]);
        stuNameValue.setText(data[2]);
        majorValue.setText(data[3]);
        departmentValue.setText(data[4]);
        classesValue.setText(data[5]);
        familyNameValue.setText(data[6]);
        placeValue.setText(data[7]);
        birthValue.setText(data[8]);
        politicalStatusValue.setText(data[9]);
        idcardValue.setText(data[10]);
        examineeNumberValue.setText(data[11]);
        phoneValue.setText(data[12]);
        jwtPasswordValue.setText(data[13]);
    }

}
