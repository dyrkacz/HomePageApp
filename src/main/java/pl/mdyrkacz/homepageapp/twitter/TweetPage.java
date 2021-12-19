package pl.mdyrkacz.homepageapp.twitter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TweetPage {
    private Long id;
    private String username;
    private String profileImgUrl;
    private String tweetText;
    private String created;

}
