package com.mashaoting.bibibili.pursueplay.bean;

import java.util.List;

/**
 * Created by 麻少亭 on 2017/3/23.
 */

public class ZhuiFanBean {
    /**
     * code : 0
     * message : success
     * result : {"ad":{"body":[],"head":[{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/a093348aea6f7def3ef45a68f6272270e5f0afb1.jpg","link":"http://www.bilibili.com/blackboard/activity-S1aPZanjx.html","pub_time":"2017-03-24 18:58:00","title":"4月新番"},{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/2cc14d831d0a4d7972870e72c1b944fd7a296831.jpg","link":"http://bangumi.bilibili.com/anime/5774","pub_time":"2017-03-24 12:00:00","title":"清恋 SEIREN"},{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/83822a05fa70ae0a0fb82a5fb99dd9cff9d09408.jpg","link":"http://bangumi.bilibili.com/anime/5800","pub_time":"2017-03-22 23:30:00","title":"小林家的龙女仆"},{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/a368a33425cc0efb883366f9dc69a31a435a2246.jpg","link":"http://bangumi.bilibili.com/anime/2069","pub_time":"2017-03-24 00:00:00","title":"十二国记"}]},"previous":{"list":[{"cover":"http://i0.hdslb.com/bfs/bangumi/2673ac643b48eb5bda64c960a2ca850fbebb839d.jpg","favourites":"1676554","is_finish":1,"last_time":1482262210,"newest_ep_index":"11","pub_time":1475607600,"season_id":5550,"season_status":2,"title":"夏目友人帐 伍","watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/b75c55d209d156c8631f5ceb21e5c52c834dbb60.jpg","favourites":"1334612","is_finish":0,"last_time":1483196409,"newest_ep_index":"1","pub_time":1483196400,"season_id":5747,"season_status":2,"title":"Fate/Grand Order \u2010First Order\u2010","watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/b3633d2e5cafa0d048f4beef63618c92cfac4c4c.jpg","favourites":"787005","is_finish":1,"last_time":1482465609,"newest_ep_index":"12","pub_time":1475812800,"season_id":5534,"season_status":2,"title":"我太受欢迎了该怎么办","watching_count":0}],"season":4,"year":2016},"serializing":[{"cover":"http://i0.hdslb.com/bfs/bangumi/8c7e18df9ca21fc8da6b5860b8f7d2ad1bbf4462.jpg","favourites":"2919","is_finish":0,"is_started":1,"last_time":1490347985,"newest_ep_index":"4","pub_time":1488470400,"season_id":5936,"season_status":2,"title":"茅屋爷爷讲故事","watching_count":8},{"cover":"http://i0.hdslb.com/bfs/bangumi/9db6b0f0ab276715149ebbb53acc0ce1058f0e08.jpg","favourites":"13706","is_finish":0,"is_started":1,"last_time":1490340073,"newest_ep_index":"0","pub_time":1490320800,"season_id":5975,"season_status":2,"title":"降灵记","watching_count":626},{"cover":"http://i0.hdslb.com/bfs/bangumi/465effa90d0dc3d916fe9d51a73ec066c831bce9.jpg","favourites":"200707","is_finish":0,"is_started":1,"last_time":1490336384,"newest_ep_index":"0","pub_time":1490284800,"season_id":5856,"season_status":2,"title":"银之守墓人","watching_count":699},{"cover":"http://i0.hdslb.com/bfs/bangumi/055fcccf87dd9b2ff6d86adc43711fce5df78e44.jpg","favourites":"792312","is_finish":1,"is_started":1,"last_time":1490328362,"newest_ep_index":"12","pub_time":1483675200,"season_id":5776,"season_status":2,"title":"URARA迷路帖","watching_count":3688},{"cover":"http://i0.hdslb.com/bfs/bangumi/91e659b3a3cfe0e62e5e26a11a9f617533e8f161.jpg","favourites":"797751","is_finish":1,"is_started":1,"last_time":1490328302,"newest_ep_index":"12","pub_time":1483675200,"season_id":5774,"season_status":2,"title":"清恋 SEIREN","watching_count":4373},{"cover":"http://i0.hdslb.com/bfs/bangumi/ec03710bac931d7c5184ce7cb8ab9bdbc3e435db.jpg","favourites":"25050","is_finish":0,"is_started":1,"last_time":1490328000,"newest_ep_index":"19","pub_time":1478793600,"season_id":5894,"season_status":2,"title":"Disintegration-分解世界-","watching_count":45}]}
     */

