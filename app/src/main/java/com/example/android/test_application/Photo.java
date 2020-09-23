package com.example.android.test_application;

import java.util.List;

public class Photo {

    private PhotoUrls urls;

    public PhotoUrls getUrls() {
        return urls;
    }

    static class PhotoUrls{
        private String raw;
        private String full;
        private String regular;
        private String small;
        private String thump;

        public String getRaw() {
            return raw;
        }

        public String getFull() {
            return full;
        }

        public String getRegular() {
            return regular;
        }

        public String getSmall() {
            return small;
        }

        public String getThump() {
            return thump;
        }
    }
}
