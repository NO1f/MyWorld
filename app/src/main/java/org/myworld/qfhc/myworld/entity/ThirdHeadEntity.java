package org.myworld.qfhc.myworld.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/1 17:47
 * @备注：
 */
public class ThirdHeadEntity {

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
        private int attention_group_size;


        private List<RecGroupsEntity> rec_groups;

        private List<ModuleElementsEntity> module_elements;
        private List<?> attention_groups;

        public void setAttention_group_size(int attention_group_size) {
            this.attention_group_size = attention_group_size;
        }

        public void setRec_groups(List<RecGroupsEntity> rec_groups) {
            this.rec_groups = rec_groups;
        }

        public void setModule_elements(List<ModuleElementsEntity> module_elements) {
            this.module_elements = module_elements;
        }

        public void setAttention_groups(List<?> attention_groups) {
            this.attention_groups = attention_groups;
        }

        public int getAttention_group_size() {
            return attention_group_size;
        }

        public List<RecGroupsEntity> getRec_groups() {
            return rec_groups;
        }

        public List<ModuleElementsEntity> getModule_elements() {
            return module_elements;
        }

        public List<?> getAttention_groups() {
            return attention_groups;
        }

        public static class RecGroupsEntity {
            private String id;
            private String name;
            private String pic1;
            private String pic2;
            private String pic3;
            private String create_time;
            private String update_time;
            private AuthorEntity author;


            private DynamicEntity dynamic;
            private int attention_type;
            private String desc;
            private List<?> attention_users;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setPic1(String pic1) {
                this.pic1 = pic1;
            }

            public void setPic2(String pic2) {
                this.pic2 = pic2;
            }

            public void setPic3(String pic3) {
                this.pic3 = pic3;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public void setAuthor(AuthorEntity author) {
                this.author = author;
            }

            public void setDynamic(DynamicEntity dynamic) {
                this.dynamic = dynamic;
            }

            public void setAttention_type(int attention_type) {
                this.attention_type = attention_type;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public void setAttention_users(List<?> attention_users) {
                this.attention_users = attention_users;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getPic1() {
                return pic1;
            }

            public String getPic2() {
                return pic2;
            }

            public String getPic3() {
                return pic3;
            }

            public String getCreate_time() {
                return create_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public AuthorEntity getAuthor() {
                return author;
            }

            public DynamicEntity getDynamic() {
                return dynamic;
            }

            public int getAttention_type() {
                return attention_type;
            }

            public String getDesc() {
                return desc;
            }

            public List<?> getAttention_users() {
                return attention_users;
            }

            public static class AuthorEntity {
            }

            public static class DynamicEntity {
                private int views;
                private int attentions;
                private int posts;

                public void setViews(int views) {
                    this.views = views;
                }

                public void setAttentions(int attentions) {
                    this.attentions = attentions;
                }

                public void setPosts(int posts) {
                    this.posts = posts;
                }

                public int getViews() {
                    return views;
                }

                public int getAttentions() {
                    return attentions;
                }

                public int getPosts() {
                    return posts;
                }
            }
        }

        public static class ModuleElementsEntity implements Serializable{
            private String id;
            private String title;

            private List<ElementsEntity> elements;

            public void setId(String id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setElements(List<ElementsEntity> elements) {
                this.elements = elements;
            }

            public String getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public List<ElementsEntity> getElements() {
                return elements;
            }

            public static class ElementsEntity implements Serializable{
                private String id;
                private String title;
                private String sub_title;
                private String type;
                private String photo;
                private String extend;
                private int index;

                public void setId(String id) {
                    this.id = id;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public void setSub_title(String sub_title) {
                    this.sub_title = sub_title;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public void setPhoto(String photo) {
                    this.photo = photo;
                }

                public void setExtend(String extend) {
                    this.extend = extend;
                }

                public void setIndex(int index) {
                    this.index = index;
                }

                public String getId() {
                    return id;
                }

                public String getTitle() {
                    return title;
                }

                public String getSub_title() {
                    return sub_title;
                }

                public String getType() {
                    return type;
                }

                public String getPhoto() {
                    return photo;
                }

                public String getExtend() {
                    return extend;
                }

                public int getIndex() {
                    return index;
                }
            }
        }
    }
}
