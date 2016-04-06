package org.myworld.qfhc.myworld.entity;

import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/6 00:59
 * @备注：
 */
public class SearchTwoEntity  {

    private int status;
    private String msg;
    private int ts;
    private DataEntity data;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setTs(int ts) {
        this.ts = ts;
    }

    public void setData(DataEntity data) {
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

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {

        private List<TopicEntity> topic;

        public void setTopic(List<TopicEntity> topic) {
            this.topic = topic;
        }

        public List<TopicEntity> getTopic() {
            return topic;
        }

        public static class TopicEntity {
            private String id;
            private String title;
            private String type;
            private String pic;
            private boolean is_show_like;
            private boolean islike;
            private String likes;
            private String update_time;

            public void setId(String id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public void setIs_show_like(boolean is_show_like) {
                this.is_show_like = is_show_like;
            }

            public void setIslike(boolean islike) {
                this.islike = islike;
            }

            public void setLikes(String likes) {
                this.likes = likes;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public String getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public String getType() {
                return type;
            }

            public String getPic() {
                return pic;
            }

            public boolean isIs_show_like() {
                return is_show_like;
            }

            public boolean isIslike() {
                return islike;
            }

            public String getLikes() {
                return likes;
            }

            public String getUpdate_time() {
                return update_time;
            }
        }
    }
}
