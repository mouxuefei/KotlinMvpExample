package com.exmple.baseprojectmvp.http;

/**
 * @version V1.0 <描述当前版本功能>
 * @FileName: dd.java
 * @author: villa_mou
 * @date: 07-14:31
 * @desc
 */
public class dd
{

    /**
     * code : 1
     * msg : ok
     * data : {"webUrlData":{"webUrlDomain":"http://meiqi.app.6lapp.cn:8081","paymentUrl":"/payment/index.html?ap=1","housesDetailsUrl":"/houses/detail.html?ap=1","activityDetailsUrl":"/activity/detail.html?ap=1","activitySignUrl":"/activity/sign.html?ap=1","goodsDetailUrl":"/flea/goods-detail.html?ap=1","goodsReportUrl":"/flea/report.html?ap=1","storyDetailUrl":"/story/detail.html?ap=1","topicDetailUrl":"/topic/detail.html?ap=1","userCleanUpUrl":"/user/cleanup/index.html?ap=1","userCleanUpDetailUrl":"/user/cleanup/detail.html?ap=1","userRepairUrl":"/user/repair/index.html?ap=1","userRepairDetailUrl":"/user/repair/detail.html?ap=1","userCommendUrl":"/user/commend/index.html?ap=1","userComplainUrl":"/user/complain/index.html?ap=1","userContractUrl":"/user/contract/index.html?ap=1","userEvaluateRecordUrl":"/user/evaluate-record/index.html?ap=1","userHouseMoveUrl":"/user/house-move/index.html?ap=1","userHouseMoveDetailUrl":"/user/house-move/detail.html?ap=1","userShopUrl":"/user/shop/index.html?ap=1","repairAboutUrl":"/user/repair/about.html?ap=1","clearAboutUrl":"/user/cleanup/about.html?ap=1","paymentRecordUrl":"/payment/record.html?ap=1","moveAboutUrl":"/user/house-move/about.html?ap=1","signDetailUrl":"/activity/sign-detail.html?ap=1","payDetailUrl":"/payment/detail.html?ap=1","servePayDetailUrl":"/pay/index.html?ap=1"}}
     */

    public int code;
    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * webUrlData : {"webUrlDomain":"http://meiqi.app.6lapp.cn:8081","paymentUrl":"/payment/index.html?ap=1","housesDetailsUrl":"/houses/detail.html?ap=1","activityDetailsUrl":"/activity/detail.html?ap=1","activitySignUrl":"/activity/sign.html?ap=1","goodsDetailUrl":"/flea/goods-detail.html?ap=1","goodsReportUrl":"/flea/report.html?ap=1","storyDetailUrl":"/story/detail.html?ap=1","topicDetailUrl":"/topic/detail.html?ap=1","userCleanUpUrl":"/user/cleanup/index.html?ap=1","userCleanUpDetailUrl":"/user/cleanup/detail.html?ap=1","userRepairUrl":"/user/repair/index.html?ap=1","userRepairDetailUrl":"/user/repair/detail.html?ap=1","userCommendUrl":"/user/commend/index.html?ap=1","userComplainUrl":"/user/complain/index.html?ap=1","userContractUrl":"/user/contract/index.html?ap=1","userEvaluateRecordUrl":"/user/evaluate-record/index.html?ap=1","userHouseMoveUrl":"/user/house-move/index.html?ap=1","userHouseMoveDetailUrl":"/user/house-move/detail.html?ap=1","userShopUrl":"/user/shop/index.html?ap=1","repairAboutUrl":"/user/repair/about.html?ap=1","clearAboutUrl":"/user/cleanup/about.html?ap=1","paymentRecordUrl":"/payment/record.html?ap=1","moveAboutUrl":"/user/house-move/about.html?ap=1","signDetailUrl":"/activity/sign-detail.html?ap=1","payDetailUrl":"/payment/detail.html?ap=1","servePayDetailUrl":"/pay/index.html?ap=1"}
         */

        public WebUrlDataBean webUrlData;

        public static class WebUrlDataBean {
            /**
             * webUrlDomain : http://meiqi.app.6lapp.cn:8081
             * paymentUrl : /payment/index.html?ap=1
             * housesDetailsUrl : /houses/detail.html?ap=1
             * activityDetailsUrl : /activity/detail.html?ap=1
             * activitySignUrl : /activity/sign.html?ap=1
             * goodsDetailUrl : /flea/goods-detail.html?ap=1
             * goodsReportUrl : /flea/report.html?ap=1
             * storyDetailUrl : /story/detail.html?ap=1
             * topicDetailUrl : /topic/detail.html?ap=1
             * userCleanUpUrl : /user/cleanup/index.html?ap=1
             * userCleanUpDetailUrl : /user/cleanup/detail.html?ap=1
             * userRepairUrl : /user/repair/index.html?ap=1
             * userRepairDetailUrl : /user/repair/detail.html?ap=1
             * userCommendUrl : /user/commend/index.html?ap=1
             * userComplainUrl : /user/complain/index.html?ap=1
             * userContractUrl : /user/contract/index.html?ap=1
             * userEvaluateRecordUrl : /user/evaluate-record/index.html?ap=1
             * userHouseMoveUrl : /user/house-move/index.html?ap=1
             * userHouseMoveDetailUrl : /user/house-move/detail.html?ap=1
             * userShopUrl : /user/shop/index.html?ap=1
             * repairAboutUrl : /user/repair/about.html?ap=1
             * clearAboutUrl : /user/cleanup/about.html?ap=1
             * paymentRecordUrl : /payment/record.html?ap=1
             * moveAboutUrl : /user/house-move/about.html?ap=1
             * signDetailUrl : /activity/sign-detail.html?ap=1
             * payDetailUrl : /payment/detail.html?ap=1
             * servePayDetailUrl : /pay/index.html?ap=1
             */

            public String webUrlDomain;
            public String paymentUrl;
            public String housesDetailsUrl;
            public String activityDetailsUrl;
            public String activitySignUrl;
            public String goodsDetailUrl;
            public String goodsReportUrl;
            public String storyDetailUrl;
            public String topicDetailUrl;
            public String userCleanUpUrl;
            public String userCleanUpDetailUrl;
            public String userRepairUrl;
            public String userRepairDetailUrl;
            public String userCommendUrl;
            public String userComplainUrl;
            public String userContractUrl;
            public String userEvaluateRecordUrl;
            public String userHouseMoveUrl;
            public String userHouseMoveDetailUrl;
            public String userShopUrl;
            public String repairAboutUrl;
            public String clearAboutUrl;
            public String paymentRecordUrl;
            public String moveAboutUrl;
            public String signDetailUrl;
            public String payDetailUrl;
            public String servePayDetailUrl;
        }
    }
}
