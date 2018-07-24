package com.exmple.baseprojectmvp.http;

import android.support.annotation.Keep;

import com.exmple.corelib.http.entity.BaseBean;

import java.util.List;

/**
 * @version V1.0 <描述当前版本功能>
 * @FileName: MainDataBean.java
 * @author: villa_mou
 * @date: 05-16:05
 * @desc
 */
@Keep
public class MainDataBean extends BaseBean {

    /**
     * data : {"fileUrlDomain":"http://meiqi.file.6lapp.cn:8081/","fileUserUrlDomain":"http://meiqi.user.file.6lapp.cn:8081/","webUrlData":{"webUrlDomain":"http://meiqi.app.6lapp.cn:8081","paymentUrl":"/payment/index.html?ap=1","housesDetailsUrl":"/houses/detail.html?ap=1","activityDetailsUrl":"/activity/detail.html?ap=1","activitySignUrl":"/activity/sign.html?ap=1","goodsDetailUrl":"/flea/goods-detail.html?ap=1","goodsReportUrl":"/flea/report.html?ap=1","storyDetailUrl":"/story/detail.html?ap=1","topicDetailUrl":"/topic/detail.html?ap=1","userCleanUpUrl":"/user/cleanup/index.html?ap=1","userCleanUpDetailUrl":"/user/cleanup/detail.html?ap=1","userRepairUrl":"/user/repair/index.html?ap=1","userRepairDetailUrl":"/user/repair/detail.html?ap=1","userCommendUrl":"/user/commend/index.html?ap=1","userComplainUrl":"/user/complain/index.html?ap=1","userContractUrl":"/user/contract/index.html?ap=1","userEvaluateRecordUrl":"/user/evaluate-record/index.html?ap=1","userHouseMoveUrl":"/user/house-move/index.html?ap=1","userShopUrl":"/user/shop/index.html?ap=1"},"bannerList":[{"banner_id":"10","type":"1","picture":"img/20180605/5b15fe975c0f3.jpg","content":"www.woshipm.com"}],"topicsTopList":[{"topics_top_id":"7","topics_id":"7","picture":"img/20180605/5b1602e7569b3.jpg","type":"1","link":""},{"topics_top_id":"6","topics_id":"6","picture":"img/20180605/5b1602df066a8.jpg","type":"1","link":""},{"topics_top_id":"5","topics_id":"4","picture":"img/20180605/5b1602d0294b9.jpg","type":"1","link":"www.dunlop.com.cn"}],"houseTopList":[{"house_popular_top_id":"7","house_popular_id":"5","picture":"img/20180605/5b160303a0c84.jpg","alias":"深圳后庭精选房源","min_price":"300000","max_price":"500000"},{"house_popular_top_id":"6","house_popular_id":"5","picture":"img/20180605/5b1602fd3e478.jpg","alias":"深圳后庭精选房源","min_price":"300000","max_price":"500000"},{"house_popular_top_id":"5","house_popular_id":"5","picture":"img/20180605/5b1602f6c0d0a.jpg","alias":"深圳后庭精选房源","min_price":"300000","max_price":"500000"}],"storyList":[{"story_id":"16","title":"罗斯柴尔德","content":"往事96m8m56t3i6","annex":["img/20180606/5b174689537fb.png"],"picture":"img/20180606/5b174689537fb.png","scan":"0","time":"1528252041","nickname":"","avatar":""},{"story_id":"15","title":"我的故事。。。。。。。","content":"后悔落空户口来lol停机理亏扣卡OK了里GPSppsill噢噢噢哦哦","annex":["img/20180605/5b166e34f32a8.png"],"picture":"img/20180605/5b166e34f32a8.png","scan":"0","time":"1528196661","nickname":"","avatar":""},{"story_id":"10","title":"在一起吧、","content":"哦对","annex":["img/20180605/5b164c2f90f68.png","img/20180605/5b164c2f91444.png","img/20180605/5b164c2f918b4.png","img/20180605/5b164c2f91d18.png","img/20180605/5b164c2f92195.png","img/20180605/5b164c2f95cea.png","img/20180605/5b164c2f9618f.png"],"picture":"img/20180605/5b164c2f90f68.png","scan":"0","time":"1528187951","nickname":"","avatar":""}]}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * fileUrlDomain : http://meiqi.file.6lapp.cn:8081/
         * fileUserUrlDomain : http://meiqi.user.file.6lapp.cn:8081/
         * webUrlData : {"webUrlDomain":"http://meiqi.app.6lapp.cn:8081","paymentUrl":"/payment/index.html?ap=1","housesDetailsUrl":"/houses/detail.html?ap=1","activityDetailsUrl":"/activity/detail.html?ap=1","activitySignUrl":"/activity/sign.html?ap=1","goodsDetailUrl":"/flea/goods-detail.html?ap=1","goodsReportUrl":"/flea/report.html?ap=1","storyDetailUrl":"/story/detail.html?ap=1","topicDetailUrl":"/topic/detail.html?ap=1","userCleanUpUrl":"/user/cleanup/index.html?ap=1","userCleanUpDetailUrl":"/user/cleanup/detail.html?ap=1","userRepairUrl":"/user/repair/index.html?ap=1","userRepairDetailUrl":"/user/repair/detail.html?ap=1","userCommendUrl":"/user/commend/index.html?ap=1","userComplainUrl":"/user/complain/index.html?ap=1","userContractUrl":"/user/contract/index.html?ap=1","userEvaluateRecordUrl":"/user/evaluate-record/index.html?ap=1","userHouseMoveUrl":"/user/house-move/index.html?ap=1","userShopUrl":"/user/shop/index.html?ap=1"}
         * bannerList : [{"banner_id":"10","type":"1","picture":"img/20180605/5b15fe975c0f3.jpg","content":"www.woshipm.com"}]
         * topicsTopList : [{"topics_top_id":"7","topics_id":"7","picture":"img/20180605/5b1602e7569b3.jpg","type":"1","link":""},{"topics_top_id":"6","topics_id":"6","picture":"img/20180605/5b1602df066a8.jpg","type":"1","link":""},{"topics_top_id":"5","topics_id":"4","picture":"img/20180605/5b1602d0294b9.jpg","type":"1","link":"www.dunlop.com.cn"}]
         * houseTopList : [{"house_popular_top_id":"7","house_popular_id":"5","picture":"img/20180605/5b160303a0c84.jpg","alias":"深圳后庭精选房源","min_price":"300000","max_price":"500000"},{"house_popular_top_id":"6","house_popular_id":"5","picture":"img/20180605/5b1602fd3e478.jpg","alias":"深圳后庭精选房源","min_price":"300000","max_price":"500000"},{"house_popular_top_id":"5","house_popular_id":"5","picture":"img/20180605/5b1602f6c0d0a.jpg","alias":"深圳后庭精选房源","min_price":"300000","max_price":"500000"}]
         * storyList : [{"story_id":"16","title":"罗斯柴尔德","content":"往事96m8m56t3i6","annex":["img/20180606/5b174689537fb.png"],"picture":"img/20180606/5b174689537fb.png","scan":"0","time":"1528252041","nickname":"","avatar":""},{"story_id":"15","title":"我的故事。。。。。。。","content":"后悔落空户口来lol停机理亏扣卡OK了里GPSppsill噢噢噢哦哦","annex":["img/20180605/5b166e34f32a8.png"],"picture":"img/20180605/5b166e34f32a8.png","scan":"0","time":"1528196661","nickname":"","avatar":""},{"story_id":"10","title":"在一起吧、","content":"哦对","annex":["img/20180605/5b164c2f90f68.png","img/20180605/5b164c2f91444.png","img/20180605/5b164c2f918b4.png","img/20180605/5b164c2f91d18.png","img/20180605/5b164c2f92195.png","img/20180605/5b164c2f95cea.png","img/20180605/5b164c2f9618f.png"],"picture":"img/20180605/5b164c2f90f68.png","scan":"0","time":"1528187951","nickname":"","avatar":""}]
         */

