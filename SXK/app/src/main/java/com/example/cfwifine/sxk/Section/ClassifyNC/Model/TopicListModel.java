package com.example.cfwifine.sxk.Section.ClassifyNC.Model;

import java.util.List;

/**
 * Created by cfwifine on 16/12/12.
 */

public class TopicListModel {

    /**
     * code : 1
     * total : 2
     * topicList : [{"userid":1,"nickname":"纪长琼","headimgurl":"http://wx.qlogo.cn/mmopen/qSacGXhAgjw6ichGbkgUcbPUryMMyt3GGUfmb3QLjdhmusczx97wB7zrlo2tQmJbdsvibbb0uj32pAHoFjBlxUM4qlgrPxwDibf/0","content":"这是一个测试","imgList":null,"moduleid":1,"likeList":[],"commentList":[],"status":1,"topicid":4,"createtime":1481541365,"updatetime":1481541365},{"userid":1,"nickname":"纪长琼","headimgurl":"http://wx.qlogo.cn/mmopen/qSacGXhAgjw6ichGbkgUcbPUryMMyt3GGUfmb3QLjdhmusczx97wB7zrlo2tQmJbdsvibbb0uj32pAHoFjBlxUM4qlgrPxwDibf/0","content":"这是一个测试","imgList":null,"moduleid":3,"likeList":[{"userid":1,"nickname":"纪长琼"},{"userid":4,"nickname":"杨伟康"}],"commentList":[{"userid":4,"nickname":"杨伟康","comment":"Ghjghjghghjgkjh"},{"userid":4,"nickname":"杨伟康","comment":"Sdjlkfajsdfkl;jalksdjf"},{"userid":4,"nickname":"杨伟康","comment":"Fakldsjflkajsdklf"},{"userid":4,"nickname":"杨伟康","comment":"Ksjdfkljkldsfj"},{"userid":4,"nickname":"杨伟康","comment":"Sdflkal;sell;f"},{"userid":4,"nickname":"杨伟康","comment":"Dara's"}],"status":1,"topicid":3,"createtime":1481510748,"updatetime":1481539993}]
     */

    private int code;
    private int total;
    private List<TopicListBean> topicList;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TopicListBean> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<TopicListBean> topicList) {
        this.topicList = topicList;
    }

    public static class TopicListBean {
        /**
         * userid : 1
         * nickname : 纪长琼
         * headimgurl : http://wx.qlogo.cn/mmopen/qSacGXhAgjw6ichGbkgUcbPUryMMyt3GGUfmb3QLjdhmusczx97wB7zrlo2tQmJbdsvibbb0uj32pAHoFjBlxUM4qlgrPxwDibf/0
         * content : 这是一个测试
         * imgList : null
         * moduleid : 1
         * likeList : []
         * commentList : []
         * status : 1
         * topicid : 4
         * createtime : 1481541365
         * updatetime : 1481541365
         */

        private int userid;
        private String nickname;
        private String headimgurl;
        private String content;
        private Object imgList;
        private int moduleid;
        private int status;
        private int topicid;
        private int createtime;
        private int updatetime;
        private List<?> likeList;
        private List<?> commentList;

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getImgList() {
            return imgList;
        }

        public void setImgList(Object imgList) {
            this.imgList = imgList;
        }

        public int getModuleid() {
            return moduleid;
        }

        public void setModuleid(int moduleid) {
            this.moduleid = moduleid;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getTopicid() {
            return topicid;
        }

        public void setTopicid(int topicid) {
            this.topicid = topicid;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public int getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(int updatetime) {
            this.updatetime = updatetime;
        }

        public List<?> getLikeList() {
            return likeList;
        }

        public void setLikeList(List<?> likeList) {
            this.likeList = likeList;
        }

        public List<?> getCommentList() {
            return commentList;
        }

        public void setCommentList(List<?> commentList) {
            this.commentList = commentList;
        }
    }
}
