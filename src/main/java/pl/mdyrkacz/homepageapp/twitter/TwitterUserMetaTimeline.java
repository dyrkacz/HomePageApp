package pl.mdyrkacz.homepageapp.twitter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TwitterUserMetaTimeline {

    @JsonProperty("result_count")
    private int resultCount;
}
