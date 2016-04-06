package org.myworld.qfhc.myworld.entity;

import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/5 23:03
 * @备注：
 */
public class SearchDetailOneDetEntity {

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
        private String id;
        private String likes;
        private boolean islike;
        private String comments;
        private boolean iscomments;

        private ProductEntity product;

        public void setId(String id) {
            this.id = id;
        }

        public void setLikes(String likes) {
            this.likes = likes;
        }

        public void setIslike(boolean islike) {
            this.islike = islike;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public void setIscomments(boolean iscomments) {
            this.iscomments = iscomments;
        }

        public void setProduct(ProductEntity product) {
            this.product = product;
        }

        public String getId() {
            return id;
        }

        public String getLikes() {
            return likes;
        }

        public boolean isIslike() {
            return islike;
        }

        public String getComments() {
            return comments;
        }

        public boolean isIscomments() {
            return iscomments;
        }

        public ProductEntity getProduct() {
            return product;
        }

        public static class ProductEntity {
            private String id;
            private String topic_id;
            private String category;
            private String title;
            private String desc;
            private String price;
            private String url;
            private String item_id;
            private String platform;
            private String share_url;

            private List<PicEntity> pic;

            public void setId(String id) {
                this.id = id;
            }

            public void setTopic_id(String topic_id) {
                this.topic_id = topic_id;
            }

            public void setCategory(String category) {
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

            public void setItem_id(String item_id) {
                this.item_id = item_id;
            }

            public void setPlatform(String platform) {
                this.platform = platform;
            }

            public void setShare_url(String share_url) {
                this.share_url = share_url;
            }

            public void setPic(List<PicEntity> pic) {
                this.pic = pic;
            }

            public String getId() {
                return id;
            }

            public String getTopic_id() {
                return topic_id;
            }

            public String getCategory() {
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

            public String getItem_id() {
                return item_id;
            }

            public String getPlatform() {
                return platform;
            }

            public String getShare_url() {
                return share_url;
            }

            public List<PicEntity> getPic() {
                return pic;
            }

            public static class PicEntity {
                private String pic;
                private String width;
                private String height;

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public void setWidth(String width) {
                    this.width = width;
                }

                public void setHeight(String height) {
                    this.height = height;
                }

                public String getPic() {
                    return pic;
                }

                public String getWidth() {
                    return width;
                }

                public String getHeight() {
                    return height;
                }
            }
        }
    }
}
