package com.spider.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import com.spider.pojo.CoursesResultEntity;

public class PersistenceManager {
	public static boolean persist(List<CoursesResultEntity> entity) throws Exception {
		String url = "jdbc:mysql://localhost:3306/net?allowPublicKeyRetrieval=true&amp;useSSL=false";
		Class.forName("com.mysql.jdbc.Driver");
		String dbUser = "test";
		String dbPwd = "root";
		Connection conn = (Connection) DriverManager.getConnection(url,dbUser,dbPwd);
		try{
			for (CoursesResultEntity coursesResultEntity : entity) {
				String sql = "insert into courses(Tid,Sid,Cname,Ctime,Cimage,Cinfo,Curl) values(?,?,?,?,?,?,?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				System.out.println(coursesResultEntity);
				ps.setInt(1, coursesResultEntity.getTid());
				ps.setInt(2, coursesResultEntity.getSid());
				ps.setString(3, coursesResultEntity.getCname());
				ps.setDate(4,new Date( coursesResultEntity.getCtime().getTime()));
				ps.setString(5, coursesResultEntity.getCimage());
				ps.setString(6, coursesResultEntity.getCinfo());
				ps.setString(7, coursesResultEntity.getCurl());
				ps.executeUpdate();
			}
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}
}
