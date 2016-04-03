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
        String INDEX_CONTENT_LUYOUSHUO="http://www.lukou.com/api/v2/tag/46/posts?end_id=%d&start=0";
        String INDEX_CONTENT_FUSHI = "http://www.lukou.com/api/v2/tag/30/posts?end_id=%d&start=0";
        String INDEX_CONTENT_MEIZHUANG = "http://www.lukou.com/api/v2/tag/2/posts?end_id=%d&start=0";
        String INDEX_CONTENT_JIAJU="http://www.lukou.com/api/v2/tag/29/posts?end_id=%d&start=0";
        String INDEX_CONTENT_MEISHI = "http://www.lukou.com/api/v2/tag/4/posts?end_id=%d&start=0";

        String INDEX_DETAIL ="http://www.lukou.com/sharepost/%d?platform=app&app_version=0&os=android&source=";


        String THIRD_ONE="http://open4.bantangapp.com/community/post/communityHome?com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=&oauth_token=&track_deviceid=862719022792917&track_device_info=R821T&channel_name=oppo&app_installtime=1459500638619&app_versions=5.5.1&os_versions=4.2.2&screensize=480&v=11";
        String THIRD_ONE_BOTTOM="http://open4.bantangapp.com/community/group/info?com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=&oauth_token=&track_deviceid=862719022792917&track_device_info=R821T&channel_name=oppo&app_installtime=1459500638619&app_versions=5.5.1&os_versions=4.2.2&screensize=480&v=11&page=0&pagesize=10&group_id=%f&type_id=1";
        String THIRD_ONE_ELE="http://open4.bantangapp.com/community/post/elementPost?com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=&oauth_token=&track_deviceid=862719022792917&track_device_info=R821T&channel_name=oppo&app_installtime=1459500638619&app_versions=5.5.1&os_versions=4.2.2&screensize=480&v=11&page=%d&pagesize=10&element_id=%f";
        String THIRD_ONE_TAG="http://open4.bantangapp.com/community/post/listByTag?com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=&oauth_token=&track_deviceid=862719022792917&track_device_info=R821T&channel_name=oppo&app_installtime=1459500638619&app_versions=5.5.1&os_versions=4.2.2&screensize=480&v=11&page=%d&pagesize=10&type_id=0&last_id=&tag_id=%f";
    }

    interface KEYS {

        String INDEX_HEAD_CONTENT = "head_content";
        String INDEX_CONTENT_POSITION="position";
        String INDEX_DETAIL_URL = "detail_url";
        String THIRD_HEAD = "third_head";
        String THIRD_DETAIL_URL = "third_detail_url";
        String THIRD_DETAIL_TITLE = "title";
        String THIRD_DETAIL_ID = "third_detail_id";
    }

}
