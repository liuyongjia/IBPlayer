package com.example.ibplayer;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EncodingUtils;
import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.example.ibplayer.domain.VideoInfoItem;
import com.example.ibplayer.domain.VideoPageItem;
import com.example.ibplayer.domain.VideoUrl;
import com.example.ibplayer.util.JsonUtil;
import com.example.ibplayer.util.UrlUtil;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class VideoInfo extends Activity {

	private String img;
	private String title;
	private String play;
	private String danmuku;
	private String up;
	private String aid;
	private Button button;
	private VideoInfoItem infoItem;
	private ListView listView;
	private int time;
	private String duration;
	private ArrayList<String> url_count;
	private int listCount;
	private ArrayList<VideoPageItem> videoPageList;
	private TextView videoNumTest;
	private ArrayList<String> urlList;
	private TextView tv_label;
	private ArrayList<String> videoInfoList = new ArrayList<String>();
	private String url;
	private String url_page;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_videoinfo);
		
		Bundle bundle = new Bundle();
		bundle = getIntent().getExtras();
		img = bundle.getString("img");
		title = bundle.getString("title");
		play = bundle.getString("play");
		danmuku = bundle.getString("danmuku");
		up = bundle.getString("up");
		aid = bundle.getString("aid");
		duration = bundle.getString("duration");
		listView = (ListView) findViewById(R.id.video_info_list);
		videoNumTest = (TextView) findViewById(R.id.videoNumTextView);
		button = (Button) findViewById(R.id.playButton);
		tv_label = (TextView) findViewById(R.id.labelTextView);
		url = "http://www.bilibili.com/m/html5?aid=" + aid;
		url_page = UrlUtil.URL_PAGE_DIV + aid;
