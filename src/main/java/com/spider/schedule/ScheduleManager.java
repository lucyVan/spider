package com.spider.schedule;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class ScheduleManager {
	public static LinkedList<String> todoTaskList = new LinkedList<String>();
	
	public static Set<String> todoTaskSet = new HashSet<String>();
	
	public static void addSeedUrlToTaskList(String seedUrl){
		if(todoTaskSet.contains(seedUrl)){
			return ;
		}
		todoTaskSet.add(seedUrl);
		todoTaskList.add(seedUrl);
	}
	
	public static String getUrl(){
		String url = todoTaskList.removeFirst();
		return url;
	}

}