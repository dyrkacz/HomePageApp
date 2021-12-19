package pl.mdyrkacz.homepageapp.twitter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.json.JSONPropertyIgnore;

import java.util.List;
@Data
@JsonIgnoreProperties({ "start", "end", "url", "display_url", "status", "description", "unwound_url" })
public class TweetUrl {

    @JsonProperty("start")
    private int start;
    @JsonProperty("end")
    private int end;
    @JsonProperty("url")
    private String url;
    @JsonProperty("expanded_url")
    private String expandedUrl;
    @JsonProperty("display_url")
    private String displayedUrl;
    @JsonProperty("images")
    private List<TweetImages> tweetImagesList;
    @JsonProperty("status")
    private String status;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("unwound_url")
    private String unwoundUrl;

}

