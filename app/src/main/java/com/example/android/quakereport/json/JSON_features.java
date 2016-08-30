package com.example.android.quakereport.json;

import java.util.List;

/**
 * Created by Jacky on 2016/8/24.
 */
public class JSON_Features {

    private List<FeaturesBean> features;
    /**
     * generated : LongInteger
     * url : String
     * title : String
     * api : String
     * count : Integer
     * status : Integer
     */

    private MetadataBean metadata;

    public List<FeaturesBean> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeaturesBean> features) {
        this.features = features;
    }

    public MetadataBean getMetadata() { return metadata; }

    public void setMetadata(MetadataBean metadata) { this.metadata = metadata; }

    public static class FeaturesBean {
        /**
         * mag : 1.3
         * place : 88km WNW of Cantwell, Alaska
         * time : 1472015348000
         * updated : 1472016185778
         * tz : -480
         * url : http://earthquake.usgs.gov/earthquakes/eventpage/ak13876481
         * detail : http://earthquake.usgs.gov/earthquakes/feed/v1.0/detail/ak13876481.geojson
         * felt : null
         * cdi : null
         * mmi : null
         * alert : null
         * status : automatic
         * tsunami : 0
         * sig : 26
         * net : ak
         * code : 13876481
         * ids : ,ak13876481,
         * sources : ,ak,
         * types : ,general-link,geoserve,origin,
         * nst : null
         * dmin : null
         * rms : 0.35
         * gap : null
         * magType : ml
         * type : earthquake
         * title : M 1.3 - 88km WNW of Cantwell, Alaska
         */

        private PropertiesBean properties;

        public PropertiesBean getProperties() {
            return properties;
        }

        public void setProperties(PropertiesBean properties) {
            this.properties = properties;
        }

        public static class PropertiesBean {
            private double mag;
            private String place;
            private long time;
            private String url;

            public double getMag() {
                return mag;
            }

            public void setMag(double mag) {
                this.mag = mag;
            }

            public String getPlace() {
                return place;
            }

            public void setPlace(String place) {
                this.place = place;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }

    public static class MetadataBean {
        private String generated;
        private String url;
        private String title;
        private String count;

        public String getGenerated() {
            return generated;
        }

        public void setGenerated(String generated) {
            this.generated = generated;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }
}
