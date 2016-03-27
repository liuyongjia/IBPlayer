package com.example.ibplayer.specialActivity;

import com.example.ibplayer.R;
import com.example.ibplayer.page.AnimePageContainer;
import com.example.ibplayer.page.BangumiPageContainer;
import com.example.ibplayer.page.PageContainer;
import com.example.ibplayer.page.SpecialPageContainer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class SpcialActivity extends FragmentActivity{
	
	private String spid;
	private String title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_divide_detail);
		
		spid = getIntent().getStringExtra("spid");
		title = getIntent().getStringExtra("title");
		initData();
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		
	}

	private void initData() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.replace(R.id.fl_main, new SpecialPageContainer(SpcialActivity.this , spid ,title));
		transaction.commit();
		
	}
}
