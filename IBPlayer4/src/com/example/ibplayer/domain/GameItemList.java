package com.example.ibplayer.domain;

import java.util.ArrayList;

public class GameItemList {
	
	ArrayList<List> list ;
	
	
	
	public ArrayList<List> getList() {
		return list;
	}



	public void setList(ArrayList<List> list) {
		this.list = list;
	}



	public class List{
		
		String apkpkg ;
		String apksize ;
		String apkurl ;
		String apkverkey ;
		String description ;
		String height ;
		String icon ;
		String imagekey ;
		String imageurl ;
		String producer ;
		String remark ;
		String remark2 ;
		String style ;
		String title ;
		String type ;
		String vendor ;
		String width ;
		
		ArrayList<ImageList>  image_list ;
		
		
		public String getApkpkg() {
			return apkpkg;
		}


		public void setApkpkg(String apkpkg) {
			this.apkpkg = apkpkg;
		}


		public String getApksize() {
			return apksize;
		}


		public void setApksize(String apksize) {
			this.apksize = apksize;
		}


		public String getApkurl() {
			return apkurl;
		}


		public void setApkurl(String apkurl) {
			this.apkurl = apkurl;
		}


		public String getApkverkey() {
			return apkverkey;
		}


		public void setApkverkey(String apkverkey) {
			this.apkverkey = apkverkey;
		}


		public String getDescription() {
			return description;
		}


		public void setDescription(String description) {
			this.description = description;
		}


		public String getHeight() {
			return height;
		}


		public void setHeight(String height) {
			this.height = height;
		}


		public String getIcon() {
			return icon;
		}


		public void setIcon(String icon) {
			this.icon = icon;
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


		public String getProducer() {
			return producer;
		}


		public void setProducer(String producer) {
			this.producer = producer;
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


		public String getVendor() {
			return vendor;
		}


		public void setVendor(String vendor) {
			this.vendor = vendor;
		}


		public String getWidth() {
			return width;
		}


		public void setWidth(String width) {
			this.width = width;
		}


		public ArrayList<ImageList> getImage_list() {
			return image_list;
		}


		public void setImage_list(ArrayList<ImageList> image_list) {
			this.image_list = image_list;
		}


		public class ImageList{
			String height ;
			String src ;
			String width ;
			public String getHeight() {
				return height;
			}
			public void setHeight(String height) {
				this.height = height;
			}
			public String getSrc() {
				return src;
			}
			public void setSrc(String src) {
				this.src = src;
			}
			public String getWidth() {
				return width;
			}
			public void setWidth(String width) {
				this.width = width;
			}
			
			
		}
	}

}
