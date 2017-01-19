package com.example.cfwifine.sxk.Section.MineNC.Controller.MineServerCenter.Model;

import java.util.List;

/**
 * Created by cfwifine on 17/1/19.
 */

public class AnswerModel {

    /**
     * code : 1
     * total : 2
     * questionList : [{"name":"fdafdsa","content":"<p>fdafadfdaffdfdafdasfdafd，积分来打发了发送代理费附件是大量发了<br/><\/p>","sort":5,"status":1,"questionid":2,"createtime":1481716480,"updatetime":1481717017},{"name":"测试问答","content":"<p>发发发打发放大放大发放按法定放大<br/><\/p>","sort":0,"status":1,"questionid":3,"createtime":1481717054,"updatetime":1481726243}]
     */

    private int code;
    private int total;
    private List<QuestionListBean> questionList;

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

    public List<QuestionListBean> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionListBean> questionList) {
        this.questionList = questionList;
    }

    public static class QuestionListBean {
        /**
         * name : fdafdsa
         * content : <p>fdafadfdaffdfdafdasfdafd，积分来打发了发送代理费附件是大量发了<br/></p>
         * sort : 5
         * status : 1
         * questionid : 2
         * createtime : 1481716480
         * updatetime : 1481717017
         */

        private String name;
        private String content;
        private int sort;
        private int status;
        private int questionid;
        private int createtime;
        private int updatetime;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getQuestionid() {
            return questionid;
        }

        public void setQuestionid(int questionid) {
            this.questionid = questionid;
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
