package com.example.cfwifine.sxk.Section.ClassifyNC.Model;

import java.util.List;

/**
 * Created by cfwifine on 16/12/12.
 */

public class CommunityTopicListModel {

    /**
     * code : 1
     * total : 4
     * moduleList : [{"name":"啵呗秀","img":"Shexiangke_jcq::communityModule_1481426307937","description":"啵呗秀一秀","sort":1,"status":1,"moduleid":4,"createtime":1481425766,"updatetime":1481463947},{"name":"社群活动","img":"Shexiangke_jcq::communityModule_1481426246135","description":"社群活动相关","sort":2,"status":1,"moduleid":3,"createtime":1481425739,"updatetime":1481426821},{"name":"WATCH","img":"Shexiangke_jcq::communityModule_1481426226339","description":"手表相关话题","sort":3,"status":1,"moduleid":2,"createtime":1481425680,"updatetime":1481426819},{"name":"BAG","img":"Shexiangke_jcq::communityModule_1481425928798","description":"包包的相关话题","sort":4,"status":1,"moduleid":1,"createtime":1481425535,"updatetime":1481426816}]
     */

    private int code;
    private int total;
    private List<ModuleListBean> moduleList;

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

    public List<ModuleListBean> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<ModuleListBean> moduleList) {
        this.moduleList = moduleList;
    }

    public static class ModuleListBean {
        /**
         * name : 啵呗秀
         * img : Shexiangke_jcq::communityModule_1481426307937
         * description : 啵呗秀一秀
         * sort : 1
         * status : 1
         * moduleid : 4
         * createtime : 1481425766
         * updatetime : 1481463947
         */

        private String name;
        private String img;
        private String description;
        private int sort;
        private int status;
        private int moduleid;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public int getModuleid() {
            return moduleid;
        }

        public void setModuleid(int moduleid) {
            this.moduleid = moduleid;
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
