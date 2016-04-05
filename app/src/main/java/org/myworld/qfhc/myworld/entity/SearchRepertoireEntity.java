package org.myworld.qfhc.myworld.entity;

import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/5 15:01
 * @备注：
 */
public class SearchRepertoireEntity {


    /**
     * status : 1
     * msg : ok
     * ts : 1459824191
     * data : [{"id":1,"name":"厨房","en_name":"Kitchen","icon":"http://7viiaj.com2.z0.glb.qiniucdn.com/scene/001.png"},{"id":3,"name":"礼物","en_name":"Gift","icon":"http://7viiaj.com2.z0.glb.qiniucdn.com/scene/003.png"},{"id":4,"name":"宿舍","en_name":"Dorm","icon":"http://7viiaj.com2.z0.glb.qiniucdn.com/scene/004.png"},{"id":5,"name":"学生党","en_name":"Student","icon":"http://7viiaj.com2.z0.glb.qiniucdn.com/scene/005.png"},{"id":6,"name":"上班族","en_name":"Office","icon":"http://7viiaj.com2.z0.glb.qiniucdn.com/scene/006.png"},{"id":8,"name":"文艺","en_name":"Literature","icon":"http://7viiaj.com2.z0.glb.qiniucdn.com/scene/008.png"},{"id":9,"name":"节日","en_name":"Festival","icon":"http://7viiaj.com2.z0.glb.qiniucdn.com/scene/009.png"},{"id":10,"name":"指南","en_name":"Guide","icon":"http://7viiaj.com2.z0.glb.qiniucdn.com/scene/010.png"},{"id":13,"name":"爱美","en_name":"Beauty","icon":"http://7viiaj.com2.z0.glb.qiniucdn.com/scene/013.png"},{"id":14,"name":"聚会","en_name":"Party","icon":"http://7viiaj.com2.z0.glb.qiniucdn.com/scene/014.png"},{"id":15,"name":"吃货","en_name":"Foodie","icon":"http://7viiaj.com2.z0.glb.qiniucdn.com/scene/015.png"},{"id":16,"name":"设计","en_name":"Design","icon":"http://7viiaj.com2.z0.glb.qiniucdn.com/scene/016.png"},{"id":17,"name":"格调","en_name":"Bigger","icon":"http://7viiaj.com2.z0.glb.qiniucdn.com/scene/017.png"}]
     */

    private int status;
    private String msg;
    private int ts;
    /**
     * id : 1
     * name : 厨房
     * en_name : Kitchen
     * icon : http://7viiaj.com2.z0.glb.qiniucdn.com/scene/001.png
     */

    private List<DataEntity> data;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setTs(int ts) {
        this.ts = ts;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public int getTs() {
        return ts;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private int id;
        private String name;
        private String en_name;
        private String icon;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setEn_name(String en_name) {
            this.en_name = en_name;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEn_name() {
            return en_name;
        }

        public String getIcon() {
            return icon;
        }
    }
}
