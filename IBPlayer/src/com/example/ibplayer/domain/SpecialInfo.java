package com.example.ibplayer.domain;

import java.util.ArrayList;

public class SpecialInfo {
	
	ArrayList<Content> list ;
	
	
		public ArrayList<Content> getList() {
		return list;
	}


	public void setList(ArrayList<Content> list) {
		this.list = list;
	}


		public class Content {
			int height ;
			String imagekey ;
			String imageurl ;
			String remark ;
			String remark2 ;
			String spid ;
			String spname ;
			String style ;
			String title ;
			String type ;
			String width ;
			
			public int getHeight() {
				return height;
			}
			public void setHeight(int height) {
				this.height = height;
			}
			public String getImagekey() {
				return imagekey;
			}
			public void setImagekey(String imagekey) {
				this.imagekey = imagekey;
			}
			public String getImageurl() {
				return imageurl;
			}
			public void setImageurl(String imageurl) {
				this.imageurl = imageurl;
			}
			public String getRemark() {
				return remark;
			}
			public void setRemark(String remark) {
				this.remark = remark;
			}
			public String getRemark2() {
				return remark2;
			}
			public void setRemark2(String remark2) {
				this.remark2 = remark2;
			}
			public String getSpid() {
				return spid;
			}
			public void setSpid(String spid) {
				this.spid = spid;
			}
			public String getSpname() {
				return spname;
			}
			public void setSpname(String spname) {
				this.spname = spname;
			}
			public String getStyle() {
				return style;
			}
			public void setStyle(String style) {
				this.style = style;
			}
			public String getTitle() {
				return title;
			}
			public void setTitle(String title) {
				this.title = title;
			}
			public String getType() {
				return type;
			}
			public void setType(String type) {
				this.type = type;
			}
			public String getWidth() {
				return width;
			}
			public void setWidth(String width) {
				this.width = width;
			}
			
			
		}

}
