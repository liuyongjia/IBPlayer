package com.example.ibplayer.page;

import java.util.ArrayList;
import java.util.List;

import com.example.ibplayer.R;
import com.example.ibplayer.base.BaseFragment;
import com.example.ibplayer.imp.GameCenter;
import com.example.ibplayer.imp.Guide;
import com.example.ibplayer.imp.HomePage;
import com.example.ibplayer.imp.Special;
import com.example.ibplayer.imp.DivBAanguimi;
import com.example.ibplayer.util.UrlUtil;
import com.viewpagerindicator.TabPageIndicator;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RankPageContainer extends Fragment{

	List<BaseFragment> list = new ArrayList<BaseFragment>();
	Activity mActivity;
	private static final String[] CONTENT = new String[] { "全区", "新番", "动画","音乐","游戏","科学","娱乐","电影"};
	public RankPageContainer(Activity activity) {
		super();
		mActivity = activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initData();
	}

	private void initData() {
		
		String url_quanqu = UrlUtil.HOST+UrlUtil.URL_RANK_QUAN_QU ;
		String url_xinfan = UrlUtil.HOST+UrlUtil.URL_RANK_XIN_FAN ;
		String url_donghua = UrlUtil.HOST+UrlUtil.URL_RANK_DONG_HUA ;
		String url_yinyue = UrlUtil.HOST+UrlUtil.URL_RANK_YIN_YUE ;
		String url_youxi = UrlUtil.HOST+UrlUtil.URL_RANK_YOU_XI ;
		String url_keji = UrlUtil.HOST+UrlUtil.URL_RANK_KE_JI ;
		String url_yule = UrlUtil.HOST+UrlUtil.URL_RANK_YU_LE ;
		String url_dianying = UrlUtil.HOST+UrlUtil.URL_RANK_DIAN_YING ;
		list.add(new DivBAanguimi(mActivity,url_quanqu));
		list.add(new DivBAanguimi(mActivity,url_xinfan));
		list.add(new DivBAanguimi(mActivity,url_donghua));
		list.add(new DivBAanguimi(mActivity,url_yinyue));
		list.add(new DivBAanguimi(mActivity,url_youxi));
		list.add(new DivBAanguimi(mActivity,url_keji));
		list.add(new DivBAanguimi(mActivity,url_yule));
		list.add(new DivBAanguimi(mActivity,url_dianying));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = View.inflate(mActivity, R.layout.page_activity, null);
		ViewPager pager = (ViewPager) view.findViewById(R.id.vp_page);
		pager.setAdapter(new MyAdapter());
		TabPageIndicator indicator = (TabPageIndicator)view.findViewById(R.id.indicator);
		indicator.setBackgroundColor(Color.parseColor("#db77ab"));
        indicator.setViewPager(pager);
		return view;
	}
	
	class MyAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			
			return arg0 == (View) arg1 ;
		}
		
		@Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			
			container.addView(list.get(position).view);
			return list.get(position).view;
		}
		
		
		
	}
	
	
}
