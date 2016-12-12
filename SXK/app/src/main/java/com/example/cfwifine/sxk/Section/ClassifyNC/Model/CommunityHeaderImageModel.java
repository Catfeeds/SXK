package com.example.cfwifine.sxk.Section.ClassifyNC.Model;

/**
 * Created by cfwifine on 16/12/12.
 */

public class CommunityHeaderImageModel {

    /**
     * code : 1
     * setup : {"setupid":1,"img":"Shexiangke_jcq::communitySetup_img1481468567524","createtime":1481468003,"updatetime":1481468068}
     */

    private int code;
    private SetupBean setup;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public SetupBean getSetup() {
        return setup;
    }

    public void setSetup(SetupBean setup) {
        this.setup = setup;
    }

    public static class SetupBean {
        /**
         * setupid : 1
         * img : Shexiangke_jcq::communitySetup_img1481468567524
         * createtime : 1481468003
         * updatetime : 1481468068
         */

        private int setupid;
        private String img;
        private int createtime;
        private int updatetime;

        public int getSetupid() {
            return setupid;
        }

        public void setSetupid(int setupid) {
            this.setupid = setupid;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
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
    }
}
