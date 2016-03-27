package com.example.ibplayer.util;

public class UrlUtil {
	
	public final static String HOST = "http://www.bilibili.com/";
	
	//推荐
	public final static String HOMEPAGE = "index/ding.json";
	
	
	//分区-番剧-连载动画
	public static String URL_LIAN_ZAI_DONG_HUA = "index/ding/33.json";
	//分区-番剧-完结动画
	public static String URL_WAN_JIE_DONG_HUA = "index/ding/32.json";
	//分区-番剧-资讯
	public static String URL_ZHI_XUN = "index/ding/51.json";
	//分区-番剧-官方延伸
	public static String URL_GUAN_FANG_YAN_SHEN = "index/ding/152.json";
	//分区-番剧-国产动画
	public static String URL_GUO_CHAN_DONG_HUA = "index/ding/153.json";
	
	
	//分区-动画
	public static String URL_DONG_HUA = "index/ding/1.json";
	public static String URL_MAD_MAV = "index/ding/24.json";
	public static String URL_MAD_3D = "index/ding/25.json";
	public static String URL_DONG_HUA_DUAN_PIAN = "index/ding/47.json";
	public static String URL_DONG_HUA_ZONG_HE = "index/ding/27.json";
	
	//分区-音乐
	public static String URL_YIN_YUE = "index/ding/3.json";
	public static String URL_FAN_CHANG = "index/ding/31.json";
	public static String URL_VOCALOID_UTAU = "index/ding/30.json";
	public static String URL_YAN_ZOU= "index/ding/59.json";
	public static String URL_YIN_YUE_XUAN_JI = "index/ding/130.json";
	
	//分区-游戏
	public static String URL_YOU_XI = "index/ding/4.json";
	public static String URL_DAN_JI = "index/ding/17.json";
	public static String URL_WANG_LUO_YOU_XI = "index/ding/65.json";
	public static String URL_DIAN_ZI_JING_JI = "index/ding/60.json";
	
	//分区-科技
	public static String URL_KE_JI = "index/ding/36.json";
	public static String URL_JI_LU_PIAN = "index/ding/37.json";
	public static String URL_KE_PU_REN_WEN = "index/ding/124.json";
	public static String URL_YE_SHENG_JI_SHU= "index/ding/122.json";
	public static String URL_YAN_JIANG = "index/ding/39.json";
	public static String URL_JUN_SHI = "index/ding/96.json";
	public static String URL_SHU_MA = "index/ding/95.json";
	
	//分区-娱乐
	public static String URL_YU_LE = "index/ding/5.json";
	public static String URL_GAO_XIAO = "index/ding/138.json";
	public static String URL_SHENG_HUO = "index/ding/21.json";
	public static String URL_ZONG_YI = "index/ding/71.json";
	
	//分区-电影
	public static String URL_DIAN_YIN = "index/ding/23.json";
	public static String URL_OU_MEI_DIAN_YIN = "index/ding/145.json";
	public static String URL_RI_BEN_DIAN_YIN = "index/ding/146.json";
	public static String URL_GUO_CHAN_DIAN_YIN = "index/ding/147.json";
	public static String URL_DIAN_YIN_XIANG_GUAN = "index/ding/82.json";
	
	//分区-排行
	public static String URL_RANK_QUAN_QU = "index/rank/all-7-0.json";//全区
	public static String URL_RANK_XIN_FAN = "index/rank/all-7-33.json";//新番
	public static String URL_RANK_DONG_HUA = "index/rank/all-7-1.json";//动画
	public static String URL_RANK_YIN_YUE = "index/rank/all-7-3.json";//音乐
	public static String URL_RANK_YOU_XI = "index/rank/all-7-4.json";//游戏
	public static String URL_RANK_KE_JI = "index/rank/all-7-36.json";//科技
	public static String URL_RANK_YU_LE = "index/rank/all-7-5.json";//娱乐
	public static String URL_RANK_DIAN_YING = "index/rank/all-7-23.json";//电影
	
	//包括cid、分页标题。
	public static String URL_PAGE_DIV = "http://www.bilibili.com/widget/getPageList?aid=";
	
	//分段视频初步
	public static String URL_PAGE_ITEM_1 = "http://interface.bilibili.com/playurl?_aid=";
	public static String URL_PAGE_ITEM_2 = "&_tid=0&_p=1&_down=0&cid=";
	public static String URL_PAGE_ITEM_3 = "&quality=3&otype=json&appkey=86385cdc024c0f6c&type=mp4";
	
	//获取弹幕
	public static String URL_DANMU_1 = "http://comment.bilibili.com/";
	public static String URL_DANMU_2 = ".xml";
	
	//专题界面
	public static String URL_SPECIAL = "http://app.bilibili.com/promo/android3/2626/bangumi.android3.xhdpi.json";
	
	//专题相关视频
	public static String URL_SPECIAL_REL1 = "http://api.bilibili.com/spview?spid=" ;
	public static String URL_SPECIAL_REL2 = "&type=json&sign=c0a162542dd9df6b7ba5075c658f7ff6" ;
	
	//游戏中心
	public static String URL_GAMECENTER = "http://app.bilibili.com/promo/android3/2914/game.android3.xhdpi.json" ;
	
//	获取aid
//	http://interface.bilibili.com/player?id=cid:{cid}
}
