package com.example.cfwifine.sxk.Section.MineNC.Model;

/**
 * Created by cfwifine on 16/12/15.
 */

public class UserProtocalModel {

    /**
     * code : 1
     * setup : {"setupid":1,"content":"<p>fd<\/p>","createtime":1481725697,"updatetime":1481725716}
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
         * content : <p>fd</p>
         * createtime : 1481725697
         * updatetime : 1481725716
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
