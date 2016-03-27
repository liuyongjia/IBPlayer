package com.example.ibplayer.imp;

import java.util.ArrayList;

import org.json.JSONException;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.ibplayer.R;
import com.example.ibplayer.base.BaseFragment;
import com.example.ibplayer.domain.SpecialInfo;
import com.example.ibplayer.imageLoader.InitImageLoader;
import com.example.ibplayer.listener.MySpecialListener;
import com.example.ibplayer.util.UrlUtil;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class Special extends BaseFragment{
	
	private com.origamilabs.library.views.StaggeredGridView staggeredGridView;
	private SpecialInfo specialInfo;
	private DisplayImageOptions options;
	private ImageLoader imageLoader = ImageLoader.getInstance();

	public Special(Activity activity) {
		super(activity);
		mActivity = activity;
	}

	@Override
	public void initView() {
		view = View.inflate(mActivity, R.layout.special_layout, null);
		
		InitImageLoader.init(R.drawable.ic_splash_default, mActivity);
		
		staggeredGridView = (com.origamilabs.library.views.StaggeredGridView) view.findViewById(R.id.staggeredGridView1);
		 
        url = UrlUtil.URL_SPECIAL;
        
        getDataFromServer(url);
        
		super.initView();
	}
	
	private void getDataFromServer(String url) {
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

	private void parseData(final String result) {
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Gson gson = new Gson();
				specialInfo = gson.fromJson(result, SpecialInfo.class);
				handler.sendEmptyMessage(0);
			}
		}).start();
		
	}
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			MyAapter adapter = new MyAapter();
	        staggeredGridView.setAdapter(adapter);
	        staggeredGridView.setOnItemClickListener(new MySpecialListener(specialInfo, mActivity));
		};
	};
	
	class MyAapter extends BaseAdapter{

		private View mView;
		private ViewHolder holder;

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return specialInfo.getList().size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return specialInfo.getList().get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			BitmapUtils bitmapUtils = new BitmapUtils(mActivity);
			
			if (convertView == null ) {
				mView = View.inflate(mActivity, R.layout.special_item_layout, null);
				holder = new ViewHolder();
				mView.setTag(holder);
			}else {
				mView = convertView ;
				holder = (ViewHolder) mView.getTag();
			}
			
			holder.imageView = (ImageView) mView.findViewById(R.id.item);
			
			imageLoader.displayImage(specialInfo.getList().get(position).getImageurl(), holder.imageView, options);
			return mView;
		}
		
	}
	
	class ViewHolder {
		ImageView imageView;
	}
	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
	}
}
