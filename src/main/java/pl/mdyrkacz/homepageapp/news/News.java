package pl.mdyrkacz.homepageapp.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {
    private String title;
    private String img;
    private String uri;
    private String description;


}
