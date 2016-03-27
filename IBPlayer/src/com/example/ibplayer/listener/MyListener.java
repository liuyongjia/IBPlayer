package com.example.ibplayer.listener;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.ibplayer.VideoInfo;
import com.example.ibplayer.domain.VideoItem;

public class MyListener implements OnItemClickListener{

	private ArrayList<VideoItem> mList;
	Activity mActivity ; 
	public MyListener(ArrayList<VideoItem> list ,Activity activity) {
		super();
		mList = list ;
		mActivity = activity ;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(mActivity, VideoInfo.class);
		intent.putExtra("img", mList.get(position).getPic());
		intent.putExtra("title", mList.get(position).getTitle());
		intent.putExtra("play", mList.get(position).getPlay());
		intent.putExtra("danmuku", mList.get(position).getReview());
		intent.putExtra("up", mList.get(position).getAuthor());
		intent.putExtra("aid", mList.get(position).getAid());
		intent.putExtra("duration", mList.get(position).getDuration());
		mActivity.startActivity(intent);
		
	}
	
}
