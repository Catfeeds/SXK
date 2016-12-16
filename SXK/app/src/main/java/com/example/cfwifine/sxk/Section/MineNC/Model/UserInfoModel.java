package com.example.cfwifine.sxk.Section.MineNC.Model;

/**
 * Created by cfwifine on 16/12/10.
 */

public class UserInfoModel {


    /**
     * code : 1
     * user : {"mobile":"15539910985","password":"$2y$10$gRQSTvbUHLfXF1SEv2WfyeFko5jOX/m3TllbS.ruXn3TqVqT4SPEe","userid":10,"createtime":1480922278,"updatetime":1481875357,"nickname":"杨伟康","sex":1,"birthday":946656000,"profile":"测试手册按时发生大幅度","role":1,"headimgurl":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_20161216160154"}
     */

    private int code;
    private UserBean user;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * mobile : 15539910985
         * password : $2y$10$gRQSTvbUHLfXF1SEv2WfyeFko5jOX/m3TllbS.ruXn3TqVqT4SPEe
         * userid : 10
         * createtime : 1480922278
         * updatetime : 1481875357
         * nickname : 杨伟康
         * sex : 1
         * birthday : 946656000
         * profile : 测试手册按时发生大幅度
         * role : 1
         * headimgurl : http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_20161216160154
         */

        private String mobile;
        private String password;
        private int userid;
        private int createtime;
        private int updatetime;
        private String nickname;
        private int sex;
        private int birthday;
        private String profile;
        private int role;
        private String headimgurl;

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
    }
}