    private int code;
    private String message;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * ad : {"body":[],"head":[{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/a093348aea6f7def3ef45a68f6272270e5f0afb1.jpg","link":"http://www.bilibili.com/blackboard/activity-S1aPZanjx.html","pub_time":"2017-03-24 18:58:00","title":"4月新番"},{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/2cc14d831d0a4d7972870e72c1b944fd7a296831.jpg","link":"http://bangumi.bilibili.com/anime/5774","pub_time":"2017-03-24 12:00:00","title":"清恋 SEIREN"},{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/83822a05fa70ae0a0fb82a5fb99dd9cff9d09408.jpg","link":"http://bangumi.bilibili.com/anime/5800","pub_time":"2017-03-22 23:30:00","title":"小林家的龙女仆"},{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/a368a33425cc0efb883366f9dc69a31a435a2246.jpg","link":"http://bangumi.bilibili.com/anime/2069","pub_time":"2017-03-24 00:00:00","title":"十二国记"}]}
         * previous : {"list":[{"cover":"http://i0.hdslb.com/bfs/bangumi/2673ac643b48eb5bda64c960a2ca850fbebb839d.jpg","favourites":"1676554","is_finish":1,"last_time":1482262210,"newest_ep_index":"11","pub_time":1475607600,"season_id":5550,"season_status":2,"title":"夏目友人帐 伍","watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/b75c55d209d156c8631f5ceb21e5c52c834dbb60.jpg","favourites":"1334612","is_finish":0,"last_time":1483196409,"newest_ep_index":"1","pub_time":1483196400,"season_id":5747,"season_status":2,"title":"Fate/Grand Order \u2010First Order\u2010","watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/b3633d2e5cafa0d048f4beef63618c92cfac4c4c.jpg","favourites":"787005","is_finish":1,"last_time":1482465609,"newest_ep_index":"12","pub_time":1475812800,"season_id":5534,"season_status":2,"title":"我太受欢迎了该怎么办","watching_count":0}],"season":4,"year":2016}
         * serializing : [{"cover":"http://i0.hdslb.com/bfs/bangumi/8c7e18df9ca21fc8da6b5860b8f7d2ad1bbf4462.jpg","favourites":"2919","is_finish":0,"is_started":1,"last_time":1490347985,"newest_ep_index":"4","pub_time":1488470400,"season_id":5936,"season_status":2,"title":"茅屋爷爷讲故事","watching_count":8},{"cover":"http://i0.hdslb.com/bfs/bangumi/9db6b0f0ab276715149ebbb53acc0ce1058f0e08.jpg","favourites":"13706","is_finish":0,"is_started":1,"last_time":1490340073,"newest_ep_index":"0","pub_time":1490320800,"season_id":5975,"season_status":2,"title":"降灵记","watching_count":626},{"cover":"http://i0.hdslb.com/bfs/bangumi/465effa90d0dc3d916fe9d51a73ec066c831bce9.jpg","favourites":"200707","is_finish":0,"is_started":1,"last_time":1490336384,"newest_ep_index":"0","pub_time":1490284800,"season_id":5856,"season_status":2,"title":"银之守墓人","watching_count":699},{"cover":"http://i0.hdslb.com/bfs/bangumi/055fcccf87dd9b2ff6d86adc43711fce5df78e44.jpg","favourites":"792312","is_finish":1,"is_started":1,"last_time":1490328362,"newest_ep_index":"12","pub_time":1483675200,"season_id":5776,"season_status":2,"title":"URARA迷路帖","watching_count":3688},{"cover":"http://i0.hdslb.com/bfs/bangumi/91e659b3a3cfe0e62e5e26a11a9f617533e8f161.jpg","favourites":"797751","is_finish":1,"is_started":1,"last_time":1490328302,"newest_ep_index":"12","pub_time":1483675200,"season_id":5774,"season_status":2,"title":"清恋 SEIREN","watching_count":4373},{"cover":"http://i0.hdslb.com/bfs/bangumi/ec03710bac931d7c5184ce7cb8ab9bdbc3e435db.jpg","favourites":"25050","is_finish":0,"is_started":1,"last_time":1490328000,"newest_ep_index":"19","pub_time":1478793600,"season_id":5894,"season_status":2,"title":"Disintegration-分解世界-","watching_count":45}]
         */

        private AdBean ad;
        private PreviousBean previous;
        private List<SerializingBean> serializing;

        public AdBean getAd() {
            return ad;
        }

        public void setAd(AdBean ad) {
            this.ad = ad;
        }

        public PreviousBean getPrevious() {
            return previous;
        }

        public void setPrevious(PreviousBean previous) {
            this.previous = previous;
        }

        public List<SerializingBean> getSerializing() {
            return serializing;
        }

        public void setSerializing(List<SerializingBean> serializing) {
            this.serializing = serializing;
        }

        public static class AdBean {
            private List<?> body;
            private List<HeadBean> head;

            public List<?> getBody() {
                return body;
            }

            public void setBody(List<?> body) {
                this.body = body;
            }

            public List<HeadBean> getHead() {
                return head;
            }

            public void setHead(List<HeadBean> head) {
                this.head = head;
            }

            public static class HeadBean {
                /**
                 * id : 0
                 * img : http://i0.hdslb.com/bfs/bangumi/a093348aea6f7def3ef45a68f6272270e5f0afb1.jpg
                 * link : http://www.bilibili.com/blackboard/activity-S1aPZanjx.html
                 * pub_time : 2017-03-24 18:58:00
                 * title : 4月新番
                 */

                private int id;
                private String img;
                private String link;
                private String pub_time;
                private String title;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getPub_time() {
                    return pub_time;
                }

                public void setPub_time(String pub_time) {
                    this.pub_time = pub_time;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }
        }

        public static class PreviousBean {
            /**
             * list : [{"cover":"http://i0.hdslb.com/bfs/bangumi/2673ac643b48eb5bda64c960a2ca850fbebb839d.jpg","favourites":"1676554","is_finish":1,"last_time":1482262210,"newest_ep_index":"11","pub_time":1475607600,"season_id":5550,"season_status":2,"title":"夏目友人帐 伍","watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/b75c55d209d156c8631f5ceb21e5c52c834dbb60.jpg","favourites":"1334612","is_finish":0,"last_time":1483196409,"newest_ep_index":"1","pub_time":1483196400,"season_id":5747,"season_status":2,"title":"Fate/Grand Order \u2010First Order\u2010","watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/b3633d2e5cafa0d048f4beef63618c92cfac4c4c.jpg","favourites":"787005","is_finish":1,"last_time":1482465609,"newest_ep_index":"12","pub_time":1475812800,"season_id":5534,"season_status":2,"title":"我太受欢迎了该怎么办","watching_count":0}]
             * season : 4
             * year : 2016
             */

            private int season;
            private int year;
            private List<ListBean> list;

            public int getSeason() {
                return season;
            }

            public void setSeason(int season) {
                this.season = season;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * cover : http://i0.hdslb.com/bfs/bangumi/2673ac643b48eb5bda64c960a2ca850fbebb839d.jpg
                 * favourites : 1676554
                 * is_finish : 1
                 * last_time : 1482262210
                 * newest_ep_index : 11
                 * pub_time : 1475607600
                 * season_id : 5550
                 * season_status : 2
                 * title : 夏目友人帐 伍
                 * watching_count : 0
                 */

                private String cover;
                private String favourites;
                private int is_finish;
                private int last_time;
                private String newest_ep_index;
                private int pub_time;
                private int season_id;
                private int season_status;
                private String title;
                private int watching_count;

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public String getFavourites() {
                    return favourites;
                }

                public void setFavourites(String favourites) {
                    this.favourites = favourites;
                }

                public int getIs_finish() {
                    return is_finish;
                }

                public void setIs_finish(int is_finish) {
                    this.is_finish = is_finish;
                }

                public int getLast_time() {
                    return last_time;
                }

                public void setLast_time(int last_time) {
                    this.last_time = last_time;
                }

                public String getNewest_ep_index() {
                    return newest_ep_index;
                }

                public void setNewest_ep_index(String newest_ep_index) {
                    this.newest_ep_index = newest_ep_index;
                }

                public int getPub_time() {
                    return pub_time;
                }

                public void setPub_time(int pub_time) {
                    this.pub_time = pub_time;
                }

                public int getSeason_id() {
                    return season_id;
                }

                public void setSeason_id(int season_id) {
                    this.season_id = season_id;
                }

                public int getSeason_status() {
                    return season_status;
                }

                public void setSeason_status(int season_status) {
                    this.season_status = season_status;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getWatching_count() {
                    return watching_count;
                }

                public void setWatching_count(int watching_count) {
                    this.watching_count = watching_count;
                }
            }
        }

        public static class SerializingBean {
            /**
             * cover : http://i0.hdslb.com/bfs/bangumi/8c7e18df9ca21fc8da6b5860b8f7d2ad1bbf4462.jpg
             * favourites : 2919
             * is_finish : 0
             * is_started : 1
             * last_time : 1490347985
             * newest_ep_index : 4
             * pub_time : 1488470400
             * season_id : 5936
             * season_status : 2
             * title : 茅屋爷爷讲故事
             * watching_count : 8
             */

            private String cover;
            private String favourites;
            private int is_finish;
            private int is_started;
            private int last_time;
            private String newest_ep_index;
            private int pub_time;
            private int season_id;
            private int season_status;
            private String title;
            private int watching_count;

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getFavourites() {
                return favourites;
            }

            public void setFavourites(String favourites) {
                this.favourites = favourites;
            }

            public int getIs_finish() {
                return is_finish;
            }

            public void setIs_finish(int is_finish) {
                this.is_finish = is_finish;
            }

            public int getIs_started() {
                return is_started;
            }

            public void setIs_started(int is_started) {
                this.is_started = is_started;
            }

            public int getLast_time() {
                return last_time;
            }

            public void setLast_time(int last_time) {
                this.last_time = last_time;
            }

            public String getNewest_ep_index() {
                return newest_ep_index;
            }

            public void setNewest_ep_index(String newest_ep_index) {
                this.newest_ep_index = newest_ep_index;
            }

            public int getPub_time() {
                return pub_time;
            }

            public void setPub_time(int pub_time) {
                this.pub_time = pub_time;
            }

            public int getSeason_id() {
                return season_id;
            }

            public void setSeason_id(int season_id) {
                this.season_id = season_id;
            }

            public int getSeason_status() {
                return season_status;
            }

            public void setSeason_status(int season_status) {
                this.season_status = season_status;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getWatching_count() {
                return watching_count;
            }

            public void setWatching_count(int watching_count) {
                this.watching_count = watching_count;
            }
        }
    }
}