package pl.mdyrkacz.homepageapp.twitter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class TwitterUserDataTimeline {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("username")
    private String username;
}
