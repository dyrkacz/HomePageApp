package pl.mdyrkacz.homepageapp.twitter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TweetMeta {

    @JsonProperty("oldest_id")
    private String oldestId;
    @JsonProperty("newest_id")
    private String newestId;
    @JsonProperty("result_count")
    private int resultCount;
    @JsonProperty("next_token")
    private String nextToken;

}