//		new VideoInfoTask().execute();
		initListButton();
		
		
		
		initView();
		getDataFromServer(url);
		getPageDataFromServer(url_page);
		
	}
		
		private class VideoInfoTask extends AsyncTask<String, Void, Integer> {
			String label;

			@Override
			protected Integer doInBackground(String... arg0) {
				String html = "http://www.bilibili.com/mobile/video/av"+aid+".html";
				String listHTML = getHtmlString(html);
				
				Document listDoc = Jsoup.parse(listHTML);
				Elements listElements = listDoc.getElementsByClass("li-wrap-content");
				for (int i = 0; i < listElements.size(); i++) {
					videoInfoList.add(listElements.get(i).text());
				}
				Elements labelElements = listDoc.select("[name=keywords]");
				label = labelElements.attr("content");

				
				return null;
			}
			
			@Override
			protected void onPostExecute(Integer result) {
				// TODO Auto-generated method stubs
				super.onPostExecute(result);
				if(label != null){
					label = label.substring(20);
					tv_label.setText("标签："+label);
				}
				initListButton();
				
				initView();
				
				getDataFromServer(url);
				getPageDataFromServer(url_page);
			}
		}
	
	public static String getHtmlString(String urlString) {  
	    try {
	        URL url = new URL(urlString);  
	        URLConnection ucon = url.openConnection();  
	        InputStream instr = ucon.getInputStream();  
	        BufferedInputStream bis = new BufferedInputStream(instr); 
	        ByteArrayBuffer baf = new ByteArrayBuffer(500);  
	        int current = 0;  
	        while ((current = bis.read()) != -1) {  
	            baf.append((byte) current);  
	        }  
	        return EncodingUtils.getString(baf.toByteArray(), "utf-8");  
	    } catch (Exception e) {
	    	
	    	Log.d("win","lllll"+e.toString());
	        return "";  
	    }  
	} 

	private void getPageDataFromServer(String url_page) {
		HttpUtils utils = new HttpUtils();
		
		utils.send(HttpMethod.GET, url_page , new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				
				try {
					parsePageData(result);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}


			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(VideoInfo.this, "连接异常", 0);
				
			}
		});
	}

	protected void parsePageData(String result) throws JSONException {
		videoPageList = JsonUtil.getVideoPageList(result);
		
		ListAdapter listAdapter = new ListAdapter();
		listView.setAdapter(listAdapter);
		videoNumTest.setText("共" + videoPageList.size() + "集");
		
		initVideoUrlList();
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				startVideo(urlList.get(position) ,position);
				
			}

		});
		
		
	}
	
	private void startVideo(String url ,  final int position) {
		
		HttpUtils utils = new HttpUtils();
		
		utils.send(HttpMethod.GET, url , new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				int mPosition = position ;
				
				getUrlAndStart(result , mPosition);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(VideoInfo.this, "连接异常", 0);
				
			}
		});
		
	}
	
	private void getUrlAndStart(String result , int position) {
		Gson gson = new Gson();
		VideoUrl videoUrl = gson.fromJson(result, VideoUrl.class);
		System.out.println(videoUrl);
		
		Intent intent = new Intent(VideoInfo.this, MVideoView.class);
		intent.putExtra("path", videoUrl.durl.get(0).url);
		
		if (videoPageList.size() == 1) {
			intent.putExtra("title", title);
		}else {
			intent.putExtra("title", videoPageList.get(position).getPagename());
		}
		
		String danmu = UrlUtil.URL_DANMU_1 + videoPageList.get(position).getCid() + UrlUtil.URL_DANMU_2;
		intent.putExtra("danmu_url", danmu);
		startActivity(intent);
	}

	private void initVideoUrlList() {
		urlList = new ArrayList<String>(); 
		for (int i = 0; i < videoPageList.size(); i++) {
			String url_item = UrlUtil.URL_PAGE_ITEM_1 + aid + UrlUtil.URL_PAGE_ITEM_2 + videoPageList.get(i).getCid() + UrlUtil.URL_PAGE_ITEM_3 ;
			urlList.add(url_item);
		}
	}

	private void initListButton() {
		
		listCount = getCount();
		
		url_count = new ArrayList<String>();
		for (int i = 0; i < listCount; i++) {
			String text = "第" + (i+1) + "集" ;
			url_count.add(text);
		}
		
	}

	private int getCount() {
		
		int count = 1;
		
		if(duration != null){
			String[] alltime = duration.split(":");
			try {
				time = Integer.parseInt(alltime[0]);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			count = time/24 ;
			
		}
		return count;
	}

	private void getDataFromServer(String url) {
		HttpUtils utils = new HttpUtils();
		
		utils.send(HttpMethod.GET, url , new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				
				parseData(result);
			}


			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(VideoInfo.this, "连接异常", 0);
				
			}
		});
		
	}
	
	private void parseData(String result) {
		Gson gson = new Gson();
		infoItem = gson.fromJson(result, VideoInfoItem.class);
		button.setText("点击播放");
		button.setOnClickListener(new MyListener());
	}

	class MyListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(VideoInfo.this, MVideoView.class);
			intent.putExtra("path", infoItem.getSrc());
			intent.putExtra("title", title);
			intent.putExtra("danmu_url", infoItem.getCid());
			startActivity(intent);
		}
		
	}
	
	
	private void initView() {
		
		BitmapUtils utils = new BitmapUtils(VideoInfo.this);
		ImageView log = (ImageView) findViewById(R.id.imageView);
		TextView tv_title = (TextView) findViewById(R.id.titleTextView);
		TextView tv_play = (TextView) findViewById(R.id.playTextView);
		TextView tv_danmuku = (TextView) findViewById(R.id.video_reviewTextView);
		TextView tv_up = (TextView) findViewById(R.id.authorTextView);
		
		utils.display(log, img);
		tv_title.setText(title);
		tv_play.setText("播放：" + play);
		tv_danmuku.setText("弹幕:" + danmuku);
		tv_up.setText("up:" + up);
		
	}
	
	
	class ListAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return videoPageList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return videoPageList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(VideoInfo.this, R.layout.vidio_list_count, null);
			TextView textView = (TextView) view.findViewById(R.id.list_item);
			
			if (videoPageList.size() == 1) {
				textView.setText(title);
			}else {
				textView.setText(videoPageList.get(position).getPagename());
			}
			
			
			return view;
		}
		
	}
}
