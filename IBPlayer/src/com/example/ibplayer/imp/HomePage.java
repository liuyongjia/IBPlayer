package com.example.ibplayer.imp;

import java.util.ArrayList;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.example.ibplayer.R;
import com.example.ibplayer.VideoInfo;
import com.example.ibplayer.adapter.HomPageAdapter;
import com.example.ibplayer.base.BaseFragment;
import com.example.ibplayer.domain.HomePageBean;
import com.example.ibplayer.domain.HomePageBean.Content;
import com.example.ibplayer.domain.VideoItem;
import com.example.ibplayer.listener.MyListener;
import com.example.ibplayer.util.JsonUtil;
import com.example.ibplayer.util.UrlUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class HomePage extends BaseFragment {

	private GridView bangumi;
	private GridView douga;
	private GridView kichiku;
	private GridView teleplay;
	private GridView music;
	private GridView game;
	private GridView ent;
	private GridView movie;
	private GridView technology;
	private GridView dance;
	private GridView fashion;
	private String mResult;
	public HomePage(Activity activity) {
		super(activity);
		mActivity = activity;
	}

	@Override
	public void initView() {
		super.initView();
		view = View.inflate(mActivity, R.layout.homepage_layout, null);
		
		bangumi = (GridView) view.findViewById(R.id.gv_bangumi);
		douga = (GridView) view.findViewById(R.id.gv_douga);
		kichiku = (GridView) view.findViewById(R.id.gv_kichiku);
		teleplay = (GridView) view.findViewById(R.id.gv_teleplay);
		music = (GridView) view.findViewById(R.id.gv_music);
		game = (GridView) view.findViewById(R.id.gv_game);
		ent = (GridView) view.findViewById(R.id.gv_ent);
		movie = (GridView) view.findViewById(R.id.gv_movie);
		technology = (GridView) view.findViewById(R.id.gv_technology);
		dance = (GridView) view.findViewById(R.id.gv_dance);
		fashion = (GridView) view.findViewById(R.id.gv_fashion);
		
	}
	
	@Override
	public void initData() {
		
		super.initData();
		
		url = UrlUtil.HOST+UrlUtil.HOMEPAGE;
		
		getdataFromServer(url);
		
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

	ArrayList<VideoItem> bangumiList ;
	ArrayList<VideoItem> dougaList ;
	ArrayList<VideoItem> danceList ;
	ArrayList<VideoItem> kichikuList ;
	ArrayList<VideoItem> teleplayList ;
	ArrayList<VideoItem> musicList ;
	ArrayList<VideoItem> gameList ;
	ArrayList<VideoItem> entList ;
	ArrayList<VideoItem> movieList ;
	ArrayList<VideoItem> technologyList ;
	ArrayList<VideoItem> fashionList ;
	
	private void parseData(String result) throws JSONException {
		
		mResult = result;
		
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				
				try {
					bangumiList = JsonUtil.getVideoInfo(mResult , "bangumi");
					dougaList = JsonUtil.getVideoInfo(mResult, "douga");
					danceList = JsonUtil.getVideoInfo(mResult, "dance");
					kichikuList = JsonUtil.getVideoInfo(mResult , "kichiku");
					teleplayList = JsonUtil.getVideoInfo(mResult, "teleplay");
					musicList = JsonUtil.getVideoInfo(mResult, "music");
					gameList = JsonUtil.getVideoInfo(mResult , "game");
					entList = JsonUtil.getVideoInfo(mResult, "ent");
					movieList = JsonUtil.getVideoInfo(mResult, "movie");
					technologyList = JsonUtil.getVideoInfo(mResult, "technology");
					fashionList = JsonUtil.getVideoInfo(mResult, "fashion");
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				handler.sendEmptyMessage(0);
			}
		}){
			
		}.start();
	}
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			HomPageAdapter bangumiAdapter = new HomPageAdapter(mActivity, bangumiList);
			bangumi.setAdapter(bangumiAdapter);
			bangumi.setOnItemClickListener(new MyListener(bangumiList, mActivity));
			
			HomPageAdapter dougaAdapter = new HomPageAdapter(mActivity, dougaList);
			douga.setAdapter(dougaAdapter);
			douga.setOnItemClickListener(new MyListener(dougaList, mActivity));
			
			HomPageAdapter danceAdapter = new HomPageAdapter(mActivity, danceList);
			dance.setAdapter(danceAdapter);
			dance.setOnItemClickListener(new MyListener(danceList, mActivity));
			
			HomPageAdapter kichikuAdapter = new HomPageAdapter(mActivity, kichikuList);
			kichiku.setAdapter(kichikuAdapter);
			kichiku.setOnItemClickListener(new MyListener(kichikuList, mActivity));
			
			HomPageAdapter teleplayAdapter = new HomPageAdapter(mActivity, teleplayList);
			teleplay.setAdapter(teleplayAdapter);
			teleplay.setOnItemClickListener(new MyListener(teleplayList, mActivity));
			
			HomPageAdapter musicAdapter = new HomPageAdapter(mActivity, musicList);
			music.setAdapter(musicAdapter);
			music.setOnItemClickListener(new MyListener(musicList, mActivity));
			
			HomPageAdapter gameAdapter = new HomPageAdapter(mActivity, gameList);
			game.setAdapter(gameAdapter);
			game.setOnItemClickListener(new MyListener(gameList, mActivity));
			
			HomPageAdapter entAdapter = new HomPageAdapter(mActivity, entList);
			ent.setAdapter(entAdapter);
			ent.setOnItemClickListener(new MyListener(entList, mActivity));
			
			HomPageAdapter movieAdapter = new HomPageAdapter(mActivity, movieList);
			movie.setAdapter(movieAdapter);
			movie.setOnItemClickListener(new MyListener(movieList, mActivity));
			
			HomPageAdapter technologyAdapter = new HomPageAdapter(mActivity, technologyList);
			technology.setAdapter(technologyAdapter);
			technology.setOnItemClickListener(new MyListener(technologyList, mActivity));
			
			HomPageAdapter fashionAdapter = new HomPageAdapter(mActivity, fashionList);
			fashion.setAdapter(fashionAdapter);
			fashion.setOnItemClickListener(new MyListener(fashionList, mActivity));
		};
	};
	
	
	
}
