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
        String INDEX_CONTENT_FUSHI = "http://www.lukou.com/api/v2/tag/30/posts?end_id=%d&start=0";
        String INDEX_CONTENT_MEIZHUANG = "http://www.lukou.com/api/v2/tag/2/posts?end_id=%d&start=0";
        String INDEX_CONTENT_JIAJU="http://www.lukou.com/api/v2/tag/29/posts?end_id=%d&start=0";
        String INDEX_CONTENT_MEISHI = "http://www.lukou.com/api/v2/tag/4/posts?end_id=%d&start=0";


    }

    interface KEYS {

        String INDEX_HEAD_CONTENT = "head_content";
        String INDEX_CONTENT_POSITION="position";
    }

}
