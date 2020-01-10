package com.spider.controler;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import com.spider.download.DownloadManager;
import com.spider.parser.ParserManager;
import com.spider.pojo.CoursesResultEntity;

public class jdbc {
	public static void main(String[] agrs){
		try{
			System.out.println("����ɹ���");
			String url = "jdbc:mysql://localhost:3306/net?allowPublicKeyRetrieval=true&amp;useSSL=false";
			Class.forName("com.mysql.jdbc.Driver");
			String dbUser = "test";
			String dbPwd = "root";
			Connection conn = (Connection) DriverManager.getConnection(url,dbUser,dbPwd);
			String urll = "https://www.bilibili.com/v/technology/speech_course/?spm_id_from=333.827.b_7072696d6172795f6d656e75.49#/all/default/0/1/";
			String charset = "utf-8";
			String htmlSource = DownloadManager.getHtmlSource(urll);
			List<CoursesResultEntity> resultList = ParserManager.parseHtml(htmlSource);
			for (CoursesResultEntity coursesResultEntity : resultList) {
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
	}
}
