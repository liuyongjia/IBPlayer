package com.example.ibplayer.base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseFragment  {

	public Activity mActivity ;
	public View view; 
	public String url;
	
	public BaseFragment(Activity activity) {
		super();
		mActivity = activity ;
		initView();
		initData();
	}
	
	public void initView() {
		// TODO Auto-generated method stub
		
	}
	public void initData() {
		// TODO Auto-generated method stub
		
	}


	
	
	
}
