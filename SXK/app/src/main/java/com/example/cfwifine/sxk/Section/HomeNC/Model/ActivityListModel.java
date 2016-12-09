package com.example.cfwifine.sxk.Section.HomeNC.Model;

import java.util.List;

/**
 * Created by cfwifine on 16/12/9.
 */

public class ActivityListModel {

    /**
     * code : 1
     * total : 1
     * activityList : [{"name":"软件园二期专场交友派对等你来嗨","img":"Shexiangke_jcq::finance_1481200306936","time":1481887800,"place":"福建省厦门市思明区软件园二期东门","content":"<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 这场活动是专门为软二的白领们打造的，不管是游戏环节还是现场的氛围布置，我们的工作团队都经过多次探讨、反复敲定，尤其是游戏环节是需要男女生共同搭档来完成的，相信会让大家耳目一新。\u201d工作人员透露，除了年轻朋友们喜爱的\u201c踩气球\u201d，还有迅速点燃气氛的\u201c纸杯传递\u201d、考验彼此默契度的\u201c就不听指挥\u201d等等。<\/p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 值得一提的是，当晚的派对还将穿插一对一的聊天交友环节，工作人员表示，在以往的交友形式当中，一对一的\u201c8分钟约会\u201d最受都市年轻人的喜爱，这次的派对也将结合这一形式，将8分钟缩短成5分钟，让他们在快速了解对方的同时能有更多的时间接触到现场的每一位异性。而在活动的最后，主办方还开放了3个告白名额给现场想要表白的\u201c勇士\u201d，\u201c在以往的活动中，不少牵手成功的单身朋友们都是靠最后这个需要勇气的环节，希望这个环节也能在当天晚上助他们一臂之力。\u201d<\/p><p>　　同时，当晚的派对还有精致可口的西式茶歇可以享用，软件园的\u201c一起茶吧\u201d为参加活动的嘉宾们准备了丰富多样的西点，主办方还提供了包括电风扇、公仔玩偶、屈臣氏化妆品等在内的丰富奖品。<\/p>","sort":4,"status":1,"activityid":4,"createtime":1481199814,"updatetime":1481202905}]
     */

    private int code;
    private int total;
    private List<ActivityListBean> activityList;

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

    public List<ActivityListBean> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<ActivityListBean> activityList) {
        this.activityList = activityList;
    }

    public static class ActivityListBean {
        /**
         * name : 软件园二期专场交友派对等你来嗨
         * img : Shexiangke_jcq::finance_1481200306936
         * time : 1481887800
         * place : 福建省厦门市思明区软件园二期东门
         * content : <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 这场活动是专门为软二的白领们打造的，不管是游戏环节还是现场的氛围布置，我们的工作团队都经过多次探讨、反复敲定，尤其是游戏环节是需要男女生共同搭档来完成的，相信会让大家耳目一新。”工作人员透露，除了年轻朋友们喜爱的“踩气球”，还有迅速点燃气氛的“纸杯传递”、考验彼此默契度的“就不听指挥”等等。</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 值得一提的是，当晚的派对还将穿插一对一的聊天交友环节，工作人员表示，在以往的交友形式当中，一对一的“8分钟约会”最受都市年轻人的喜爱，这次的派对也将结合这一形式，将8分钟缩短成5分钟，让他们在快速了解对方的同时能有更多的时间接触到现场的每一位异性。而在活动的最后，主办方还开放了3个告白名额给现场想要表白的“勇士”，“在以往的活动中，不少牵手成功的单身朋友们都是靠最后这个需要勇气的环节，希望这个环节也能在当天晚上助他们一臂之力。”</p><p>　　同时，当晚的派对还有精致可口的西式茶歇可以享用，软件园的“一起茶吧”为参加活动的嘉宾们准备了丰富多样的西点，主办方还提供了包括电风扇、公仔玩偶、屈臣氏化妆品等在内的丰富奖品。</p>
         * sort : 4
         * status : 1
         * activityid : 4
         * createtime : 1481199814
         * updatetime : 1481202905
         */

        private String name;
        private String img;
        private int time;
        private String place;
        private String content;
        private int sort;
        private int status;
        private int activityid;
        private int createtime;
        private int updatetime;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
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

        public int getActivityid() {
            return activityid;
        }

        public void setActivityid(int activityid) {
            this.activityid = activityid;
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
