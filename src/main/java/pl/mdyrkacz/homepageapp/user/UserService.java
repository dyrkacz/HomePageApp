package pl.mdyrkacz.homepageapp.user;

import org.apache.http.impl.client.CloseableHttpClient;
import pl.mdyrkacz.homepageapp.news.News;
import pl.mdyrkacz.homepageapp.news.NewsRss;
import pl.mdyrkacz.homepageapp.twitter.TwitterUserTimeline;
import pl.mdyrkacz.homepageapp.weather.Weather;

import java.util.List;

public interface UserService {

    User findByUserName(String name);

    User findByUserNameEnabled(String name);

    void saveUser(User user);

    void editUser(User user, boolean changePassword);

    User findById(Long id);

    List<NewsRss> newsRssListByUser(User user);

    Long[] getRssId(List<NewsRss> newsRssList, User user);

    List<News> getActiveNews(String newsCategory, User user);

    String getActiveNewsCategoryName(String newsCategory, User user);

    Weather getWeather(User user);

    List<News> newsXml(String url);

    User getUserWithLists(Long id);

    void getTwitterPage(String twitterId, String twitterToken, User user);

    boolean existByUsername(String username);

}