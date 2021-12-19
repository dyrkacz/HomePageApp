package pl.mdyrkacz.homepageapp.twitter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TweetImages {

    @JsonProperty("url")
    private String url;
    @JsonProperty("width")
    private int width;
    @JsonProperty("height")
    private int height;
}
