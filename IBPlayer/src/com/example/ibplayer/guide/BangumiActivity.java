package com.example.ibplayer.guide;

import com.example.ibplayer.R;
import com.example.ibplayer.page.BangumiPageContainer;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class BangumiActivity extends FragmentActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_divide_detail);
		initData();
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		
	}

	private void initData() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.replace(R.id.fl_main, new BangumiPageContainer(BangumiActivity.this));
		transaction.commit();
		
	}
}
