package com.example.cfwifine.sxk.Section.CommunityNC.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr.Yang on 2016/12/15.
 */

public class TopicListModel {

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
         * userid : 12
         * nickname :
         * headimgurl : http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_20170110103802
         * content : 啵呗
         * imgList : ["Fuz3YyfbB8IkNj5hTOxNdHM-EFb1","Fm2n3Iq_KTUWFejsudNGJ3BjUgLO","FqiDXvS3lXThkLcawPTp5fCPrmMW","Fny0RkfHsnApVmsqd2WMwIN6Vifj","ForBT3ekTM6NsMalBwwu313UR0f0","FrLnjF7CqBkdOUSxWDOTgr0IGEpL"]
         * moduleid : 4
         * likeList : [{"userid":23,"nickname":"It's me"}]
         * commentList : [{"userid":23,"nickname":"It's me","comment":"深刻"}]
         * status : 1
         * topicid : 168
         * createtime : 1484711100
         * updatetime : 1484711283
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
        private List<Object> imgList;
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

        public List<Object> getImgList() {
            return imgList;
        }

        public void setImgList(List<Object> imgList) {
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

        public static class LikeListBean {
            /**
             * userid : 23
             * nickname : It's me
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
             * userid : 23
             * nickname : It's me
             * comment : 深刻
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
