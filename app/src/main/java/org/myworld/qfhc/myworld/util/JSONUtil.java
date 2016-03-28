package org.myworld.qfhc.myworld.util;


import com.google.gson.Gson;
import org.myworld.qfhc.myworld.entity.IndexEntity;


/**
 * Created by Ken on 2016/3/7.
 * 解析json
 */
public class JSONUtil {

    /**
     * 解析首页新闻列表
     * @return
     */
    public static IndexEntity.ResultEntity getContentByJSON(String json){
        if(json != null){
                IndexEntity indexEntity = new Gson().fromJson(json, IndexEntity.class);
                IndexEntity.ResultEntity result = indexEntity.getResult();
                return result;
        }

        return null;
    }
}
