package com.example.ibplayer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.zip.DataFormatException;

import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.controller.DrawHandler.Callback;
import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.loader.android.BiliDanmakuLoader;
import master.flame.danmaku.danmaku.loader.android.DanmakuLoaderFactory;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.BaseCacheStuffer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.model.android.SpannedCacheStuffer;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.IDataSource;
import master.flame.danmaku.danmaku.parser.android.BiliDanmukuParser;
import master.flame.danmaku.danmaku.util.IOUtils;
import master.flame.danmaku.ui.widget.DanmakuView;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection.Response;

import com.example.ibplayer.util.CompressionTools;
import com.example.ibplayer.util.ThreadManager;
import com.example.ibplayer.view.MediaController;
import com.example.ibplayer.view.VideoView;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;


//主播放器
public class MVideoView extends Activity {
	
	private String path ;
	//播放界面
	private VideoView videoView;
	//进度条
	private MediaController controller;
	//手势识别
	private GestureDetector detector;
	//默认缩放模式
	private int mLayout = VideoView.VIDEO_LAYOUT_ORIGIN;
	//音量管理器
	private AudioManager audioManager;
	//最大音量
	private int maxVolume;
	//音量和亮度
	private FrameLayout fl_volume_bright;
	private ImageView iv_volume_bright;
	private ImageView iv_progress_front;
	private ImageView iv_progress_back;
	//当前音量
	private int mVolume = 3 ;
	private float mBright;
	private BaseDanmakuParser mParser;
	private DanmakuContext mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_videoview);
		videoView = (VideoView) findViewById(R.id.surface_view);
		mDanmakuView = (IDanmakuView) findViewById(R.id.sv_danmaku);
		danmu_url = getIntent().getStringExtra("danmu_url");
		
		path = getIntent().getStringExtra("path");
		if(path!=null){
			videoView.setVideoPath(path);
		}
		
		title = "";
		title = getIntent().getStringExtra("title");
		if (!LibsChecker.checkVitamioLibs(this)) {return;}
		controller = new MediaController(this , title , mDanmakuView);
		
		videoView.setMediaController(controller);
		videoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);
		videoView.setVideoLayout(VideoView.VIDEO_LAYOUT_STRETCH , 0);
		videoView.requestFocus();
		
		findViews();
		videoView.start();  
		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		fl_volume_bright = (FrameLayout) findViewById(R.id.volume_bright_back);
		iv_volume_bright = (ImageView) findViewById(R.id.iv_volume_bright_log);
		iv_progress_front = (ImageView) findViewById(R.id.iv_progress_front);
		iv_progress_back = (ImageView) findViewById(R.id.iv_progress_back);
		
		
		detector = new GestureDetector(this, new MyGestureDetector());
		
	}
	
	Handler handler2 = new Handler(){
		public void handleMessage(Message msg) {
			 mDanmakuView.setCallback(new master.flame.danmaku.controller.DrawHandler.Callback() {
	                @Override
	                public void updateTimer(DanmakuTimer timer) {
	                }

	                @Override
	                public void drawingFinished() {

	                }

	                @Override
	                public void danmakuShown(BaseDanmaku danmaku) {
//	                    Log.d("DFM", "danmakuShown(): text=" + danmaku.text);
	                }

	                @Override
	                public void prepared() {
	                    mDanmakuView.start();
	                }
	            });
	            
	            mDanmakuView.prepare(mParser, mContext);
	            mDanmakuView.showFPS(false);
	            mDanmakuView.enableDanmakuDrawingCache(true);
		};
	};
	
	
	private void findViews() {
		 // 设置最大显示行数
        HashMap<Integer, Integer> maxLinesPair = new HashMap<Integer, Integer>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 5); // 滚动弹幕最大显示3行
        // 设置是否禁止重叠
        HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<Integer, Boolean>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_RL, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP, true);
		
        mContext = DanmakuContext.create();
        mContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3).setDuplicateMergingEnabled(false).setScrollSpeedFactor(1.2f).setScaleTextSize(1.2f)
        .setCacheStuffer(new SpannedCacheStuffer(), mCacheStufferAdapter) // 图文混排使用SpannedCacheStuffer
