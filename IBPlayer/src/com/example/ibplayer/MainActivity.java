package com.example.ibplayer;

import java.util.ArrayList;

import com.example.ibplayer.imp.HomePage;
import com.example.ibplayer.page.BangumiPageContainer;
import com.example.ibplayer.page.PageContainer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	private ViewPager pager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initData();
		initView();
	}

	private void initData() {
		
	}
	
	private void initView() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.replace(R.id.fl_main, new PageContainer(MainActivity.this));
		transaction.commit();
	}
	
		
}
