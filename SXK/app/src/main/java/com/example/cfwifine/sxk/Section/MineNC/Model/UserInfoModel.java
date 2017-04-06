package com.example.cfwifine.sxk.Section.MineNC.Model;

/**
 * Created by cfwifine on 16/12/10.
 */

public class UserInfoModel {


    /**
     * code : 1
     * user : {"mobile":"15539910985","password":"$2y$10$A5hqDBocEo/MaPoFgMM9He9qv42fnht0UwqdKVXcSdE6igOmPcMrq","userid":10,"createtime":1480922278,"updatetime":1491037716,"nickname":"肥仔","sex":1,"birthday":694195200,"profile":"哈哈","role":4,"headimgurl":"http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_20170307171545","buyer":{"name":"ghjn","idNumber":"43138119870827275X","front":"buyer_front_1486370551","back":"buyer_back_1486370551"},"balance":0,"seller":{"name":"接口","idNumber":"411381199301107614","front":"seller_front_1488251082","back":"seller_back_1488251082"},"score":0.06,"invitationCode":"1805301"}
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
         * password : $2y$10$A5hqDBocEo/MaPoFgMM9He9qv42fnht0UwqdKVXcSdE6igOmPcMrq
         * userid : 10
         * createtime : 1480922278
         * updatetime : 1491037716
         * nickname : 肥仔
         * sex : 1
         * birthday : 694195200
         * profile : 哈哈
         * role : 4
         * headimgurl : http://ohqqvdngk.bkt.clouddn.com/sxk_userPic_20170307171545
         * buyer : {"name":"ghjn","idNumber":"43138119870827275X","front":"buyer_front_1486370551","back":"buyer_back_1486370551"}
         * balance : 0
         * seller : {"name":"接口","idNumber":"411381199301107614","front":"seller_front_1488251082","back":"seller_back_1488251082"}
         * score : 0.06
         * invitationCode : 1805301
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
        private BuyerBean buyer;
        private int balance;
        private SellerBean seller;
        private double score;
        private String invitationCode;

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

        public BuyerBean getBuyer() {
            return buyer;
        }

        public void setBuyer(BuyerBean buyer) {
            this.buyer = buyer;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public SellerBean getSeller() {
            return seller;
        }

        public void setSeller(SellerBean seller) {
            this.seller = seller;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getInvitationCode() {
            return invitationCode;
        }

        public void setInvitationCode(String invitationCode) {
            this.invitationCode = invitationCode;
        }

        public static class BuyerBean {
            /**
             * name : ghjn
             * idNumber : 43138119870827275X
             * front : buyer_front_1486370551
             * back : buyer_back_1486370551
             */

            private String name;
            private String idNumber;
            private String front;
            private String back;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIdNumber() {
                return idNumber;
            }

            public void setIdNumber(String idNumber) {
                this.idNumber = idNumber;
            }

            public String getFront() {
                return front;
            }

            public void setFront(String front) {
                this.front = front;
            }

            public String getBack() {
                return back;
            }

            public void setBack(String back) {
                this.back = back;
            }
        }

        public static class SellerBean {
            /**
             * name : 接口
             * idNumber : 411381199301107614
             * front : seller_front_1488251082
             * back : seller_back_1488251082
             */

            private String name;
            private String idNumber;
            private String front;
            private String back;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIdNumber() {
                return idNumber;
            }

            public void setIdNumber(String idNumber) {
                this.idNumber = idNumber;
            }

            public String getFront() {
                return front;
            }

            public void setFront(String front) {
                this.front = front;
            }

            public String getBack() {
                return back;
            }

            public void setBack(String back) {
                this.back = back;
            }
        }
    }
}
