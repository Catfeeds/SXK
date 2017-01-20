package com.example.cfwifine.sxk.Section.MineNC.Controller.MineFollow.Model;

import java.util.List;

/**
 * Created by cfwifine on 17/1/20.
 */

public class FollowListModel {

    /**
     * code : 1
     * follow : {"followid":10,"userid":10,"list":[4],"createtime":1484878224,"updatetime":1484885204,"userList":[{"mobile":"15659568279","password":"$2y$10$VZtILutAH4ZwzmRPzwgAjeMi5ntU35y43M5dWZ6ZV1K4hO2iyCAoG","userid":4,"createtime":1480911994,"updatetime":1482739983,"nickname":"mdzz","role":1,"headimgurl":"http://ohqqvdngk.bkt.clouddn.com/FvsTxttqkUOzjStlN_QRyUbRNqRP","sex":2,"birthday":930758400,"profile":"室内设计"}]}
     */

    private int code;
    private FollowBean follow;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public FollowBean getFollow() {
        return follow;
    }

    public void setFollow(FollowBean follow) {
        this.follow = follow;
    }

    public static class FollowBean {
        /**
         * followid : 10
         * userid : 10
         * list : [4]
         * createtime : 1484878224
         * updatetime : 1484885204
         * userList : [{"mobile":"15659568279","password":"$2y$10$VZtILutAH4ZwzmRPzwgAjeMi5ntU35y43M5dWZ6ZV1K4hO2iyCAoG","userid":4,"createtime":1480911994,"updatetime":1482739983,"nickname":"mdzz","role":1,"headimgurl":"http://ohqqvdngk.bkt.clouddn.com/FvsTxttqkUOzjStlN_QRyUbRNqRP","sex":2,"birthday":930758400,"profile":"室内设计"}]
         */

        private int followid;
        private int userid;
        private int createtime;
        private int updatetime;
        private List<Integer> list;
        private List<UserListBean> userList;

        public int getFollowid() {
            return followid;
        }

        public void setFollowid(int followid) {
            this.followid = followid;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
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

        public List<Integer> getList() {
            return list;
        }

        public void setList(List<Integer> list) {
            this.list = list;
        }

        public List<UserListBean> getUserList() {
            return userList;
        }

        public void setUserList(List<UserListBean> userList) {
            this.userList = userList;
        }

        public static class UserListBean {
            /**
             * mobile : 15659568279
             * password : $2y$10$VZtILutAH4ZwzmRPzwgAjeMi5ntU35y43M5dWZ6ZV1K4hO2iyCAoG
             * userid : 4
             * createtime : 1480911994
             * updatetime : 1482739983
             * nickname : mdzz
             * role : 1
             * headimgurl : http://ohqqvdngk.bkt.clouddn.com/FvsTxttqkUOzjStlN_QRyUbRNqRP
             * sex : 2
             * birthday : 930758400
             * profile : 室内设计
             */

            private String mobile;
            private String password;
            private int userid;
            private int createtime;
            private int updatetime;
            private String nickname;
            private int role;
            private String headimgurl;
            private int sex;
            private int birthday;
            private String profile;

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
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

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getRole() {
                return role;
            }

            public void setRole(int role) {
                this.role = role;
            }

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
                this.headimgurl = headimgurl;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getBirthday() {
                return birthday;
            }

            public void setBirthday(int birthday) {
                this.birthday = birthday;
            }

            public String getProfile() {
                return profile;
            }

            public void setProfile(String profile) {
                this.profile = profile;
            }
        }
    }
}
