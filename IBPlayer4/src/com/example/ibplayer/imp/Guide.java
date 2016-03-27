package com.example.ibplayer.imp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ibplayer.R;
import com.example.ibplayer.base.BaseFragment;
import com.example.ibplayer.guide.AnimeActivity;
import com.example.ibplayer.guide.BangumiActivity;
import com.example.ibplayer.guide.GameActivity;
import com.example.ibplayer.guide.MovieActivity;
import com.example.ibplayer.guide.MusicActivity;
import com.example.ibplayer.guide.RankActivity;
import com.example.ibplayer.guide.TechnologyActivity;
import com.example.ibplayer.guide.YuleActivity;

public class Guide extends BaseFragment {

	private GridView gridView;
	
	private static String [] names = {
		"番剧","动画","音乐",
		"游戏","科技","娱乐",
		"电影","排行"
	};
	
	private static int[] ids = {
		R.mipmap.ic_category_t13,R.mipmap.ic_category_t1,R.mipmap.ic_category_t3,
		R.mipmap.ic_category_t4,R.mipmap.ic_category_t36,R.mipmap.ic_category_t5,
		R.mipmap.ic_category_t23,R.mipmap.ic_cate_ranks
	};

	public Guide(Activity activity) {
		super(activity);
		mActivity = activity;
	}
	

	@Override
	public void initView() {
		super.initView();
		view = View.inflate(mActivity, R.layout.fragment_subarea, null);
		
		gridView = (GridView) view.findViewById(R.id.AreaGridView);
		
		MyAdapter myAdapter = new MyAdapter();
		gridView.setAdapter(myAdapter);
		gridView.setOnItemClickListener(new MyListener());
	}
	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		
	}
	
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return names.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return names[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = View.inflate(mActivity, R.layout.area_item, null);
			
			ImageView imageView = (ImageView) convertView.findViewById(R.id.areaImageView);
			TextView textView = (TextView) convertView.findViewById(R.id.areaTextView);
			
			imageView.setImageResource(ids[position]);
			textView.setText(names[position]);
			
			return convertView;
		}
		
	}
	
	class MyListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			if(position == 0){
				Intent intent = new Intent(mActivity, BangumiActivity.class);
				mActivity.startActivity(intent);
			}else if (position == 1) {
				Intent intent = new Intent(mActivity, AnimeActivity.class);
				mActivity.startActivity(intent);
			}else if (position == 2) {
				Intent intent = new Intent(mActivity, MusicActivity.class);
				mActivity.startActivity(intent);
			}else if (position == 3) {
				Intent intent = new Intent(mActivity, GameActivity.class);
				mActivity.startActivity(intent);
			}else if (position == 4) {
				Intent intent = new Intent(mActivity, TechnologyActivity.class);
				mActivity.startActivity(intent);
			}else if (position == 5) {
				Intent intent = new Intent(mActivity, YuleActivity.class);
				mActivity.startActivity(intent);
			}else if (position == 6) {
				Intent intent = new Intent(mActivity, MovieActivity.class);
				mActivity.startActivity(intent);
			}
//			else if (position == 7) {
//				Intent intent = new Intent(mActivity, RankActivity.class);
//				mActivity.startActivity(intent);
//			}
			
			
		}
		
	}

	

	
}
