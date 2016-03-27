package com.example.ibplayer.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomePageBean implements Serializable{
	
	public ArrayList<Content> result ;
	
	
	@Override
	public String toString() {
		return "HomePageBean [result=" + result + "]";
	}


	public class Content{
		
		public ArrayList<Body> body ;
		public Head head ;
		public String type ;
		


		@Override
		public String toString() {
			return "Content [body=" + body + ", head=" + head + ", type="
					+ type + "]";
		}

		public class Body {
			public String cover ;
			public String danmaku ;
			public String desc1 ;
			public String desc2 ;
			public String play ;
			public String small_cover ;
			public String title;
			
			@Override
			public String toString() {
				return "Body [cover=" + cover + ", danmaku=" + danmaku
						+ ", desc1=" + desc1 + ", desc2=" + desc2 + ", play="
						+ play + ", small_cover=" + small_cover + ", title="
						+ title + "]";
			}
			
			
		};
		
		public class Head {
			public String param ;
			public String style ;
			public String title ;
			
			@Override
			public String toString() {
				return "Head [param=" + param + ", style=" + style + ", title="
						+ title + "]";
			}
			
			
		};
		
		
	}
}
