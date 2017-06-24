package hello;

import java.sql.*;

/**
 * Created by Sunlong on 2017/6/24.
 */

public class User extends Thread{
	private String dbUrl = "jdbc:mysql://127.0.0.1:3306/hello?characterEncoding=utf8&useSSL=false";// 根据实际情况变化
	private String dbUser = "root";
	private String dbPass = "root";
	private String[]data;

	public void run()
	{
		insert();
	}
	public User(){

	}

	public User(String []data)
	{
		this.data = data;
	}

	public int insert() {
		int i = 0;
		String sql = "INSERT INTO `users`(`student_id`, `student_name`, `major`, `department`, `class`, `nation`, `place_of_origin`, `date_of_birth`, `political_outlook`, `id_card`, `examinee_number`, `cell_phone_number`, `password`) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection cnn = getConn();
		try {
			PreparedStatement preStmt = cnn.prepareStatement(sql);
			preStmt.setString(1, "".equals(data[1]) ? null : data[1]);
			preStmt.setString(2, "".equals(data[2]) ? null : data[2]);
			preStmt.setString(3, "".equals(data[3]) ? null : data[3]);
			preStmt.setString(4, "".equals(data[4]) ? null : data[4]);
			preStmt.setString(5, "".equals(data[5]) ? null : data[5]);
			preStmt.setString(6, "".equals(data[6]) ? null : data[6]);
			preStmt.setString(7, "".equals(data[7]) ? null : data[7]);
			preStmt.setString(8, "".equals(data[8]) ? null : data[8]);
			preStmt.setString(9, "".equals(data[9]) ? null : data[9]);
			preStmt.setString(10, "".equals(data[10]) ? null : data[10]);
			preStmt.setString(11, "".equals(data[11]) ? null : data[11]);
			preStmt.setString(12, "".equals(data[12]) ? null : data[12]);
			preStmt.setString(13, "".equals(data[13]) ? null : data[13]);
			i = preStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cnn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				cnn = null;
			}
		}
		return i;
	}

	public ResultSet getStudent(String student_id) {
		String sql = "select * from users where student_id='" + student_id.trim() + "'";
		Connection cnn = getConn();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = cnn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	private Connection getConn() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}