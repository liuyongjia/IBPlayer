package com.example.ibplayer.imageLoader;

import android.app.Activity;

import com.example.ibplayer.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class InitImageLoader {
	
	private static ImageLoader imageLoader = ImageLoader.getInstance();// 得到图片加载器
	private static DisplayImageOptions options;
	
	public static void init(int res , Activity mActivity){
		
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

}
