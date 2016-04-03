package org.myworld.qfhc.myworld.entity;

import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/3 14:27
 * @备注：
 */
public class ThirdDetailEntity {

    private String content;
    private List<TagsEntity> tags;
    private String datestr;
    private List<PicsEntity> pics;
    private List<ProductEntity> product;
    private DynamicEntity dynamic;

    public ThirdDetailEntity(String content, List<TagsEntity> tags, String datestr, List<PicsEntity> pics, List<ProductEntity> product, DynamicEntity dynamic) {
        this.content = content;
        this.tags = tags;
        this.datestr = datestr;
        this.pics = pics;
        this.product = product;
        this.dynamic = dynamic;
    }

    public ThirdDetailEntity() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<TagsEntity> getTags() {
        return tags;
    }

    public void setTags(List<TagsEntity> tags) {
        this.tags = tags;
    }

    public String getDatestr() {
        return datestr;
    }

    public void setDatestr(String datestr) {
        this.datestr = datestr;
    }

    public List<PicsEntity> getPics() {
        return pics;
    }

    public void setPics(List<PicsEntity> pics) {
        this.pics = pics;
    }

    public List<ProductEntity> getProduct() {
        return product;
    }

    public void setProduct(List<ProductEntity> product) {
        this.product = product;
    }

    public DynamicEntity getDynamic() {
        return dynamic;
    }

    public void setDynamic(DynamicEntity dynamic) {
        this.dynamic = dynamic;
    }

    public static class TagsEntity {

        private String id;
        private String name;

        public TagsEntity() {
        }

        public TagsEntity(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class PicsEntity {

        private String url;

        public PicsEntity() {
        }

        public PicsEntity(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class DynamicEntity {

        private String likes;

        public DynamicEntity() {
        }

        public DynamicEntity(String likes) {
            this.likes = likes;
        }

        public String getLikes() {
            return likes;
        }

        public void setLikes(String likes) {
            this.likes = likes;
        }
    }

    public static class ProductEntity {

        private String title;
        private String price;
        private String url;
        private String pic;

        public ProductEntity() {
        }

        public ProductEntity(String title, String price, String url, String pic) {
            this.title = title;
            this.price = price;
            this.url = url;
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
