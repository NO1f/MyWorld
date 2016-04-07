package org.myworld.qfhc.myworld.entity;

import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/7 13:10
 * @备注：
 */
public class SearchDanPinEntity {

    private int status;
    private String msg;
    private int ts;

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
        private String id;
        private String type_id;
        private String title;
        private String desc;
        private String price;
        private String pic;
        private String likes;

        private UserEntity user;
        private String platform;
        private String item_id;

        public void setId(String id) {
            this.id = id;
        }

        public void setType_id(String type_id) {
            this.type_id = type_id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setLikes(String likes) {
            this.likes = likes;
        }

        public void setUser(UserEntity user) {
            this.user = user;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public String getId() {
            return id;
        }

        public String getType_id() {
            return type_id;
        }

        public String getTitle() {
            return title;
        }

        public String getDesc() {
            return desc;
        }

        public String getPrice() {
            return price;
        }

        public String getPic() {
            return pic;
        }

        public String getLikes() {
            return likes;
        }

        public UserEntity getUser() {
            return user;
        }

        public String getPlatform() {
            return platform;
        }

        public String getItem_id() {
            return item_id;
        }

        public static class UserEntity {
            private String user_id;
            private String nickname;
            private String avatar;
            private int is_official;

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public void setIs_official(int is_official) {
                this.is_official = is_official;
            }

            public String getUser_id() {
                return user_id;
            }

            public String getNickname() {
                return nickname;
            }

            public String getAvatar() {
                return avatar;
            }

            public int getIs_official() {
                return is_official;
            }
        }
    }
}
