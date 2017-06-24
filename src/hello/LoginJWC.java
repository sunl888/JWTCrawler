package hello;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.regex.*;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.JOptionPane;

/**
 * Created by Sunlong on 2017/6/23.
 */

class LoginJWC extends Thread {
    private final String studentInfoUri = "http://211.70.176.123/wap/grxx.asp";
    private final String loginUri = "http://211.70.176.123/wap/index.asp";
    private String stuNum = "";
    private String idCard = "";
    private String regularExpression = "<font color=\"red\">(?<stuName>.+)</font><font[\\s\\S]+?专业[\\s\\S]+?<td align=\"center\" width=\"170\" height=\"22\" valign=\"middle\">(?<stuNum>.*)</td>[\\s\\S]+?<td align=\"center\" width=\"150\" height=\"22\" valign=\"middle\">(?<major>.*)</td>[\\s\\S]+?班级</font></td>[\\s\\S]+?<td align=\"center\" width=\"170\" height=\"22\" valign=\"middle\">(?<department>.*)</td>[\\s\\S]+?<td align=\"center\" width=\"150\" height=\"22\" valign=\"middle\">(?<class>.*)</td>[\\s\\S]+?籍贯</font></td>[\\s\\S]+?<td align=\"center\" width=\"170\" height=\"22\" valign=\"middle\">(?<familyName>.*)</td>[\\s\\S]+?<td align=\"center\" width=\"150\" height=\"22\" valign=\"middle\">(?<place>.*)</td>[\\s\\S]+?政治面貌</font></td>[\\s\\S]+?<td align=\"center\" width=\"170\" height=\"22\" valign=\"middle\">(?<birth>.*)</td>[\\s\\S]+?<td align=\"center\" width=\"150\" height=\"22\" valign=\"middle\">(?<politicalStatus>.*)</td>[\\s\\S]+?高考考生号</font></td>[\\s\\S]+?<td align=\"center\" width=\"170\" height=\"22\" valign=\"middle\">(?<idcard>.*)</td>[\\s\\S]+?<td align=\"center\" width=\"150\" height=\"22\" valign=\"middle\">(?<examineeNumber>.*)</td>[\\s\\S]+?教务系统登陆密码</font></td>[\\s\\S]+?<td align=\"center\" width=\"170\" height=\"22\" valign=\"middle\">(?<phone>.*)</td>[\\s\\S]+?<td align=\"center\" width=\"150\" height=\"22\" valign=\"middle\">(?<jwtPassword>.*)</td>";
    private Map<String, String> cookies = null;
    StudentInfoUI stu = null;

    LoginJWC(String stuNum, String idCard) {
        stu = new StudentInfoUI();
        this.stuNum = stuNum;
        this.idCard = idCard;
        //启动一个线程获取学生信息
        this.start();
    }

    public void run() {
        //获取学生信息
        getStudentInfo();
    }

    private void loginJWC() {
        Response res = null;
        try {
            res = Jsoup.connect(loginUri).data("xh", this.stuNum).data("sfzh", this.idCard).timeout(0).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.cookies = res.cookies();
    }

    private void getStudentInfo() {
        Boolean hasRecord = false;
        String[] student = new String[14];
        //判断数据库里有没有该学生信息
        ResultSet userInfo = new User().getStudent(this.stuNum);
        try {
            while (userInfo.next()) {
                student[1] = userInfo.getString(1);
                student[2] = userInfo.getString(2);
                student[3] = userInfo.getString(3);
                student[4] = userInfo.getString(4);
                student[5] = userInfo.getString(5);
                student[6] = userInfo.getString(6);
                student[7] = userInfo.getString(7);
                student[8] = userInfo.getString(8);
                student[9] = userInfo.getString(9);
                student[10] = userInfo.getString(10);
                student[11] = userInfo.getString(11);
                student[12] = userInfo.getString(12);
                student[13] = userInfo.getString(13);
                hasRecord = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                userInfo.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //数据库里没有数据
        if (!hasRecord) {
            loginJWC();
            Document doc = null;
            try {
                doc = Jsoup.connect(studentInfoUri)
                        .userAgent("Mozilla")
                        .cookies(cookies)
                        .timeout(0)
                        .get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Matcher m = Pattern.compile(this.regularExpression).matcher(doc.toString());
            while (m.find()) {
                student[1] = m.group("stuNum");// 學號
                student[2] = m.group("stuName");// 姓名
                student[3] = m.group("major"); // 专业
                student[4] = m.group("department"); // 系别
                student[5] = m.group("class"); // 班级
                student[6] = m.group("familyName"); // 民族
                student[7] = m.group("place");// 籍貫
                student[8] = m.group("birth");// 出生日期
                student[9] = m.group("politicalStatus");// 政治面貌
                student[10] = m.group("idcard");// 身份證號碼
                student[11] = m.group("examineeNumber");// 高考考生號
                student[12] = m.group("phone");// 手機號
                student[13] = m.group("jwtPassword");// 教務處密碼
            }
            if (student[1] == null) {
                JOptionPane.showMessageDialog(null, "sorry,没有找到该学生");
                stu.dispose();//关闭当前页面
                new MainUI();
                
            } else {
                User user = new User(student);
                user.start();
            }
        }
        //刷新视图
        stu.refreshData(student);
    }
}