package org.myworld.qfhc.myworld.util;

/**
 * @version V1.0
 * @类名称: ${type_name}
 * @类描述: ${todo}
 * @创建人：HC
 * @创建时间：${date} ${time}
 * @备注：FirstReceiver
 */
public interface Constant {

    interface CODE {

    }

    interface URL {
        String INDEX_HEAD = "http://www.lukou.com/api/v2/home/head";
        String INDEX_CONTENT_JINGXUAN = "http://www.lukou.com/api/v2/tag/32/posts?is_home=1&end_id=%d&start=0";
        String INDEX_CONTENT_LUYOUSHUO = "http://www.lukou.com/api/v2/tag/46/posts?end_id=%d&start=0";
        String INDEX_CONTENT_FUSHI = "http://www.lukou.com/api/v2/tag/30/posts?end_id=%d&start=0";
        String INDEX_CONTENT_MEIZHUANG = "http://www.lukou.com/api/v2/tag/2/posts?end_id=%d&start=0";
        String INDEX_CONTENT_JIAJU = "http://www.lukou.com/api/v2/tag/29/posts?end_id=%d&start=0";
        String INDEX_CONTENT_MEISHI = "http://www.lukou.com/api/v2/tag/4/posts?end_id=%d&start=0";

        String INDEX_DETAIL = "http://www.lukou.com/sharepost/%d?platform=app&app_version=0&os=android&source=";


        String THIRD_ONE = "http://open4.bantangapp.com/community/post/communityHome?com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=&oauth_token=&track_deviceid=862719022792917&track_device_info=R821T&channel_name=oppo&app_installtime=1459500638619&app_versions=5.5.1&os_versions=4.2.2&screensize=480&v=11";
        String THIRD_ONE_BOTTOM = "http://open4.bantangapp.com/community/group/info?com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=&oauth_token=&track_deviceid=862719022792917&track_device_info=R821T&channel_name=oppo&app_installtime=1459500638619&app_versions=5.5.1&os_versions=4.2.2&screensize=480&v=11&page=%d&pagesize=10&group_id=%d&type_id=0";
        String THIRD_ONE_ELE = "http://open4.bantangapp.com/community/post/elementPost?com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=&oauth_token=&track_deviceid=862719022792917&track_device_info=R821T&channel_name=oppo&app_installtime=1459500638619&app_versions=5.5.1&os_versions=4.2.2&screensize=480&v=11&page=%d&pagesize=10&element_id=%d";
        String THIRD_ONE_TAG = "http://open4.bantangapp.com/community/post/listByTag?com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=&oauth_token=&track_deviceid=862719022792917&track_device_info=R821T&channel_name=oppo&app_installtime=1459500638619&app_versions=5.5.1&os_versions=4.2.2&screensize=480&v=11&page=%d&pagesize=10&type_id=0&last_id=&tag_id=%d";

        String SEARCH_ONE = "http://open4.bantangapp.com/category/list?com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=2014239&oauth_token=1a329999cbdc5ef0c6081f4d62b99393&track_deviceid=862719022792917&track_device_info=R821T&channel_name=oppo&app_installtime=1459500638619&app_versions=5.5.1&os_versions=4.2.2&screensize=480&v=11&is_new=1";
        String SEARCH_TWO = "http://open4.bantangapp.com/category/scene?com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=2014239&oauth_token=1a329999cbdc5ef0c6081f4d62b99393&track_deviceid=862719022792917&track_device_info=R821T&channel_name=oppo&app_installtime=1459500638619&app_versions=5.5.1&os_versions=4.2.2&screensize=480&v=11";
        String SEARCH_DETAIL_ONE = "http://open4.bantangapp.com/product/productList?com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=2014239&oauth_token=1a329999cbdc5ef0c6081f4d62b99393&track_deviceid=862719022792917&track_device_info=R821T&channel_name=oppo&app_installtime=1459500638619&app_versions=5.5.1&os_versions=4.2.2&screensize=480&v=11&subclass=%d&page=%d&pagesize=10";
        String SEARCH_DETAIL_ONE_DET = "http://open4.bantangapp.com/product/newInfo?com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=2014239&oauth_token=1a329999cbdc5ef0c6081f4d62b99393&track_deviceid=862719022792917&track_device_info=R821T&channel_name=oppo&app_installtime=1459500638619&app_versions=5.5.1&os_versions=4.2.2&screensize=480&v=11&id=%d&page=0&pagesize=10&comments_pagesize=10";

        String SEARCH_DETAIL_TWO_NEWS = "http://open4.bantangapp.com/topic/list?com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=2014239&oauth_token=1a329999cbdc5ef0c6081f4d62b99393&track_deviceid=862719022792917&track_device_info=R821T&channel_name=oppo&app_installtime=1459500638619&app_versions=5.5.1&os_versions=4.2.2&screensize=480&v=11&scene=%d&type_id=0&update_time=&page=%d&pagesize=10";
        String SEARCH_DETAIL_TWO_HOST = "http://open4.bantangapp.com/topic/list?com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=2014239&oauth_token=1a329999cbdc5ef0c6081f4d62b99393&track_deviceid=862719022792917&track_device_info=R821T&channel_name=oppo&app_installtime=1459500638619&app_versions=5.5.1&os_versions=4.2.2&screensize=480&v=11&scene=%d&type_id=1&update_time=&page=%d&pagesize=10";
        String SEARCH_DETAIL_TWO_DETAIL = "http://open4.bantangapp.com/topic/newInfo?com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=2014239&oauth_token=1a329999cbdc5ef0c6081f4d62b99393&track_deviceid=862719022792917&track_device_info=R821T&channel_name=oppo&app_installtime=1459500638619&app_versions=5.5.1&os_versions=4.2.2&screensize=480&v=11&id=%d&statistics_uv=0&from_home_rec=0";
        String SEARCH_DETAIL_RECOMMEND = "http://open4.bantangapp.com/community/post/listByTags?com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=2014239&oauth_token=1a329999cbdc5ef0c6081f4d62b99393&track_deviceid=862719022792917&track_device_info=R821T&channel_name=oppo&app_installtime=1459500638619&app_versions=5.5.1&os_versions=4.2.2&screensize=480&v=11&page=0&pagesize=%d&tag_ids=757,16156,642,1608,425,1174,6771&type_id=1&topic_id=%d&show_product=1";

    }

    interface KEYS {

        String INDEX_HEAD_CONTENT = "head_content";
        String INDEX_CONTENT_POSITION = "position";
        String INDEX_DETAIL_URL = "detail_url";
        String THIRD_HEAD = "third_head";
        String THIRD_DETAIL_URL = "third_detail_url";
        String THIRD_DETAIL_TITLE = "title";
        String THIRD_DETAIL_ID = "third_detail_id";
        String THIRD_BOTTOM_DETAIL_ID = "third_bottom_detail_id";
        String THIRD_BOTTOM_DETAIL_NAME = "third_bottom_detail_name";
        String SEARCH_ONE_ID = "search_one_id";
        String SEARCH_ONE_NAME = "search_one_name";
        String SEARCH_ONE_DETAIL_ID = "search_one_detail_id";
        String SEARCH_TWO_NAME = "search_two_name";
        String SEARCH_TWO_ID = "search_two_id";
        String SEARCH_TWO_DET_ID = "search_two_det_id ";
        String BUNDLE = "bundle";
        String SEARCH_RECOMMENT = "search_recomment";
    }

}
