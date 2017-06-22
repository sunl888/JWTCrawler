package hello;

import java.io.IOException;
import java.util.Map;
import java.util.regex.*;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Test {
	private final String studentInfoUri = "http://211.70.176.123/wap/grxx.asp";
	private final String loginUri = "http://211.70.176.123/wap/index.asp";
	private Map<String, String> cookies = null;

	public static void main(String[] args) throws IOException {
		new Test();
	}
	public Test() throws IOException {
		this.getStudentInfo();
	}

	private void loginJWC(String xh, String sfzh) throws IOException {
		Response res = Jsoup.connect(loginUri).data("xh", xh).data("sfzh", sfzh).timeout(0).execute();
		this.cookies = res.cookies();
	}

	public void getStudentInfo() throws IOException {
		String[] student = new String[14];
		loginJWC("1508220224", "340881199507175311");
		//loginJWC("1508220217", "342625199511033236");
		Document doc = null;
		doc = Jsoup.connect(studentInfoUri).userAgent("Mozilla").cookies(cookies).timeout(0).get();
		String regX = "<font color=\"red\">(?<stuName>.+)</font><font[\\s\\S]+?רҵ[\\s\\S]+?<td align=\"center\" width=\"170\" height=\"22\" valign=\"middle\">(?<stuNum>.*)</td>[\\s\\S]+?<td align=\"center\" width=\"150\" height=\"22\" valign=\"middle\">(?<major>.*)</td>[\\s\\S]+?�༶</font></td>[\\s\\S]+?<td align=\"center\" width=\"170\" height=\"22\" valign=\"middle\">(?<department>.*)</td>[\\s\\S]+?<td align=\"center\" width=\"150\" height=\"22\" valign=\"middle\">(?<class>.*)</td>[\\s\\S]+?����</font></td>[\\s\\S]+?<td align=\"center\" width=\"170\" height=\"22\" valign=\"middle\">(?<familyName>.*)</td>[\\s\\S]+?<td align=\"center\" width=\"150\" height=\"22\" valign=\"middle\">(?<place>.*)</td>[\\s\\S]+?������ò</font></td>[\\s\\S]+?<td align=\"center\" width=\"170\" height=\"22\" valign=\"middle\">(?<birth>.*)</td>[\\s\\S]+?<td align=\"center\" width=\"150\" height=\"22\" valign=\"middle\">(?<politicalStatus>.*)</td>[\\s\\S]+?�߿�������</font></td>[\\s\\S]+?<td align=\"center\" width=\"170\" height=\"22\" valign=\"middle\">(?<idcard>.*)</td>[\\s\\S]+?<td align=\"center\" width=\"150\" height=\"22\" valign=\"middle\">(?<examineeNumber>.*)</td>[\\s\\S]+?����ϵͳ��½����</font></td>[\\s\\S]+?<td align=\"center\" width=\"170\" height=\"22\" valign=\"middle\">(?<phone>.*)</td>[\\s\\S]+?<td align=\"center\" width=\"150\" height=\"22\" valign=\"middle\">(?<jwtPassword>.*)</td>";
		Matcher m = Pattern.compile(regX).matcher(doc.toString());
		while (m.find()) {
			student[1] = new String(m.group("stuNum"));
			student[2] = new String(m.group("stuName"));
			student[3] = new String(m.group("major")); // רҵ
			student[4] = new String(m.group("department")); // ϵ��
			student[5] = new String(m.group("class")); // �༶
			student[6] = new String(m.group("familyName")); // ����
			student[7] = new String(m.group("place"));
			student[8] = new String(m.group("birth"));
			student[9] = new String(m.group("politicalStatus"));
			student[10] = new String(m.group("idcard"));
			student[11] = new String(m.group("examineeNumber"));
			student[12] = new String(m.group("phone"));
			student[13] = new String(m.group("jwtPassword"));
		}
		if (student[1] == null) {
			System.out.println("û���ҵ���ѧ��");
		}
		User user = new User();
		user.insert(student);
	}
}
