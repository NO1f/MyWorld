package org.myworld.qfhc.myworld.util;


import com.google.gson.Gson;
import org.myworld.qfhc.myworld.entity.IndexHeadEntity;
import org.myworld.qfhc.myworld.entity.IndextContentEntity;


/**
 * Created by Ken on 2016/3/7.
 * 解析json
 */
public class JSONUtil {

    /**
     * 解析首页新闻列表
     * @return
     */
    public static IndexHeadEntity.DataEntity getHeadByJSON(String json){
        if(json != null){

            IndexHeadEntity indexEntity = new Gson().fromJson(json, IndexHeadEntity.class);
            IndexHeadEntity.DataEntity data = indexEntity.getData();
            L.e("--------------------------------------"+indexEntity);
            L.e("--------------------------------------"+data);
            return data;
        }

        return null;
    }


    public static IndextContentEntity.DataEntity.PostListEntity getContentByJson(String json){
        if (json!=null){
            IndextContentEntity indextContentEntity = new Gson().fromJson(json, IndextContentEntity.class);
            IndextContentEntity.DataEntity data = indextContentEntity.getData();
            IndextContentEntity.DataEntity.PostListEntity postList = data.getPostList();
            return postList;
        }
        return null;
    }

}
