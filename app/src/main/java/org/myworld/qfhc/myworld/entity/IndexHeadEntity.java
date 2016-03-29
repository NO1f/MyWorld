package org.myworld.qfhc.myworld.entity;

import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/3/28 16:42
 * @备注：
 */
public class IndexHeadEntity {


    /**
     * code : 200
     * data : {"tabs":[{"url":"lukou://albumcategory","imageUrl":"","name":"精选","id":32},{"url":"lukou://albumcategory","imageUrl":"","name":"路友说","id":46},{"url":"lukou://albumcategory","imageUrl":"","name":"服饰","id":30},{"url":"lukou://albumcategory","imageUrl":"http://img1.lukou.com/static/p/icons/meizhuang.png","name":"美妆","id":2},{"url":"lukou://albumcategory","imageUrl":"","name":"生活家居","id":29},{"url":"lukou://albumcategory","imageUrl":"http://img1.lukou.com/static/p/icons/meishi.png","name":"美食","id":4}],"banner":[{"url":"http://www.lukou.com/wb/featured/1","imageUrl":"http://img1.lukou.com/static/p/blog/medium/0004/80/74/03/4807403.jpg"},{"url":"http://www.lukou.com/wb/featured/3","imageUrl":"http://img1.lukou.com/static/p/blog/medium/0004/79/94/34/4799434.jpg"},{"url":"http://www.lukou.com/wb/test?target=main&tpid=4&sharable=true","imageUrl":"http://img1.lukou.com/static/p/blog/large/0004/74/58/43/4745843.jpg"}],"lives":{"count":2,"authorAvatar":"http://img1.lukou.com/static/p/blog/medium/0004/55/92/55/4559255.jpg ","list":[{"url":"lukou://feedinfo?feedID=5172756","title":"5个手残党也能搞定的抹茶料理"},{"url":"lukou://feedinfo?feedID=5140133","title":"小个子女生旅行穿搭指南"}],"title":"路口头条"}}
     */

    private int code;
    /**
     * tabs : [{"url":"lukou://albumcategory","imageUrl":"","name":"精选","id":32},{"url":"lukou://albumcategory","imageUrl":"","name":"路友说","id":46},{"url":"lukou://albumcategory","imageUrl":"","name":"服饰","id":30},{"url":"lukou://albumcategory","imageUrl":"http://img1.lukou.com/static/p/icons/meizhuang.png","name":"美妆","id":2},{"url":"lukou://albumcategory","imageUrl":"","name":"生活家居","id":29},{"url":"lukou://albumcategory","imageUrl":"http://img1.lukou.com/static/p/icons/meishi.png","name":"美食","id":4}]
     * banner : [{"url":"http://www.lukou.com/wb/featured/1","imageUrl":"http://img1.lukou.com/static/p/blog/medium/0004/80/74/03/4807403.jpg"},{"url":"http://www.lukou.com/wb/featured/3","imageUrl":"http://img1.lukou.com/static/p/blog/medium/0004/79/94/34/4799434.jpg"},{"url":"http://www.lukou.com/wb/test?target=main&tpid=4&sharable=true","imageUrl":"http://img1.lukou.com/static/p/blog/large/0004/74/58/43/4745843.jpg"}]
     * lives : {"count":2,"authorAvatar":"http://img1.lukou.com/static/p/blog/medium/0004/55/92/55/4559255.jpg ","list":[{"url":"lukou://feedinfo?feedID=5172756","title":"5个手残党也能搞定的抹茶料理"},{"url":"lukou://feedinfo?feedID=5140133","title":"小个子女生旅行穿搭指南"}],"title":"路口头条"}
     */

    private DataEntity data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        /**
         * count : 2
         * authorAvatar : http://img1.lukou.com/static/p/blog/medium/0004/55/92/55/4559255.jpg
         * list : [{"url":"lukou://feedinfo?feedID=5172756","title":"5个手残党也能搞定的抹茶料理"},{"url":"lukou://feedinfo?feedID=5140133","title":"小个子女生旅行穿搭指南"}]
         * title : 路口头条
         */

        private LivesEntity lives;
        /**
         * url : lukou://albumcategory
         * imageUrl :
         * name : 精选
         * id : 32
         */

        private List<TabsEntity> tabs;
        /**
         * url : http://www.lukou.com/wb/featured/1
         * imageUrl : http://img1.lukou.com/static/p/blog/medium/0004/80/74/03/4807403.jpg
         */

        private List<BannerEntity> banner;

        public void setLives(LivesEntity lives) {
            this.lives = lives;
        }

        public void setTabs(List<TabsEntity> tabs) {
            this.tabs = tabs;
        }

        public void setBanner(List<BannerEntity> banner) {
            this.banner = banner;
        }

        public LivesEntity getLives() {
            return lives;
        }

        public List<TabsEntity> getTabs() {
            return tabs;
        }

        public List<BannerEntity> getBanner() {
            return banner;
        }

        public static class LivesEntity {
            private int count;
            private String authorAvatar;
            private String title;
            /**
             * url : lukou://feedinfo?feedID=5172756
             * title : 5个手残党也能搞定的抹茶料理
             */

            private List<ListEntity> list;

            public void setCount(int count) {
                this.count = count;
            }

            public void setAuthorAvatar(String authorAvatar) {
                this.authorAvatar = authorAvatar;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setList(List<ListEntity> list) {
                this.list = list;
            }

            public int getCount() {
                return count;
            }

            public String getAuthorAvatar() {
                return authorAvatar;
            }

            public String getTitle() {
                return title;
            }

            public List<ListEntity> getList() {
                return list;
            }

            public static class ListEntity {
                private String url;
                private String title;

                public void setUrl(String url) {
                    this.url = url;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getUrl() {
                    return url;
                }

                public String getTitle() {
                    return title;
                }
            }
        }

        public static class TabsEntity {
            private String url;
            private String imageUrl;
            private String name;
            private int id;

            public void setUrl(String url) {
                this.url = url;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public String getName() {
                return name;
            }

            public int getId() {
                return id;
            }
        }

        public static class BannerEntity {
            private String url;
            private String imageUrl;

            public void setUrl(String url) {
                this.url = url;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getUrl() {
                return url;
            }

            public String getImageUrl() {
                return imageUrl;
            }
        }
    }
}
