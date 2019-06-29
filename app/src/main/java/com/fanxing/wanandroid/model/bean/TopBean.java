package com.fanxing.wanandroid.model.bean;

import java.util.List;

/**
 * 顶置文章的实体类
 * @author 繁星
 */
public class TopBean {
    /**
     * data : [{"apkLink":"","author":"鸿洋","chapterId":298,"chapterName":"我的博客","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8004,"link":"http://www.wanandroid.com/blog/show/2030","niceDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1561650768000,"superChapterId":298,"superChapterName":"原创文章","tags":[],"title":"6月底收租 欢迎赞助本站","type":1,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":448,"chapterName":"慕课网","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8638,"link":"https://www.imooc.com/learn/1141","niceDate":"2019-06-25","origin":"","prefix":"","projectLink":"","publishTime":1561431761000,"superChapterId":448,"superChapterName":"视频","tags":[],"title":"给大家录制的课程| 自定义、FlexBoxLayout、FlexBoxManager实现流式布局","type":1,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"xiaoyang","chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>可以仔细思考下ANR是如何产生的？<\/p><br><p>我在 UI 线程执行一个非常耗时的操作一定会出现 ANR 弹框吗？<\/p><br><p>本周 3/3&nbsp;<\/p>","envelopePic":"","fresh":false,"id":8650,"link":"https://www.wanandroid.com/wenda/show/8650","niceDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1561650610000,"superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问 ANR的产生的原理是什么，AMS中涉及ANR的代码有哪些？","type":1,"userId":2,"visible":1,"zan":6},{"apkLink":"","author":"xiaoyang","chapterId":472,"chapterName":"测试","collect":false,"courseId":13,"desc":"<p><img src=\"https://www.wanandroid.com/blogimgs/edcd445d-bdbc-42a2-a982-7113245b9920.png\" style=\"max-width:100%;max-height:100%\"><br><\/p><br><p>可以填写昵称了。<\/p><br><p>右上角依然显示用户名，因为只有你自己能看到，其次我担心大家太容易忘记用户名。<\/p><br><p>问答模块，全部显示昵称，更新昵称后会同步历史问答。<\/p><br><p>欢迎测试，有问题及时反馈。<\/p>","envelopePic":"","fresh":true,"id":8652,"link":"https://www.wanandroid.com/wenda/show/8652","niceDate":"20小时前","origin":"","prefix":"","projectLink":"","publishTime":1561741253000,"superChapterId":440,"superChapterName":"问答","tags":[],"title":"新增功能 | 可以更新昵称啦！","type":1,"userId":2,"visible":1,"zan":3}]
     * errorCode : 0
     * errorMsg :
     */

    private int errorCode;
    private String errorMsg;
    private List<DataBean> data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * apkLink :
         * author : 鸿洋
         * chapterId : 298
         * chapterName : 我的博客
         * collect : false
         * courseId : 13
         * desc :
         * envelopePic :
         * fresh : false
         * id : 8004
         * link : http://www.wanandroid.com/blog/show/2030
         * niceDate : 1天前
         * origin :
         * prefix :
         * projectLink :
         * publishTime : 1561650768000
         * superChapterId : 298
         * superChapterName : 原创文章
         * tags : []
         * title : 6月底收租 欢迎赞助本站
         * type : 1
         * userId : -1
         * visible : 1
         * zan : 0
         */

        private String apkLink;
        private String author;
        private int chapterId;
        private String chapterName;
        private boolean collect;
        private int courseId;
        private String desc;
        private String envelopePic;
        private boolean fresh;
        private int id;
        private String link;
        private String niceDate;
        private String origin;
        private String prefix;
        private String projectLink;
        private long publishTime;
        private int superChapterId;
        private String superChapterName;
        private String title;
        private int type;
        private int userId;
        private int visible;
        private int zan;
        private List<?> tags;

        public String getApkLink() {
            return apkLink;
        }

        public void setApkLink(String apkLink) {
            this.apkLink = apkLink;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getChapterId() {
            return chapterId;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public boolean isCollect() {
            return collect;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public boolean isFresh() {
            return fresh;
        }

        public void setFresh(boolean fresh) {
            this.fresh = fresh;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getProjectLink() {
            return projectLink;
        }

        public void setProjectLink(String projectLink) {
            this.projectLink = projectLink;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public int getSuperChapterId() {
            return superChapterId;
        }

        public void setSuperChapterId(int superChapterId) {
            this.superChapterId = superChapterId;
        }

        public String getSuperChapterName() {
            return superChapterName;
        }

        public void setSuperChapterName(String superChapterName) {
            this.superChapterName = superChapterName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }

        public List<?> getTags() {
            return tags;
        }

        public void setTags(List<?> tags) {
            this.tags = tags;
        }
    }
}
