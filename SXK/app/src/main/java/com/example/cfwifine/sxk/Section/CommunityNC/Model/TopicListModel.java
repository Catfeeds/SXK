package com.example.cfwifine.sxk.Section.CommunityNC.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Yang on 2016/12/15.
 */

public class TopicListModel implements Serializable{

    /**
     * code : 1
     * total : 42
     * topicList : [{"userid":4,"nickname":"杨伟康","headimgurl":null,"content":"我们的","imgList":[{"image":"FvpCpGbSKJrNGjXg43gjmrOI8_Ia"}],"moduleid":4,"likeList":[{"userid":4,"nickname":"杨伟康"}],"commentList":[{"userid":4,"nickname":"杨伟康","comment":"我的地方反反复复风格"},{"userid":4,"nickname":"杨伟康","comment":"i 看见大家简单简单简单简单就吵架"},{"userid":4,"nickname":"杨伟康","comment":"想到当年妇女大家简单简单减肥减肥交界处"}],"status":1,"topicid":79,"createtime":1481774109,"updatetime":1481780753},{"userid":4,"nickname":"杨伟康","headimgurl":null,"content":"我","imgList":[{"image":"FvpCpGbSKJrNGjXg43gjmrOI8_Ia"}],"moduleid":4,"likeList":[{"userid":4,"nickname":"杨伟康"}],"commentList":[{"userid":4,"nickname":"杨伟康","comment":"到底简单就简单简单简单解放军"},{"userid":4,"nickname":"杨伟康","comment":"回家看见简单简单就"}],"status":1,"topicid":78,"createtime":1481773878,"updatetime":1481780785},{"userid":4,"nickname":"杨伟康","headimgurl":null,"content":"国会反对","imgList":[{"image":"FoeRnSv-kK_9h4QqvpEAmPljMvW6"},{"image":"FoeRnSv-kK_9h4QqvpEAmPljMvW6"},{"image":"FqQSZBySEPyyaVHv06i-pCOrXJG0"}],"moduleid":4,"likeList":[{"userid":4,"nickname":"杨伟康"}],"commentList":[],"status":1,"topicid":77,"createtime":1481773546,"updatetime":1481775268},{"userid":10,"nickname":"okile1","headimgurl":null,"content":"啊啊啊KKK","imgList":[{"image":"sxk_userPic_201612151132470"},{"image":"sxk_userPic_201612151132511"}],"moduleid":3,"likeList":[{"userid":4,"nickname":"杨伟康"}],"commentList":[],"status":1,"topicid":76,"createtime":1481772774,"updatetime":1481775291},{"userid":10,"nickname":"okile1","headimgurl":null,"content":"啊啊啊KKK","imgList":[{"image":"sxk_userPic_201612151132120"},{"image":"sxk_userPic_201612151132151"}],"moduleid":3,"likeList":[{"userid":4,"nickname":"杨伟康"}],"commentList":[],"status":1,"topicid":75,"createtime":1481772740,"updatetime":1481775296},{"userid":10,"nickname":"okile1","headimgurl":null,"content":"knoll","imgList":[{"image":"sxk_userPic_201612151127400"},{"image":"sxk_userPic_201612151127431"}],"moduleid":4,"likeList":[],"commentList":[],"status":1,"topicid":74,"createtime":1481772467,"updatetime":1481772467},{"userid":10,"nickname":"okile1","headimgurl":null,"content":"knoll","imgList":[{"image":"sxk_userPic_201612151127290"},{"image":"sxk_userPic_201612151127331"}],"moduleid":4,"likeList":[],"commentList":[],"status":1,"topicid":73,"createtime":1481772456,"updatetime":1481772456},{"userid":10,"nickname":"okile1","headimgurl":null,"content":"knoll","imgList":[{"image":"sxk_userPic_201612151127170"},{"image":"sxk_userPic_201612151127211"}],"moduleid":4,"likeList":[],"commentList":[],"status":1,"topicid":72,"createtime":1481772444,"updatetime":1481772444},{"userid":10,"nickname":"okile1","headimgurl":null,"content":"来咯哦哦找我","imgList":[{"image":"sxk_userPic_201612151124240"},{"image":"sxk_userPic_201612151124301"}],"moduleid":3,"likeList":[],"commentList":[],"status":1,"topicid":71,"createtime":1481772274,"updatetime":1481772274}]
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
         * userid : 4
         * nickname : 杨伟康
         * headimgurl : null
         * content : 我们的
         * imgList : [{"image":"FvpCpGbSKJrNGjXg43gjmrOI8_Ia"}]
         * moduleid : 4
         * likeList : [{"userid":4,"nickname":"杨伟康"}]
         * commentList : [{"userid":4,"nickname":"杨伟康","comment":"我的地方反反复复风格"},{"userid":4,"nickname":"杨伟康","comment":"i 看见大家简单简单简单简单就吵架"},{"userid":4,"nickname":"杨伟康","comment":"想到当年妇女大家简单简单减肥减肥交界处"}]
         * status : 1
         * topicid : 79
         * createtime : 1481774109
         * updatetime : 1481780753
         */

        private int userid;
        private String nickname;
        private String headimgurl;
        private String content;
        private int moduleid;
        private int status;
        private int topicid;
        private int createtime;
        private int updatetime;
        private List<ImgListBean> imgList;
        private List<LikeListBean> likeList;
        private List<CommentListBean> commentList;

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

        public List<ImgListBean> getImgList() {
            return imgList;
        }

        public void setImgList(List<ImgListBean> imgList) {
            this.imgList = imgList;
        }

        public List<LikeListBean> getLikeList() {
            return likeList;
        }

        public void setLikeList(List<LikeListBean> likeList) {
            this.likeList = likeList;
        }

        public List<CommentListBean> getCommentList() {
            return commentList;
        }

        public void setCommentList(List<CommentListBean> commentList) {
            this.commentList = commentList;
        }

        public static class ImgListBean{
            /**
             * image : FvpCpGbSKJrNGjXg43gjmrOI8_Ia
             */
            private String image;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }

        public static class LikeListBean {
            /**
             * userid : 4
             * nickname : 杨伟康
             */

            private int userid;
            private String nickname;

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
        }

        public static class CommentListBean {
            /**
             * userid : 4
             * nickname : 杨伟康
             * comment : 我的地方反反复复风格
             */

            private int userid;
            private String nickname;
            private String comment;

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

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }
        }
    }
}
