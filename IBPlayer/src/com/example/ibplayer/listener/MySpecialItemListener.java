package com.example.ibplayer.listener;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.ibplayer.VideoInfo;
import com.example.ibplayer.domain.SpecialRelInfo;
import com.example.ibplayer.domain.VideoItem;

public class MySpecialItemListener implements OnItemClickListener{

	SpecialRelInfo mListinfo ;
	Activity mActivity ; 
	public MySpecialItemListener(SpecialRelInfo listInfo ,Activity activity) {
		super();
		mListinfo = listInfo ;
		mActivity = activity ;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(mActivity, VideoInfo.class);
		intent.putExtra("img", mListinfo.getList().get(position).getCover());
		intent.putExtra("title", mListinfo.getList().get(position).getTitle());
		intent.putExtra("play", mListinfo.getList().get(position).getClick());
		intent.putExtra("danmuku", "");
		intent.putExtra("up", "");
		intent.putExtra("aid", mListinfo.getList().get(position).getAid());
		intent.putExtra("duration", "");
		mActivity.startActivity(intent);
		
	}
	
}
