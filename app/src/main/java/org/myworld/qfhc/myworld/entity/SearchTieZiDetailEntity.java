package org.myworld.qfhc.myworld.entity;

import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/7 01:57
 * @备注：
 */
public class SearchTieZiDetailEntity {

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

        private PostEntity post;

        public void setPost(PostEntity post) {
            this.post = post;
        }

        public PostEntity getPost() {
            return post;
        }

        public static class PostEntity {
            private String id;
            private String content;
            private String author_id;
            private String relation_id;
            private String share_url;
            private String is_recommend;
            private String create_time;
            private String publish_time;
            private String datetime;
            private String datestr;
            private String mini_pic_url;

            private AuthorEntity author;

            private DynamicEntity dynamic;

            private List<TagsEntity> tags;

            private List<PicsEntity> pics;

            private List<ProductEntity> product;
            private List<?> comments;

            public void setId(String id) {
                this.id = id;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public void setAuthor_id(String author_id) {
                this.author_id = author_id;
            }

            public void setRelation_id(String relation_id) {
                this.relation_id = relation_id;
            }

            public void setShare_url(String share_url) {
                this.share_url = share_url;
            }

            public void setIs_recommend(String is_recommend) {
                this.is_recommend = is_recommend;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public void setPublish_time(String publish_time) {
                this.publish_time = publish_time;
            }

            public void setDatetime(String datetime) {
                this.datetime = datetime;
            }

            public void setDatestr(String datestr) {
                this.datestr = datestr;
            }

            public void setMini_pic_url(String mini_pic_url) {
                this.mini_pic_url = mini_pic_url;
            }

            public void setAuthor(AuthorEntity author) {
                this.author = author;
            }

            public void setDynamic(DynamicEntity dynamic) {
                this.dynamic = dynamic;
            }

            public void setTags(List<TagsEntity> tags) {
                this.tags = tags;
            }

            public void setPics(List<PicsEntity> pics) {
                this.pics = pics;
            }

            public void setProduct(List<ProductEntity> product) {
                this.product = product;
            }

            public void setComments(List<?> comments) {
                this.comments = comments;
            }

            public String getId() {
                return id;
            }

            public String getContent() {
                return content;
            }

            public String getAuthor_id() {
                return author_id;
            }

            public String getRelation_id() {
                return relation_id;
            }

            public String getShare_url() {
                return share_url;
            }

            public String getIs_recommend() {
                return is_recommend;
            }

            public String getCreate_time() {
                return create_time;
            }

            public String getPublish_time() {
                return publish_time;
            }

            public String getDatetime() {
                return datetime;
            }

            public String getDatestr() {
                return datestr;
            }

            public String getMini_pic_url() {
                return mini_pic_url;
            }

            public AuthorEntity getAuthor() {
                return author;
            }

            public DynamicEntity getDynamic() {
                return dynamic;
            }

            public List<TagsEntity> getTags() {
                return tags;
            }

            public List<PicsEntity> getPics() {
                return pics;
            }

            public List<ProductEntity> getProduct() {
                return product;
            }

            public List<?> getComments() {
                return comments;
            }

            public static class AuthorEntity {
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

            public static class DynamicEntity {
                private String comments;
                private String likes;
                private boolean is_collect;
                private boolean is_like;
                private boolean is_comment;

                public void setComments(String comments) {
                    this.comments = comments;
                }

                public void setLikes(String likes) {
                    this.likes = likes;
                }

                public void setIs_collect(boolean is_collect) {
                    this.is_collect = is_collect;
                }

                public void setIs_like(boolean is_like) {
                    this.is_like = is_like;
                }

                public void setIs_comment(boolean is_comment) {
                    this.is_comment = is_comment;
                }

                public String getComments() {
                    return comments;
                }

                public String getLikes() {
                    return likes;
                }

                public boolean isIs_collect() {
                    return is_collect;
                }

                public boolean isIs_like() {
                    return is_like;
                }

                public boolean isIs_comment() {
                    return is_comment;
                }
            }

            public static class TagsEntity {
                private String id;
                private String name;

                public void setId(String id) {
                    this.id = id;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getId() {
                    return id;
                }

                public String getName() {
                    return name;
                }
            }

            public static class PicsEntity {
                private String url;
                private String tags;
                private int width;
                private int height;

                public void setUrl(String url) {
                    this.url = url;
                }

                public void setTags(String tags) {
                    this.tags = tags;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public String getUrl() {
                    return url;
                }

                public String getTags() {
                    return tags;
                }

                public int getWidth() {
                    return width;
                }

                public int getHeight() {
                    return height;
                }
            }

            public static class ProductEntity {
                private String id;
                private String title;
                private String price;
                private String url;
                private String category_id;
                private String item_id;
                private String platform;
                private String desc;
                private String pic;

                public void setId(String id) {
                    this.id = id;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public void setCategory_id(String category_id) {
                    this.category_id = category_id;
                }

                public void setItem_id(String item_id) {
                    this.item_id = item_id;
                }

                public void setPlatform(String platform) {
                    this.platform = platform;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public String getId() {
                    return id;
                }

                public String getTitle() {
                    return title;
                }

                public String getPrice() {
                    return price;
                }

                public String getUrl() {
                    return url;
                }

                public String getCategory_id() {
                    return category_id;
                }

                public String getItem_id() {
                    return item_id;
                }

                public String getPlatform() {
                    return platform;
                }

                public String getDesc() {
                    return desc;
                }

                public String getPic() {
                    return pic;
                }
            }
        }
    }
}
