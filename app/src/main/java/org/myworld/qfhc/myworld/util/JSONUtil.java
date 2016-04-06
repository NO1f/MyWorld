package org.myworld.qfhc.myworld.util;


import com.google.gson.Gson;

import org.myworld.qfhc.myworld.entity.IndexDetailEntity;
import org.myworld.qfhc.myworld.entity.IndexHeadEntity;
import org.myworld.qfhc.myworld.entity.IndextContentEntity;
import org.myworld.qfhc.myworld.entity.SearchDeatilTwoChoseEntity;
import org.myworld.qfhc.myworld.entity.SearchDetailOneDetEntity;
import org.myworld.qfhc.myworld.entity.SearchDetailOneEntity;
import org.myworld.qfhc.myworld.entity.SearchQingDanEntity;
import org.myworld.qfhc.myworld.entity.SearchRepertoireEntity;
import org.myworld.qfhc.myworld.entity.SearchSKUEntity;
import org.myworld.qfhc.myworld.entity.SearchTieZiDetailEntity;
import org.myworld.qfhc.myworld.entity.SearchTieZiEntity;
import org.myworld.qfhc.myworld.entity.SearchTwoEntity;
import org.myworld.qfhc.myworld.entity.ThirdBottomDetailEntity;
import org.myworld.qfhc.myworld.entity.ThirdDetailEntity;
import org.myworld.qfhc.myworld.entity.ThirdHeadEntity;

import java.util.List;


/**
 * Created by Ken on 2016/3/7.
 * 解析json
 */
public class JSONUtil {

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

    public static ThirdDetailEntity.DataEntity getThirdDetailByJson(String json) {
        if (json != null) {
            ThirdDetailEntity thirdDetailEntity = new Gson().fromJson(json, ThirdDetailEntity.class);
            ThirdDetailEntity.DataEntity data = thirdDetailEntity.getData();
            return data;
        }
        return null;
    }

    public static ThirdBottomDetailEntity.DataEntity getThirdBottomDetailByJson(String json) {
        if (json != null) {
            ThirdBottomDetailEntity thirdBottomDetailEntity = new Gson().fromJson(json, ThirdBottomDetailEntity.class);
            ThirdBottomDetailEntity.DataEntity data = thirdBottomDetailEntity.getData();
            return data;
        }
        return null;
    }

    public static List<SearchSKUEntity.DataEntity> getSearchSKUByJson(String json) {
        if (json != null) {
            SearchSKUEntity searchSKUEntity = new Gson().fromJson(json, SearchSKUEntity.class);
            List<SearchSKUEntity.DataEntity> data = searchSKUEntity.getData();
            return data;
        }
        return null;
    }

    public static List<SearchRepertoireEntity.DataEntity> getSearchRepertoireByJson(String json) {
        if (json != null) {
            SearchRepertoireEntity searchRepertoireEntity = new Gson().fromJson(json, SearchRepertoireEntity.class);
            List<SearchRepertoireEntity.DataEntity> data = searchRepertoireEntity.getData();
            return data;
        }
        return null;
    }

    public static List<SearchDetailOneEntity.DataEntity.ListEntity> getSearchDetailOneByJson(String json) {
        if (json != null) {
            SearchDetailOneEntity searchDetailOneEntity = new Gson().fromJson(json, SearchDetailOneEntity.class);
            SearchDetailOneEntity.DataEntity data = searchDetailOneEntity.getData();
            List<SearchDetailOneEntity.DataEntity.ListEntity> list = data.getList();
            return list;
        }
        return null;
    }

    public static SearchDetailOneDetEntity.DataEntity.ProductEntity getSearchDetailOneDetByJson(String json) {
        if (json != null) {
            SearchDetailOneDetEntity searchDetailOneDetEntity = new Gson().fromJson(json, SearchDetailOneDetEntity.class);
            SearchDetailOneDetEntity.DataEntity.ProductEntity product = searchDetailOneDetEntity.getData().getProduct();
            return product;
        }
        return null;
    }

    public static List<SearchTwoEntity.DataEntity.TopicEntity> getSearchDetailTwoByJson(String json) {
        if (json != null) {

            SearchTwoEntity searchTwoEntity = new Gson().fromJson(json, SearchTwoEntity.class);
            List<SearchTwoEntity.DataEntity.TopicEntity> topic = searchTwoEntity.getData().getTopic();

            return topic;
        }
        return null;
    }

    public static SearchDeatilTwoChoseEntity.DataEntity getSearchDetailChoseByJson(String json) {
        if (json != null) {

            SearchDeatilTwoChoseEntity searchDeatilTwoChoseEntity = new Gson().fromJson(json, SearchDeatilTwoChoseEntity.class);
            SearchDeatilTwoChoseEntity.DataEntity data = searchDeatilTwoChoseEntity.getData();

            return data;
        }
        return null;
    }


    public static List<SearchQingDanEntity.DataEntity> getSearchQingdanByJson(String json) {
        if (json != null) {
            SearchQingDanEntity searchQingdanEntity = new Gson().fromJson(json, SearchQingDanEntity.class);
            List<SearchQingDanEntity.DataEntity> data = searchQingdanEntity.getData();
            return data;
        }
        return null;
    }

    public static List<SearchTieZiEntity.DataEntity> getSearchTieziByJson(String json) {
        if (json != null) {
            SearchTieZiEntity searchTieZiEntity = new Gson().fromJson(json, SearchTieZiEntity.class);
            List<SearchTieZiEntity.DataEntity> data = searchTieZiEntity.getData();
            return data;
        }
        return null;
    }

    public static SearchTieZiDetailEntity.DataEntity.PostEntity getSearchTieziDetailByJson(String json) {
        if (json != null) {
            SearchTieZiDetailEntity searchTieZiDetailEntity = new Gson().fromJson(json, SearchTieZiDetailEntity.class);
            SearchTieZiDetailEntity.DataEntity.PostEntity post = searchTieZiDetailEntity.getData().getPost();
            return post;
        }
        return null;
    }


}
