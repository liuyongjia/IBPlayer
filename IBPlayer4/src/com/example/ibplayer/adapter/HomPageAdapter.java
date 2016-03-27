package com.example.ibplayer.adapter;

import java.util.ArrayList;

import com.example.ibplayer.R;
import com.example.ibplayer.domain.HomePageBean;
import com.example.ibplayer.domain.HomePageBean.Content;
import com.example.ibplayer.domain.VideoItem;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomPageAdapter extends BaseAdapter {
	
	private ArrayList<VideoItem> list;
	private HomePageBean bean;
	private Activity mActivity;
	int numble;
	private BitmapUtils utils;
	private View view;
	private DisplayImageOptions options;
	private ImageLoader imageLoader = ImageLoader.getInstance();// 得到图片加载器

	public HomPageAdapter(Activity activity , ArrayList<VideoItem> bean ) {
		super();
		mActivity = activity ;
		list = bean ;
		utils = new BitmapUtils(activity);
		
		// 图片加载器初始化
		imageLoader.init(ImageLoaderConfiguration.createDefault(mActivity));
		// 使用DisplayImageOptions.Builder()创建DisplayImageOptions
		options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.image_demo) // 设置图片下载期间显示的图片
		.showImageForEmptyUri(R.drawable.image_demo) // 设置图片Uri为空或是错误的时候显示的图片
		.showImageOnFail(R.drawable.image_demo) // 设置图片加载或解码过程中发生错误显示的图片
		.cacheInMemory() // 设置下载的图片是否缓存在内存中
		.cacheOnDisc() // 设置下载的图片是否缓存在SD卡中
		.displayer(new RoundedBitmapDisplayer(1)) // 设置成圆角图片
		.build(); // 创建配置过得DisplayImageOption对象
	}
	

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = new ViewHolder();
		if(convertView == null){
			convertView = View.inflate(mActivity, R.layout.homepage_item, null);
			holder.bofang = (TextView) convertView.findViewById(R.id.BangumiImageView_baofang);
			holder.danmu = (TextView) convertView.findViewById(R.id.BangumiImageView_danmugu);
			holder.itemImage = (ImageView) convertView.findViewById(R.id.list_item_image);
			holder.title = (TextView) convertView.findViewById(R.id.list_item_title);
			
			if(list.get(position) != null){
				imageLoader.displayImage(list.get(position).getPic(), holder.itemImage, options);
				holder.bofang.setText(list.get(position).getVideo_review());
				holder.danmu.setText(list.get(position).getReview());
				holder.title.setText(list.get(position).getTitle());
			}
			
			
			convertView.setTag(holder);
			
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}
	
	class ViewHolder {
		TextView bofang;
		TextView danmu ;
		ImageView itemImage ;
		TextView title ;
	}

}