//        .setCacheStuffer(new BackgroundCacheStuffer())  // 绘制背景使用BackgroundCacheStuffer
        .setMaximumLines(maxLinesPair)
        .preventOverlapping(overlappingEnablePair);
        
        if (mDanmakuView != null) {
        	
        	new Thread(new Runnable() {
				
				@Override
				public void run() {
					mParser = createParser(danmu_url);
					handler2.sendEmptyMessage(0);
				}
			}).start();
        	
           
            
        }
        
        
    }


    private BaseCacheStuffer.Proxy mCacheStufferAdapter = new BaseCacheStuffer.Proxy() {

        private Drawable mDrawable;

        @Override
        public void prepareDrawing(final BaseDanmaku danmaku, boolean fromWorkerThread) {
            if (danmaku.text instanceof Spanned) { // 根据你的条件检查是否需要需要更新弹幕
                // FIXME 这里只是简单启个线程来加载远程url图片，请使用你自己的异步线程池，最好加上你的缓存池
                new Thread() {

                    @Override
                    public void run() {
                        String url = "http://www.bilibili.com/favicon.ico";
                        InputStream inputStream = null;
                        Drawable drawable = mDrawable;
                        if(drawable == null) {
                            try {
                                URLConnection urlConnection = new URL(url).openConnection();
                                inputStream = urlConnection.getInputStream();
                                drawable = BitmapDrawable.createFromStream(inputStream, "bitmap");
                                mDrawable = drawable;
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                IOUtils.closeQuietly(inputStream);
                            }
                        }
                        if (drawable != null) {
                            drawable.setBounds(0, 0, 100, 100);
                            SpannableStringBuilder spannable = createSpannable(drawable);
                            danmaku.text = spannable;
                            if(mDanmakuView != null) {
                                mDanmakuView.invalidateDanmaku(danmaku, false);
                            }
                            return;
                        }
                    }
                }.start();
            }
        }

        @Override
        public void releaseResource(BaseDanmaku danmaku) {
            // TODO 重要:清理含有ImageSpan的text中的一些占用内存的资源 例如drawable
        }
    };
    
    private SpannableStringBuilder createSpannable(Drawable drawable) {
        String text = "bitmap";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        ImageSpan span = new ImageSpan(drawable);//ImageSpan.ALIGN_BOTTOM);
        spannableStringBuilder.setSpan(span, 0, text.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.append("图文混排");
        spannableStringBuilder.setSpan(new BackgroundColorSpan(Color.parseColor("#8A2233B1")), 0, spannableStringBuilder.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return spannableStringBuilder;
    }
    
	private BaseDanmakuParser createParser(String uri) {
		   InputStream stream = null;
	        if(uri==null){
	            return new BaseDanmakuParser() {
	                
	                @Override
	                protected Danmakus parse() {
	                    return new Danmakus();
	                }
	            };
	        }
	        try {
				Response rsp = (Response) Jsoup.connect(uri).execute();
				stream = new ByteArrayInputStream(CompressionTools.decompressXML(rsp.bodyAsBytes()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DataFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
	        
	        ILoader loader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI);

	        try {
	            loader.load(stream);
	        } catch (IllegalDataException e) {
	            e.printStackTrace();
	        }
	        BaseDanmakuParser parser = new BiliDanmukuParser();
	        IDataSource<?> dataSource = loader.getDataSource();
	        parser.load(dataSource);
	        return parser;
	    }
	
	private BaseDanmakuParser createParser(InputStream stream) {

        if (stream == null) {
            return new BaseDanmakuParser() {

                @Override
                protected Danmakus parse() {
                    return new Danmakus();
                }
            };
        }

        ILoader loader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI);

        try {
            loader.load(stream);
        } catch (IllegalDataException e) {
            e.printStackTrace();
        }
        BaseDanmakuParser parser = new BiliDanmukuParser();
        IDataSource<?> dataSource = loader.getDataSource();
        parser.load(dataSource);
        return parser;

    }
	
	class MyGestureDetector extends SimpleOnGestureListener{

		private float dY;
		
		/**滑动事件,更改显示亮度和声音*/
		
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			float oldx = e1.getX();
			float oldy = e1.getY();
			int scrolly = (int) e2.getRawY();
//			System.out.println("oldy="+oldy+"---------------------");
//			System.out.println("scrolly="+scrolly+"----------------------");
			dY = oldy - scrolly; 
			WindowManager windowManager = getWindowManager();
			float width = windowManager.getDefaultDisplay().getWidth();
			float height = windowManager.getDefaultDisplay().getHeight();
			float percentDy = dY/height;
			if(oldx/width <0.6){
				adjustVolume(percentDy);
			}else{
				adjustBright(percentDy);
			}
			return super.onScroll(e1, e2, distanceX, distanceY);
		}
		
		/**
		 *右侧调节亮度 
		 * @param percentDy
		 */
		private void adjustBright(float percentDy) {
			//重要
			if (mBright < 0) {
				mBright = getWindow().getAttributes().screenBrightness;
	            if (mBright <= 0.00f)
	            	mBright = 0.50f;
	            if (mBright < 0.01f)
	            	mBright = 0.01f;
	            iv_volume_bright.setImageResource(R.drawable.video_brightness_bg);
	            fl_volume_bright.setVisibility(View.VISIBLE);
	            }
			
			float newBright =  mBright + percentDy ;
			if(newBright > 1){
				newBright = 1 ;
			}else if(newBright < 0.01 ){
				newBright = (float) 0.01 ; 
			}
			WindowManager.LayoutParams lpa = getWindow().getAttributes();
			lpa.screenBrightness = newBright;
			getWindow().setAttributes(lpa);
			/**
			 * 更改视图
			 */
			ViewGroup.LayoutParams lp = iv_progress_front.getLayoutParams();
			lp.width = (int) (iv_progress_back.getLayoutParams().width*newBright); 
			iv_progress_front.setLayoutParams(lp);
			
		}

		/**
		 * 
		 * 左侧调声音
		 */
		private void adjustVolume(float percentDy) {
			if (mVolume == -1) {
	            mVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
	            if (mVolume < 0)
	                mVolume = 0;
	            iv_volume_bright.setImageResource(R.drawable.video_volumn_bg);
	            fl_volume_bright.setVisibility(View.VISIBLE);
	            }
			int index = (int) (percentDy*maxVolume) + mVolume ;
			if(index > maxVolume){
				index = maxVolume ;
			}else if (index < 0 ){
				index = 0 ;
			}
//			System.out.println("percent"+percentDy +"-------------------------");
//			System.out.println("index"+index+"----------------------------------");
			audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);
			/**
			 * 更改视图
			 */
			ViewGroup.LayoutParams lp = iv_progress_front.getLayoutParams();
			//index/maxVolome别放前面了
			lp.width = iv_progress_back.getLayoutParams().width*index/maxVolume; 
			iv_progress_front.setLayoutParams(lp);
		}
		/** 双击,改变视图 */
		@Override
		public boolean onDoubleTap(MotionEvent e) {
			 if (mLayout == VideoView.VIDEO_LAYOUT_ZOOM)
	                mLayout = VideoView.VIDEO_LAYOUT_ORIGIN;
	            else
	                mLayout++;
	            if (videoView != null)
	                videoView.setVideoLayout(mLayout, 0);
	            return true;
		}
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (detector.onTouchEvent(event)){
			return true;
		}
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_UP:
			endDetector();
			
			break;

		default:
			break;
		}

        return super.onTouchEvent(event);
	}

	/**
	 * 隐藏
	 */
	private void endDetector() {
		mVolume = -1 ;
		mBright = -1;
		handler.removeMessages(0);
		handler.sendEmptyMessageDelayed(0, 500);
		
	}
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			fl_volume_bright.setVisibility(View.GONE);
		};
	};
	private IDanmakuView mDanmakuView;
	private String title;
	private String danmu_url;
//	private IDanmakuView mDanmakuView;
	/**
	 * 屏幕切换
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		if(videoView != null){
			videoView.setVideoLayout(mLayout, 0);
		}
		super.onConfigurationChanged(newConfig);
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
        if (mDanmakuView != null && mDanmakuView.isPrepared() && mDanmakuView.isPaused()) {
            mDanmakuView.resume();
        }
	}
//	
	@Override
	public void onPause() {
		super.onPause();
        if (mDanmakuView != null && mDanmakuView.isPrepared()) {
            mDanmakuView.pause();
        }
	}
//	
	@Override
	public void onDestroy() {
		super.onDestroy();
        if (mDanmakuView != null) {
            // dont forget release!
            mDanmakuView.release();
            mDanmakuView = null;
        }
	}
	
	@Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mDanmakuView != null) {
            // dont forget release!
            mDanmakuView.release();
            mDanmakuView = null;
        }
    }
	
		
		
	
}
