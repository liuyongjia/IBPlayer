package com.example.ibplayer.util;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.ibplayer.domain.VideoInfoItem;
import com.example.ibplayer.domain.VideoItem;
import com.example.ibplayer.domain.VideoPageItem;
import com.google.gson.JsonArray;

public class JsonUtil {

	public final static ArrayList<VideoItem > getVideoInfo(String jsonString , String category) throws JSONException{
		JSONObject object = new JSONObject(jsonString);
		JSONObject bangumiarray= object.getJSONObject(category);
		ArrayList<VideoItem> list = new ArrayList<VideoItem>();
		for (int i = 0; i < bangumiarray.length(); i++) {
			com.example.ibplayer.domain.VideoItem item = new com.example.ibplayer.domain.VideoItem();
			
			item.setAid(bangumiarray.getJSONObject(i+"").getString("aid").toString());
			item.setTypeid(bangumiarray.getJSONObject(i+"").getString("typeid").toString());
			item.setTitle(bangumiarray.getJSONObject(i+"").getString("title").toString());
			item.setSbutitle(bangumiarray.getJSONObject(i+"").optString("sbutitle").toString());
			item.setPlay(bangumiarray.getJSONObject(i+"").getString("play").toString());
			item.setReview(bangumiarray.getJSONObject(i+"").getString("review").toString());
			item.setVideo_review(bangumiarray.getJSONObject(i+"").getString("video_review").toString());
			item.setFavorites(bangumiarray.getJSONObject(i+"").getString("favorites").toString());
			item.setMid(bangumiarray.getJSONObject(i+"").getString("mid").toString());
			item.setAuthor(bangumiarray.getJSONObject(i+"").getString("author").toString());
			item.setDescription(bangumiarray.getJSONObject(i+"").getString("description").toString());
			item.setCreate(bangumiarray.getJSONObject(i+"").getString("create").toString());
			item.setPic(bangumiarray.getJSONObject(i+"").getString("pic").toString());
			item.setCredit(bangumiarray.getJSONObject(i+"").getString("credit").toString());
			item.setCoins(bangumiarray.getJSONObject(i+"").getString("coins").toString());
			item.setDuration(bangumiarray.getJSONObject(i+"").getString("duration").toString());
			list.add(item);
		}
		return list;
	}
	
	public final static VideoInfoItem getVideoInfoItem(String json) throws JSONException{
		
		JSONObject object = new JSONObject(json);
		VideoInfoItem infoItem = new VideoInfoItem();
		
		infoItem.setCid(object.getJSONObject("cid").toString());
		infoItem.setCid(object.getJSONObject("img").toString());
		infoItem.setCid(object.getJSONObject("src").toString());
		return infoItem ;
	}
	
public final static ArrayList<VideoPageItem> getVideoPageList(String json) throws JSONException{
		
		JSONArray jsonArray = new JSONArray(json);
		ArrayList<VideoPageItem> videoPageList = new ArrayList<VideoPageItem>();
		
		for (int i = 0; i < jsonArray.length(); i++) {
			VideoPageItem pageItem = new VideoPageItem();
			pageItem.setCid(jsonArray.getJSONObject(i).getString("cid").toString());
			pageItem.setPage(jsonArray.getJSONObject(i).getString("page").toString());
			pageItem.setPagename(jsonArray.getJSONObject(i).getString("pagename").toString());
			videoPageList.add(pageItem);
		}
		
		return videoPageList ;
	}
	
}
