package pl.mdyrkacz.homepageapp.twitter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class TwitterUser {

    @JsonProperty("data")
    TwitterUserData twitterUserData;
}
