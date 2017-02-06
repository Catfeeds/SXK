package com.example.cfwifine.sxk.Section.MineNC.Model;

/**
 * Created by cfwifine on 17/2/6.
 */

public class SharePresentModel {

    /**
     * code : 1
     * setup : {"setupid":1,"content":"<p>dfshs"}
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
         * content : <p>dfshs
         */

        private int setupid;
        private String content;

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
    }
}
