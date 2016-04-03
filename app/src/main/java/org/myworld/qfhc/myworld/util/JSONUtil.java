package org.myworld.qfhc.myworld.util;


import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.myworld.qfhc.myworld.entity.IndexDetailEntity;
import org.myworld.qfhc.myworld.entity.IndexHeadEntity;
import org.myworld.qfhc.myworld.entity.IndextContentEntity;
import org.myworld.qfhc.myworld.entity.ThirdDetailEntity;
import org.myworld.qfhc.myworld.entity.ThirdHeadEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ken on 2016/3/7.
 * 解析json
 */
public class JSONUtil {

    /**
     * 解析首页新闻列表
     *
     * @return
     */
    public static IndexHeadEntity.DataEntity getHeadByJSON(String json) {
        if (json != null) {

            IndexHeadEntity indexEntity = new Gson().fromJson(json, IndexHeadEntity.class);
            IndexHeadEntity.DataEntity data = indexEntity.getData();
            // L.e("--------------------------------------"+indexEntity);
            // L.e("--------------------------------------"+data);
            return data;
        }

        return null;
    }


    public static IndextContentEntity.DataEntity.PostListEntity getContentByJson(String json) {
        if (json != null) {
            IndextContentEntity indextContentEntity = new Gson().fromJson(json, IndextContentEntity.class);
            IndextContentEntity.DataEntity data = indextContentEntity.getData();
            IndextContentEntity.DataEntity.PostListEntity postList = data.getPostList();
            return postList;
        }
        return null;
    }

    public static IndexDetailEntity.DataEntity getDetailByJson(String json) {
        if (json != null) {
            IndexDetailEntity indexDetailEntity = new Gson().fromJson(json, IndexDetailEntity.class);
            IndexDetailEntity.DataEntity data = indexDetailEntity.getData();
            return data;
        }
        return null;
    }

    public static ThirdHeadEntity.DataEntity getThirdByJson(String json) {
        if (json != null) {
            ThirdHeadEntity thirdHeadEntity = new Gson().fromJson(json, ThirdHeadEntity.class);
            ThirdHeadEntity.DataEntity data = thirdHeadEntity.getData();
            return data;
        }
        return null;
    }

    public static List<ThirdDetailEntity> getThirdDetailByJson(String json) {
        if (json != null) {
            List<ThirdDetailEntity> thirdDetailEntityList = new ArrayList<>();
            List<ThirdDetailEntity.TagsEntity> tagsEntityList = new ArrayList<>();
            List<ThirdDetailEntity.PicsEntity> picsEntityList=new ArrayList<>();
            List<ThirdDetailEntity.ProductEntity> productEntityList=new ArrayList<>();;

            try {
                JSONObject jsonObject_all = new JSONObject(json);
                String msg = jsonObject_all.getString("msg");
                if ("ok".equals(msg)) {
                    //表示获取json成功
                    JSONObject jsonData = jsonObject_all.getJSONObject("data");
                    JSONArray list = jsonData.getJSONArray("list");
                    for (int i = 0; i < list.length(); i++) {

                        JSONObject jsonObject = list.getJSONObject(i);
                        String content = jsonObject.getString("content");
                        JSONArray tags = jsonObject.getJSONArray("tags");
                        for (int j = 0; j < tags.length(); j++) {
                            
                            JSONObject jsonTags = tags.getJSONObject(i);
                            String id = jsonTags.getString("id");
                            String name = jsonTags.getString("name");

                            ThirdDetailEntity.TagsEntity tagsEntity = new ThirdDetailEntity.TagsEntity(id, name);
                            tagsEntityList.add(tagsEntity);
                        }
                        String datestr = jsonObject.getString("datestr");
                        JSONArray pics = jsonObject.getJSONArray("pics");
                        for (int k = 0; k < pics.length(); k++) {
                            JSONObject jsonPics = pics.getJSONObject(i);
                            String url = jsonPics.getString("url");
                            ThirdDetailEntity.PicsEntity picsEntity=new ThirdDetailEntity.PicsEntity(url);
                            picsEntityList.add(picsEntity);
                        }
                        JSONArray product = jsonObject.optJSONArray("product");
                        for (int l = 0; l < product.length(); l++) {

                            JSONObject jsonProduct = product.getJSONObject(i);
                            String title = jsonProduct.getString("title");
                            String price = jsonProduct.getString("price");
                            String url = jsonProduct.getString("url");
                            String pic = jsonProduct.getString("pic");


                            ThirdDetailEntity.ProductEntity productEntity=new ThirdDetailEntity.ProductEntity(title,price,url,pic);
                            productEntityList.add(productEntity);
                        }
                        JSONObject dynamic = jsonObject.getJSONObject("dynamic");
                        String likes = dynamic.getString("likes");
                        ThirdDetailEntity.DynamicEntity dynamicEntity=new ThirdDetailEntity.DynamicEntity(likes);
                        
                        ThirdDetailEntity thirdDetailEntity=new ThirdDetailEntity(content,tagsEntityList,datestr,picsEntityList,productEntityList,dynamicEntity);
                        thirdDetailEntityList.add(thirdDetailEntity);
                    }


                    return thirdDetailEntityList;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
