package com.example.ibplayer.domain;

import java.util.ArrayList;

public class SpecialRelInfo {
	
	ArrayList<List> list ;
	
	
	
	public ArrayList<List> getList() {
		return list;
	}



	public void setList(ArrayList<List> list) {
		this.list = list;
	}



	public class List{
		String aid ;
		String cid ;
		String click ;
		String cover ;
		String from ;
		String page ;
		String title ;
		public String getAid() {
			return aid;
		}
		public void setAid(String aid) {
			this.aid = aid;
		}
		public String getCid() {
			return cid;
		}
		public void setCid(String cid) {
			this.cid = cid;
		}
		public String getClick() {
			return click;
		}
		public void setClick(String click) {
			this.click = click;
		}
		public String getCover() {
			return cover;
		}
		public void setCover(String cover) {
			this.cover = cover;
		}
		public String getFrom() {
			return from;
		}
		public void setFrom(String from) {
			this.from = from;
		}
		public String getPage() {
			return page;
		}
		public void setPage(String page) {
			this.page = page;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		
		
	}

}
