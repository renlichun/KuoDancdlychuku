package com.example.kuodan.model;

import java.util.List;

/**
 * @author 孙贝贝
 * @packagename com.example.kuodan.model
 * @date on 2020/4/15 14:08
 * @wechat 18813158027
 */
public class CinvList_Reslult {



    private String createDate;
    private String tf;
    private String mag;
    private String outQtyn;

    private List<ListBean> list;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getTf() {
        return tf;
    }

    public void setTf(String tf) {
        this.tf = tf;
    }

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }

    public String getOutQtyn() {
        return outQtyn;
    }

    public void setOutQtyn(String outQtyn) {
        this.outQtyn = outQtyn;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * dateTime : 2020-04-15
         * mainId : 98201807014
         * state : 11
         * custName : 舍弗勒
         */

        //
//                名称
//                规格/图号
//                数量
        //
//        rd.position_code as "positionCode" ,
//        rd.base_qtyn as "baseQtyn" ,
//        a.chukudanhao ,
//        a.code as "cinvCode" ,
//        a.name ,
//        inv.cinv_name as "cinvName" ,
//        inv.cinv_std as "cinvStd"
//        cinv_code as "cinvCode" , cinv_name as "cinvName" , cinv_std as "cinvStd" , cinvadd_code as "cinvaddCode"
        private String cinvName;
        private String cinvCode;
        private String cinvStd;
        private String cinvaddCode;

//        private String yztorf;//验证 是 否
//        private String dateTime;
//        private String mainId;
//        private int state;
//        private String custName;

        public String getCinvName() {
            return cinvName;
        }

        public void setCinvName(String cinvName) {
            this.cinvName = cinvName;
        }

        public String getCinvStd() {
            return cinvStd;
        }

        public void setCinvStd(String cinvStd) {
            this.cinvStd = cinvStd;
        }

//        public String getQty() {
//            return qty;
//        }
//
//        public void setQty(String qty) {
//            this.qty = qty;
//        }

//        public String getYztorf() {
//            return yztorf;
//        }
//
//        public void setYztorf(String yztorf) {
//            this.yztorf = yztorf;
//        }

        public String getCinvCode() {
            return cinvCode;
        }

        public void setCinvCode(String cinvCode) {
            this.cinvCode = cinvCode;
        }
//

        public String getCinvaddCode() {
            return cinvaddCode;
        }

        public void setCinvaddCode(String cinvaddCode) {
            this.cinvaddCode = cinvaddCode;
        }


//        public String getDateTime() {
//            return dateTime;
//        }
//
//        public void setDateTime(String dateTime) {
//            this.dateTime = dateTime;
//        }
//
//        public String getMainId() {
//            return mainId;
//        }
//
//        public void setMainId(String mainId) {
//            this.mainId = mainId;
//        }
//
//        public int getState() {
//            return state;
//        }
//
//        public void setState(int state) {
//            this.state = state;
//        }
//
//        public String getCustName() {
//            return custName;
//        }
//
//        public void setCustName(String custName) {
//            this.custName = custName;
//        }
    }
}
