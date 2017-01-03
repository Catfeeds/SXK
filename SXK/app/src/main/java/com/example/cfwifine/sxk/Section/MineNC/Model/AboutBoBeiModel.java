package com.example.cfwifine.sxk.Section.MineNC.Model;

/**
 * Created by cfwifine on 17/1/3.
 */

public class AboutBoBeiModel {


    /**
     * code : 1
     * setup : {"setupid":1,"content":"<p>fdafafdafs1<\/p><p style=\"text-align: center;\"><img style=\"width: 443px; height: 665px;\" alt=\"IMG_2933.JPG!thumbnail.jpg\" src=\"http://ohqqvdngk.bkt.clouddn.com/1481726004.jpg\" title=\"1481726004.jpg\" height=\"665\" width=\"443\"/><\/p>","createtime":1481725263,"updatetime":1481868922}
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
         * content : <p>fdafafdafs1</p><p style="text-align: center;"><img style="width: 443px; height: 665px;" alt="IMG_2933.JPG!thumbnail.jpg" src="http://ohqqvdngk.bkt.clouddn.com/1481726004.jpg" title="1481726004.jpg" height="665" width="443"/></p>
         * createtime : 1481725263
         * updatetime : 1481868922
         */

        private int setupid;
        private String content;
        private int createtime;
        private int updatetime;

        public int getSetupid() {
            return setupid;
        }

        public void setSetupid(int setupid) {
            this.setupid = setupid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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
