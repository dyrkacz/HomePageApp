package pl.mdyrkacz.homepageapp.twitter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties({"meta"})
public class Tweet {

    @JsonProperty("data")
    private List<TweetData> tweetDataList;
    @JsonProperty("meta")
    private TweetMeta tweetMeta;

}

