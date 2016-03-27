package com.example.ibplayer.imp;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ibplayer.R;
import com.example.ibplayer.base.BaseFragment;
import com.example.ibplayer.domain.SpecialRelInfo;
import com.example.ibplayer.listener.MySpecialItemListener;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class SpecialRel extends BaseFragment {

	public String post ;
	private ListView vedioList;
	
	SpecialRelInfo listinfo ;
	private RelativeLayout rl_warn;
	
	public SpecialRel(Activity activity ,String url) {
		super(activity);
		mActivity = activity;
		initData(url);
	}

	@Override
	public void initView() {
		super.initView();
		view = View.inflate(mActivity, R.layout.special_rel, null);
		vedioList = (ListView) view.findViewById(R.id.lv_sp_rel);
		rl_warn = (RelativeLayout) view.findViewById(R.id.rl_warn);
	}
	
	
	public void initData(String url){
		
		getDataFromSerVer(url);
	}
	
	private void getDataFromSerVer(String url) {
		
		HttpUtils utils = new HttpUtils();
		utils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				parseData(result);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
	
	private void parseData(String result) {
		Gson gson = new Gson();
		listinfo = gson.fromJson(result, SpecialRelInfo.class);
		
		if(listinfo.getList().size() == 0){
			rl_warn.setVisibility(View.VISIBLE);
		}
		MyAdapter adapter =  new MyAdapter();
		vedioList.setAdapter(adapter);
		vedioList.setOnItemClickListener(new MySpecialItemListener(listinfo, mActivity));
	}

	class MyAdapter extends BaseAdapter{

		private View mView;
		private ViewHoder hoder;

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listinfo.getList().size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return listinfo.getList().get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			BitmapUtils utils = new BitmapUtils(mActivity);
			if (convertView == null) {
				mView = View.inflate(mActivity, R.layout.sp_video_list_item, null);
				hoder = new ViewHoder();
				mView.setTag(hoder);
			}else {
				mView = convertView ;
				hoder = (ViewHoder) mView.getTag();
			}
			hoder.imageView = (ImageView) mView.findViewById(R.id.sp_list_item_image);
			hoder.title = (TextView) mView.findViewById(R.id.sp_list_item_title);
			hoder.play = (TextView) mView.findViewById(R.id.sp_TextView_bofang);
			
			utils.display(hoder.imageView, listinfo.getList().get(position).getCover());
			hoder.title.setText(listinfo.getList().get(position).getTitle());
			hoder.play.setText(listinfo.getList().get(position).getClick());
			return mView;
		}
		
	}

	class ViewHoder {
		ImageView imageView ;
		TextView title  ;
		TextView play  ;
	}
	@Override
	public void initData() {
		
		super.initData();
	}

	
	
	
}
