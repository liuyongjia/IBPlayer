package com.example.ibplayer.imp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ibplayer.GameInfoActivity;
import com.example.ibplayer.R;
import com.example.ibplayer.base.BaseFragment;
import com.example.ibplayer.domain.GameItemList;
import com.example.ibplayer.imageLoader.InitImageLoader;
import com.example.ibplayer.util.UrlUtil;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;



public class GameCenter extends BaseFragment {
	
	private DisplayImageOptions options;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private ListView listView;
	private GameItemList gameItemList;
	private ArrayList<String> imageList;

	public GameCenter(Activity activity) {
		super(activity);
		mActivity = activity;
	}
	
	@Override
	public void initView() {
		view = View.inflate(mActivity, R.layout.list, null);
		listView = (ListView) view.findViewById(R.id.lv_game);
		
		url = UrlUtil.URL_GAMECENTER;
		InitImageLoader.init(R.drawable.ic_splash_default, mActivity);
		getDataFromServer();
		super.initView();
	}
	
	private void getDataFromServer() {
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
			
		});// TODO Auto-generated method stub
		
	}
	
	private void parseData(final String result) {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				Gson gson = new Gson();
				gameItemList = gson.fromJson(result, GameItemList.class);
				handler.sendEmptyMessage(0);
			}
		}).start();
	}
	
	Handler handler = new Handler(){

		public void handleMessage(android.os.Message msg) {
			GameAdapter adapter = new GameAdapter();
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new GameListener());
		};
	};
	
	class GameListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			imageList = new ArrayList<String>();
			for (int i = 0; i < gameItemList.getList().get(position).getImage_list().size(); i++) {
				imageList.add(gameItemList.getList().get(i).getImage_list().get(i).getSrc());
			}
			Intent intent = new Intent(mActivity, GameInfoActivity.class);
			intent.putExtra("title", gameItemList.getList().get(position).getTitle());
			intent.putExtra("producer", gameItemList.getList().get(position).getProducer());
			intent.putExtra("icon", gameItemList.getList().get(position).getIcon());
			intent.putExtra("description", gameItemList.getList().get(position).getDescription());
			intent.putStringArrayListExtra("image_list", imageList);
			mActivity.startActivity(intent);
		}
		
	}
	
	class GameAdapter extends BaseAdapter{

		private View mView;
		private ViewHoder hoder;

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return gameItemList.getList().size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return gameItemList.getList().get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if(convertView == null){
				mView = View.inflate(mActivity, R.layout.game_list_item, null);
				hoder = new ViewHoder();
				mView.setTag(hoder);
			}else {
				mView = convertView ;
				hoder = (ViewHoder) mView.getTag();
			}
			
			hoder.review = (ImageView) mView.findViewById(R.id.game_item_image);
			hoder.title = (TextView) mView.findViewById(R.id.game_item_title);
			hoder.fatory = (TextView) mView.findViewById(R.id.factory_item_title);
			
			hoder.title.setText(gameItemList.getList().get(position).getTitle());
			hoder.fatory.setText(gameItemList.getList().get(position).getProducer());
			imageLoader.displayImage(gameItemList.getList().get(position).getImageurl(), hoder.review);
			return mView;
		}
		
	}
	
	class ViewHoder {
		ImageView review ;
		TextView title ;
		TextView fatory ;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
	}

	

	

	
}
