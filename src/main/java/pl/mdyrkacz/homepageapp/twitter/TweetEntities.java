package pl.mdyrkacz.homepageapp.twitter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
@JsonIgnoreProperties(value = { "annotations", "mentions", "hashtags" },ignoreUnknown = true)
public class TweetEntities {
    @JsonProperty("urls")
    private List<TweetUrl> tweetUrls;
    @JsonProperty("annotations")
    private List<TweetAnnotations> tweetAnnotationsList;
    @JsonProperty("mentions")
    private List<TweetMentions> tweetMentionsList;
    @JsonProperty("hashtags")
    private List<TweetHashtags> tweetHashtagsList;

}

