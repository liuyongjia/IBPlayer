package com.example.ibplayer.imp;

import java.util.ArrayList;

import org.json.JSONException;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ibplayer.R;
import com.example.ibplayer.base.BaseFragment;
import com.example.ibplayer.domain.BangumiList;
import com.example.ibplayer.imageLoader.InitImageLoader;
import com.example.ibplayer.listener.MyDivListener;
import com.example.ibplayer.xListView.XListView;
import com.example.ibplayer.xListView.XListView.IXListViewListener;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class DivBAanguimi extends BaseFragment {

	private XListView vedioList;
	public String post ;
	private ArrayList<com.example.ibplayer.domain.BangumiList.Content> list;
	private MyAdapter adapter;
	private DisplayImageOptions options;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	
	public DivBAanguimi(Activity activity ,String posturl) {
		super(activity);
		mActivity = activity;
		
		post = posturl;
		
		initData(post);
	}

	@Override
	public void initView() {
		super.initView();
		view = View.inflate(mActivity, R.layout.bangumi_list, null);
		InitImageLoader.init(R.drawable.ic_splash_default, mActivity);
		vedioList = (XListView) view.findViewById(R.id.lv_video_list);
		vedioList.setPullRefreshEnable(true);
		vedioList.setPullLoadEnable(true);
	}
	
	
	public void initData(String post){
		url = post;
		getdataFromServer(url);
		
		
	}
	
	@Override
	public void initData() {
		
		super.initData();
	}

	private void getdataFromServer(String url) {
		HttpUtils utils = new HttpUtils();
		utils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				try {
					parseData(result);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}


			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}

	private void parseData(final String result) throws JSONException {
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Gson gson = new Gson();
				BangumiList bangumiList = gson.fromJson(result, BangumiList.class);
				list = bangumiList.list ;
				handler.sendEmptyMessage(0);
			}
		}).start();
		
		
	}
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			adapter = new MyAdapter();
			vedioList.setAdapter(adapter);
			vedioList.setOnItemClickListener(new MyDivListener(list, mActivity));
			vedioList.setXListViewListener(new MyListener());
		};
	};
	
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position );
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position ;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			BitmapUtils utils = new BitmapUtils(mActivity);
			View view = null ;
			ViewHolder holder = new ViewHolder();
			if(convertView == null){
				view = View.inflate(mActivity, R.layout.video_list_item, null);
				holder.img_review = (ImageView) view.findViewById(R.id.list_item_image);
				holder.tv_title = (TextView) view.findViewById(R.id.list_item_title);
				holder.tv_up = (TextView) view.findViewById(R.id.TextView_up);
				holder.tv_bofang = (TextView) view.findViewById(R.id.TextView_bofang);
				view.setTag(holder);
			}else {
				view = convertView ;
				holder = (ViewHolder) view.getTag();
			}
			
			utils.display(holder.img_review, list.get(position ).pic);
			holder.tv_title.setText(list.get(position ).title);
			holder.tv_up.setText("up:"+list.get(position ).author);
			holder.tv_bofang.setText("播放"+list.get(position).play);
			return view;
		}
		
	}
	
	class ViewHolder{
		ImageView img_review ;
		TextView tv_title  ;
		TextView tv_up  ;
		TextView tv_bofang  ;
	}
	
	class MyListener implements IXListViewListener{

		
		@Override
		public void onRefresh() {
			getdataFromServer(url);
			adapter.notifyDataSetChanged();
			vedioList.stopRefresh();
		}

		@Override
		public void onLoadMore() {
			getMoredataFromServer(url);
			
		}
		
	}
	
	private void parseMoreData(String result) {
		Gson gson = new Gson();
		BangumiList bangumiList = gson.fromJson(result, BangumiList.class);
		list.addAll(bangumiList.list);
		adapter.notifyDataSetChanged();
		
		
	}
	
	private void getMoredataFromServer(String url) {
		HttpUtils utils = new HttpUtils();
		utils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				parseMoreData(result);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
	
}
