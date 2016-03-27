package com.example.ibplayer.domain;

public class VideoInfoItem {

	private String cid ;
	private String img ;
	private String src ;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	@Override
	public String toString() {
		return "VideoInfoItem [cid=" + cid + ", img=" + img + ", src=" + src
				+ "]";
	}
	
	
	
	
}
