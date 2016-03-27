package com.example.ibplayer.imp;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.ibplayer.R;
import com.example.ibplayer.base.BaseFragment;

public class SpecialBangumi extends BaseFragment {
	
	public SpecialBangumi(Activity activity) {
		super(activity);
		mActivity = activity;
	}
	
	@Override
	public void initView() {
		view = View.inflate(mActivity, R.layout.warn_layout, null);
		super.initView();
	}
	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
	}

	

	

	
}
