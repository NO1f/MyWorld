package org.myworld.qfhc.myworld.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/6 13:26
 * @备注：
 */
public class SearchDeatilTwoChoseEntity implements Serializable{

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

    public static class DataEntity implements Serializable {
        private int id;
        private int category;
        private String title;
        private String desc;
        private String type;
        private String pic;
        private String likes;
        private boolean islike;
        private String tag_ids;
        private String tags;
        private String share_url;
        private String share_pic;
        private String product_pic_host;
        private String user_avatr_host;
        private ActivityEntity activity;

        private List<ProductEntity> product;

        public void setId(int id) {
            this.id = id;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setLikes(String likes) {
            this.likes = likes;
        }

        public void setIslike(boolean islike) {
            this.islike = islike;
        }

        public void setTag_ids(String tag_ids) {
            this.tag_ids = tag_ids;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public void setShare_pic(String share_pic) {
            this.share_pic = share_pic;
        }

        public void setProduct_pic_host(String product_pic_host) {
            this.product_pic_host = product_pic_host;
        }

        public void setUser_avatr_host(String user_avatr_host) {
            this.user_avatr_host = user_avatr_host;
        }

        public void setActivity(ActivityEntity activity) {
            this.activity = activity;
        }

        public void setProduct(List<ProductEntity> product) {
            this.product = product;
        }

        public int getId() {
            return id;
        }

        public int getCategory() {
            return category;
        }

        public String getTitle() {
            return title;
        }

        public String getDesc() {
            return desc;
        }

        public String getType() {
            return type;
        }

        public String getPic() {
            return pic;
        }

        public String getLikes() {
            return likes;
        }

        public boolean isIslike() {
            return islike;
        }

        public String getTag_ids() {
            return tag_ids;
        }

        public String getTags() {
            return tags;
        }

        public String getShare_url() {
            return share_url;
        }

        public String getShare_pic() {
            return share_pic;
        }

        public String getProduct_pic_host() {
            return product_pic_host;
        }

        public String getUser_avatr_host() {
            return user_avatr_host;
        }

        public ActivityEntity getActivity() {
            return activity;
        }

        public List<ProductEntity> getProduct() {
            return product;
        }

        public static class ActivityEntity implements Serializable{
        }

        public static class ProductEntity implements Serializable{
            private String id;
            private String topic_id;
            private int category;
            private String title;
            private String desc;
            private String price;
            private String url;
            private boolean iscomments;
            private String comments;
            private boolean islike;
            private String likes;
            private String item_id;
            private String platform;


            private List<PicEntity> pic;

            private List<LikesListEntity> likes_list;

            public void setId(String id) {
                this.id = id;
            }

            public void setTopic_id(String topic_id) {
                this.topic_id = topic_id;
            }

            public void setCategory(int category) {
                this.category = category;
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

            public void setUrl(String url) {
                this.url = url;
            }

            public void setIscomments(boolean iscomments) {
                this.iscomments = iscomments;
            }

            public void setComments(String comments) {
                this.comments = comments;
            }

            public void setIslike(boolean islike) {
                this.islike = islike;
            }

            public void setLikes(String likes) {
                this.likes = likes;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
            }

            public void setPlatform(String platform) {
                this.platform = platform;
            }

            public void setPic(List<PicEntity> pic) {
                this.pic = pic;
            }

            public void setLikes_list(List<LikesListEntity> likes_list) {
                this.likes_list = likes_list;
            }

            public String getId() {
                return id;
            }

            public String getTopic_id() {
                return topic_id;
            }

            public int getCategory() {
                return category;
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

            public String getUrl() {
                return url;
            }

            public boolean isIscomments() {
                return iscomments;
            }

            public String getComments() {
                return comments;
            }

            public boolean isIslike() {
                return islike;
            }

            public String getLikes() {
                return likes;
            }

            public String getItem_id() {
                return item_id;
            }

            public String getPlatform() {
                return platform;
            }

            public List<PicEntity> getPic() {
                return pic;
            }

            public List<LikesListEntity> getLikes_list() {
                return likes_list;
            }

            public static class PicEntity implements Serializable{
                private String p;
                private int w;
                private int h;

                public void setP(String p) {
                    this.p = p;
                }

                public void setW(int w) {
                    this.w = w;
                }

                public void setH(int h) {
                    this.h = h;
                }

                public String getP() {
                    return p;
                }

                public int getW() {
                    return w;
                }

                public int getH() {
                    return h;
                }
            }

            public static class LikesListEntity implements Serializable{
                private int u;
                private String a;

                public void setU(int u) {
                    this.u = u;
                }

                public void setA(String a) {
                    this.a = a;
                }

                public int getU() {
                    return u;
                }

                public String getA() {
                    return a;
                }
            }
        }
    }
}
