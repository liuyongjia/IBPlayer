package com.example.ibplayer.domain;

import java.util.ArrayList;

public class VideoUrl {
	
	public ArrayList<UrlInfo> durl ;
	
	@Override
	public String toString() {
		return "VideoUrl [durl=" + durl + "]";
	}

	public class UrlInfo {
		public String url ;

		@Override
		public String toString() {
			return "UrlInfo [url=" + url + "]";
		}
		
	}
}
