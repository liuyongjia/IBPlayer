package com.example.ibplayer;

import java.util.ArrayList;

import com.example.ibplayer.imageLoader.InitImageLoader;
import com.example.ibplayer.view.HorizontalListView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GameInfoActivity extends Activity {
	
	private TextView tv_title;
	private TextView tv_producter;
	private TextView tv_description;
	private String title;
	private String producer;
	private String url_icon;
	private String description;
	private ArrayList<String> imageList;
	private HorizontalListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_item_layout);
		InitImageLoader.init(R.drawable.ic_splash_default, GameInfoActivity.this);
		title = getIntent().getStringExtra("title");
		producer = getIntent().getStringExtra("producer");
		url_icon = getIntent().getStringExtra("icon");
		description = getIntent().getStringExtra("description");
		imageList = getIntent().getStringArrayListExtra("image_list");
		
		System.out.println("44"+imageList.size());
		tv_title = (TextView) findViewById(R.id.tv_name);
		tv_producter = (TextView) findViewById(R.id.tv_factory);
		tv_description = (TextView) findViewById(R.id.tv_des);
		listView = (HorizontalListView) findViewById(R.id.hs);
		initView();
	}

	private void initView() {
		tv_title.setText(title);
		tv_description.setText(description);
		tv_producter.setText(producer);
		Madapter adapter = new Madapter();
		listView.setAdapter(adapter);
	}

	class Madapter extends BaseAdapter{
		
		BitmapUtils utils = new BitmapUtils(GameInfoActivity.this);

		private ViewHolder holder;
		private View mView;

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imageList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return imageList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null){
				mView = View.inflate(GameInfoActivity.this, R.layout.horizonlist_layout, null);
				holder = new ViewHolder();
				mView.setTag(holder);
			}else {
				mView = convertView;
				holder = (ViewHolder) mView.getTag();
			}
			
			holder.image = (ImageView) mView.findViewById(R.id.iv_item);
			utils.display(holder.image, imageList.get(position));
			return mView;
		}
		
	}
	
	class ViewHolder{
		ImageView image ;
	}
}