        public String fileUrlDomain;
        public String fileUserUrlDomain;
        public WebUrlDataBean webUrlData;
        public List<BannerListBean> bannerList;
        public List<TopicsTopListBean> topicsTopList;
        public List<HouseTopListBean> houseTopList;
        public List<StoryListBean> storyList;
        public List<CityListBean> cityList;

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
             * userShopUrl : /user/shop/index.html?ap=1
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
            public String userShopUrl;
        }

        public static class BannerListBean {
            /**
             * banner_id : 10
             * type : 1
             * picture : img/20180605/5b15fe975c0f3.jpg
             * content : www.woshipm.com
             */

            public String banner_id;
            public String type;
            public String picture;
            public String content;
        }

        public static class TopicsTopListBean {
            /**
             * topics_top_id : 7
             * topics_id : 7
             * picture : img/20180605/5b1602e7569b3.jpg
             * type : 1
             * link :
             */

            public String topics_top_id;
            public String topics_id;
            public String picture;
            public String type;
            public String link;
        }

        public static class HouseTopListBean {
            /**
             * house_popular_top_id : 7
             * house_popular_id : 5
             * picture : img/20180605/5b160303a0c84.jpg
             * alias : 深圳后庭精选房源
             * min_price : 300000
             * max_price : 500000
             */

            public String house_popular_top_id;
            public String house_popular_id;
            public String picture;
            public String alias;
            public String min_price;
            public String max_price;
        }

        public static class StoryListBean {
            /**
             * story_id : 16
             * title : 罗斯柴尔德
             * content : 往事96m8m56t3i6
             * annex : ["img/20180606/5b174689537fb.png"]
             * picture : img/20180606/5b174689537fb.png
             * scan : 0
             * time : 1528252041
             * nickname :
             * avatar :
             */

            public String story_id;
            public String title;
            public String content;
            public String picture;
            public String scan;
            public String time;
            public String nickname;
            public String avatar;
            public List<String> annex;
        }
        public static class CityListBean {
            /**
             * area_id : 110000
             * fullname : 北京市
             * level : 2
             * lat : 39.90469
             * lng : 116.40717
             */

            public String area_id;
            public String fullname;
            public String level;
            public String lat;
            public String lng;
        }

    }
}
