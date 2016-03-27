package com.example.ibplayer.listener;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.ibplayer.VideoInfo;
import com.example.ibplayer.domain.BangumiList.Content;
import com.example.ibplayer.domain.VideoItem;

public class MyDivListener implements OnItemClickListener{

	private ArrayList<Content> mList;
	Activity mActivity ; 
	public MyDivListener(ArrayList<Content> list ,Activity activity) {
		super();
		mList = list ;
		mActivity = activity ;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(mActivity, VideoInfo.class);
		intent.putExtra("img", mList.get(position - 1).getPic());
		intent.putExtra("title", mList.get(position - 1).getTitle());
		intent.putExtra("play", mList.get(position - 1).getPlay());
		intent.putExtra("danmuku", mList.get(position - 1).getReview());
		intent.putExtra("up", mList.get(position - 1).getAuthor());
		intent.putExtra("aid", mList.get(position - 1).getAid());
		intent.putExtra("duration", mList.get(position - 1).getDuration());
		mActivity.startActivity(intent);
		
	}
	
}
