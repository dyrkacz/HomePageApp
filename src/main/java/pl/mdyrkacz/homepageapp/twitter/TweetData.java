package pl.mdyrkacz.homepageapp.twitter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.redouane59.twitter.dto.tweet.entities.Entities;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(value = {"entities", "author_id"}, ignoreUnknown = true)
public class TweetData {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("text")
    private String text;
    @JsonProperty("author_id")
    private String authorId;
    @JsonProperty("entities")
    private TweetEntities tweetEntities;
    @JsonProperty("created_at")
    private String createdAt;

}
