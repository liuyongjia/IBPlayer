package com.example.ibplayer.domain;

import java.util.ArrayList;

public class BangumiList {
	
	public ArrayList<Content> list;

	@Override
	public String toString() {
		return "BangumiList [list=" + list + "]";
	}

	public class Content {
		
		public String aid;//视频av号
		public String typeid;//视频类型
		public String title;//视频标题
		public String sbutitle;
		public String play;//视频播放数
		public String review;//评论数
		public String video_review;//视频弹幕数
		public String favorites;//视频收藏数
		public String mid;
		public String author;//Up主
		public String description;//视频简介
		public String create;//视频发布时间
		public String pic;//视频封面地址
		public String credit;
		public String coins;//视频硬币数
		public String duration;//视频长度
		@Override
		public String toString() {
			return "Content [aid=" + aid + ", typeid=" + typeid + ", title="
					+ title + ", sbutitle=" + sbutitle + ", play=" + play
					+ ", review=" + review + ", video_review=" + video_review
					+ ", favorites=" + favorites + ", mid=" + mid + ", author="
					+ author + ", description=" + description + ", create="
					+ create + ", pic=" + pic + ", credit=" + credit
					+ ", coins=" + coins + ", duration=" + duration + "]";
		}
		public String getAid() {
			return aid;
		}
		public void setAid(String aid) {
			this.aid = aid;
		}
		public String getTypeid() {
			return typeid;
		}
		public void setTypeid(String typeid) {
			this.typeid = typeid;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getSbutitle() {
			return sbutitle;
		}
		public void setSbutitle(String sbutitle) {
			this.sbutitle = sbutitle;
		}
		public String getPlay() {
			return play;
		}
		public void setPlay(String play) {
			this.play = play;
		}
		public String getReview() {
			return review;
		}
		public void setReview(String review) {
			this.review = review;
		}
		public String getVideo_review() {
			return video_review;
		}
		public void setVideo_review(String video_review) {
			this.video_review = video_review;
		}
		public String getFavorites() {
			return favorites;
		}
		public void setFavorites(String favorites) {
			this.favorites = favorites;
		}
		public String getMid() {
			return mid;
		}
		public void setMid(String mid) {
			this.mid = mid;
		}
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getCreate() {
			return create;
		}
		public void setCreate(String create) {
			this.create = create;
		}
		public String getPic() {
			return pic;
		}
		public void setPic(String pic) {
			this.pic = pic;
		}
		public String getCredit() {
			return credit;
		}
		public void setCredit(String credit) {
			this.credit = credit;
		}
		public String getCoins() {
			return coins;
		}
		public void setCoins(String coins) {
			this.coins = coins;
		}
		public String getDuration() {
			return duration;
		}
		public void setDuration(String duration) {
			this.duration = duration;
		}
		
		
		
		
	}
}
