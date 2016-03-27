package com.example.ibplayer.listener;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.ibplayer.VideoInfo;
import com.example.ibplayer.domain.BangumiList.Content;
import com.example.ibplayer.domain.SpecialInfo;
import com.example.ibplayer.domain.VideoItem;
import com.example.ibplayer.specialActivity.SpcialActivity;
import com.origamilabs.library.views.StaggeredGridView;

public class MySpecialListener implements OnItemClickListener, com.origamilabs.library.views.StaggeredGridView.OnItemClickListener{

	private SpecialInfo mList;
	Activity mActivity ; 
	public MySpecialListener(SpecialInfo  list ,Activity activity) {
		super();
		mList = list ;
		mActivity = activity ;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
	}

	@Override
	public void onItemClick(StaggeredGridView parent, View view, int position,
			long id) {
		Intent intent = new Intent(mActivity, SpcialActivity.class);
		intent.putExtra("spid", mList.getList().get(position).getSpid());
		intent.putExtra("title", mList.getList().get(0).getTitle());
		mActivity.startActivity(intent);
		
	}
	
}
