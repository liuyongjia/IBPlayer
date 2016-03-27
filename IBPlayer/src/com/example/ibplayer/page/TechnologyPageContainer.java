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

public class TechnologyPageContainer extends Fragment{

	List<BaseFragment> list = new ArrayList<BaseFragment>();
	Activity mActivity;
	private static final String[] CONTENT = new String[] { "全区动态", "记录片", "科普人文","野生技术","公开课","军事","数码"};
	public TechnologyPageContainer(Activity activity) {
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
		
		String url_quanqu = UrlUtil.HOST+UrlUtil.URL_KE_JI ;
		String url_jilupian = UrlUtil.HOST+UrlUtil.URL_JI_LU_PIAN ;
		String url_kepu = UrlUtil.HOST+UrlUtil.URL_KE_PU_REN_WEN ;
		String url_yesheng = UrlUtil.HOST+UrlUtil.URL_YE_SHENG_JI_SHU ;
		String url_yanjing = UrlUtil.HOST+UrlUtil.URL_YAN_JIANG ;
		String url_junshi = UrlUtil.HOST+UrlUtil.URL_JUN_SHI ;
		String url_shuma = UrlUtil.HOST+UrlUtil.URL_SHU_MA ;
		list.add(new DivBAanguimi(mActivity,url_quanqu));
		list.add(new DivBAanguimi(mActivity,url_jilupian));
		list.add(new DivBAanguimi(mActivity,url_kepu));
		list.add(new DivBAanguimi(mActivity,url_yesheng));
		list.add(new DivBAanguimi(mActivity,url_yanjing));
		list.add(new DivBAanguimi(mActivity,url_junshi));
		list.add(new DivBAanguimi(mActivity,url_shuma));
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
