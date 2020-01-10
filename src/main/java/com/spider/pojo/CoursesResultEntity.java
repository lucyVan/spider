package com.spider.pojo;

import java.util.Date;

public class CoursesResultEntity {
	String Cname;
	int Tid;
	int Sid;
	String Curl;
	String Cinfo;
	String Cimage;
	public String getCimage() {
		return Cimage;
	}

	public void setCimage(String cimage) {
		Cimage = cimage;
	}

	Date Ctime;
	public String getCurl() {
		return Curl;
	}

	public void setCurl(String curl) {
		Curl = curl;
	}

	
	
	public String getCname() {
		return Cname;
	}

	public void setCname(String cname) {
		Cname = cname;
	}



	public int getTid() {
		return Tid;
	}

	public void setTid(int tid) {
		Tid = tid;
	}

	public int getSid() {
		return Sid;
	}

	public void setSid(int sid) {
		Sid = sid;
	}

	public String getCinfo() {
		return Cinfo;
	}

	public void setCinfo(String cinfo) {
		Cinfo = cinfo;
	}

	public Date getCtime() {
		return Ctime;
	}

	public void setCtime(Date ctime) {
		Ctime = ctime;
	}

	public CoursesResultEntity() {
		super();
	}

	@Override
	public String toString() {
		return "CoursesResultEntity [Cname=" + Cname + ", Tid=" + Tid + ", Sid=" + Sid + ", Curl=" + Curl + ", Cinfo="
				+ Cinfo + ", Ctime=" + Ctime + "]";
	}
	
}
