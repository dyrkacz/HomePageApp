package pl.mdyrkacz.homepageapp.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.hibernate.Hibernate;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mdyrkacz.homepageapp.api.JsonReader;
import pl.mdyrkacz.homepageapp.news.News;
import pl.mdyrkacz.homepageapp.news.NewsRss;
import pl.mdyrkacz.homepageapp.news.NewsRssService;
import pl.mdyrkacz.homepageapp.role.Role;
import pl.mdyrkacz.homepageapp.role.RoleRepository;
import pl.mdyrkacz.homepageapp.setting.SettingService;
import pl.mdyrkacz.homepageapp.twitter.*;
import pl.mdyrkacz.homepageapp.weather.Weather;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JsonReader jsonReader;
    private final NewsRssService newsRssService;
    private final SettingService settingService;
    private final String weatherApiKey = "c2dfb4cf5b5ef7e77a9259875f0fc720";


    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByUserNameEnabled(String name) {
        return userRepository.findByUserNameEnabled(name);
    }


    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public void editUser(User user, boolean changePassword) {
        if (changePassword) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } else {
            userRepository.save(user);
        }
    }

    @Override
    public List<NewsRss> newsRssListByUser(User user) {
        User userWithList = findById(user.getId());
        Hibernate.initialize(userWithList.getNewsRssList());
        return userWithList.getNewsRssList();
    }

//    public void enable(User user) {
//        user.setEnabled(1);
//        userRepository.save(user);
//    }
//
//    public void disable(User user) {
//        user.setEnabled(0);
//        userRepository.save(user);
//    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<News> newsXml(String url) {
        List<News> newsList = new ArrayList<>();
        URL feedSource = null;
        try {
            feedSource = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        SyndFeedInput input = new SyndFeedInput();
        try {
            List<SyndEntryImpl> feedList = input.build(new XmlReader(feedSource)).getEntries();
            feedList.forEach(it -> {
                News news = new News();
                news.setTitle(it.getTitle());
                news.setUri(it.getUri());
                String temp = String.valueOf(it.getDescription().getValue());
                temp = temp.replaceFirst(">", ">&&&");
                String[] tempTab = temp.split("&&&");
                news.setImg(tempTab[0]);
                news.setDescription(tempTab[1]);
                newsList.add(news);
            });
        } catch (FeedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    @Override
    public List<News> getActiveNews(String newsCategory, User user) {
        User userWithList = findById(user.getId());
        Hibernate.initialize(userWithList.getNewsRssList());
        for (int i = 0; i < userWithList.getNewsRssList().size(); i++) {
            NewsRss newsRss = newsRssService.findById(getRssId(userWithList.getNewsRssList(), userWithList)[i]);
            if (newsCategory.equals(newsRss.getNewsCategoryName())) {
                return newsXml(newsRss.getUrl());
            }
        }
        return null;
    }

    @Override
    public String getActiveNewsCategoryName(String newsCategory, User user) {
        User userWithList = findById(user.getId());
        Hibernate.initialize(userWithList.getNewsRssList());
        for (int i = 0; i < userWithList.getNewsRssList().size(); i++) {
            NewsRss newsRss = newsRssService.findById(getRssId(userWithList.getNewsRssList(), userWithList)[i]);
            if (newsCategory.equals(newsRss.getNewsCategoryName())) {
                return newsRss.getNewsCategoryName();
            }
        }
        return null;
    }

    @Override
    public Long[] getRssId(List<NewsRss> newsRssList, User user) {

        User userWithList = findById(user.getId());
        Hibernate.initialize(userWithList.getNewsRssList());
        Long[] tempTab = new Long[userWithList.getNewsRssList().size()];
        for (int i = 0; i < userWithList.getNewsRssList().size(); i++) {
            tempTab[i] = userWithList.getNewsRssList().get(i).getId();
        }
        return tempTab;
    }

    @Override
    public Weather getWeather(User user) {
        if (settingService.findCurrentLocationByUser(user) != null) {
            String currentLocation = settingService.findCurrentLocationByUser(user).getSettingValue();
            Weather weather = new Weather();
            JSONObject json = null;
            try {
                json = jsonReader.readJsonFromUrl("https://api.openweathermap.org/data/2.5/weather?id=" +
                        currentLocation + "&appid=" + weatherApiKey);
            } catch (IOException e) {
                e.printStackTrace();
            }
            double temp = json.getJSONObject("main").getDouble("temp") - 273.15;
            temp = Math.round(temp);
            weather.setTemp(temp);
            weather.setCityName(json.getString("name"));
            String weatherDescription = json.getJSONArray("weather").getJSONObject(0).getString("description");
            String tempWeatherDescription = weatherDescription.substring(0, 1).toUpperCase() + weatherDescription.substring(1);
            weather.setWeatherDescription(tempWeatherDescription);
            return weather;
        }
        return null;
    }

    @Override
    public User getUserWithLists(Long id) {
        User userWithLists = findById(id);
        Hibernate.initialize(userWithLists.getNewsRssList());
        Hibernate.initialize(userWithLists.getRoles());
        return userWithLists;
    }

    @Override
    @Async
    public void getTwitterPage(String twitterId, String twitterToken, User user) {
        user.tweetPageList.clear();
        String userid = twitterId;
        String bearerToken = decodeToken(twitterToken);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();
        try {
            TwitterUserTimeline twitterUserTimeline = getTwitterUserTimeline(userid, bearerToken, httpclient);
            for (TwitterUserDataTimeline twitterUserDataTimeline : twitterUserTimeline.getDataList()) {
                TwitterUser twitterUser = getTwitterUser(bearerToken, httpclient, twitterUserDataTimeline);
                String usernameTemp = twitterUser.getTwitterUserData().getUsername();
                String profileImgUrlTemp = twitterUser.getTwitterUserData().getProfileImageUrl();
                Tweet tweet = getTweet(bearerToken, httpclient, twitterUserDataTimeline);
                if (tweet.getTweetDataList() != null) {
                    tweet.getTweetDataList().forEach(it -> {
                        TweetPage tweetPage = new TweetPage();
                        tweetPage.setId(it.getId());
                        tweetPage.setUsername(usernameTemp);
                        tweetPage.setProfileImgUrl(profileImgUrlTemp);
                        tweetPage.setCreated(it.getCreatedAt());
                        tweetPage.setTweetText(it.getText());
                        user.tweetPageList.add(tweetPage);
                    });
                }
            }
            user.tweetPageList.sort(Comparator.comparing(t -> t.getCreated()));
            Collections.reverse(user.tweetPageList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private TwitterUserTimeline getTwitterUserTimeline(String userid, String bearerToken, CloseableHttpClient httpclient) throws IOException {
        HttpResponse responseUsersTimeline = null;
        String urlFollowingUsers = "https://api.twitter.com/2/users/" + userid + "/following";
        HttpGet request = new HttpGet(urlFollowingUsers);
        request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken);
        responseUsersTimeline = httpclient.execute(request);
        HttpEntity entityUsers = responseUsersTimeline.getEntity();
        String resultUsers = EntityUtils.toString(entityUsers);
        ObjectMapper objectMapperUsers = new ObjectMapper();
        return objectMapperUsers.readValue(resultUsers, TwitterUserTimeline.class);
    }

    private Tweet getTweet(String bearerToken, CloseableHttpClient httpclient, TwitterUserDataTimeline twitterUserDataTimeline) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String formatDateTime = LocalDateTime.now().minusDays(3).format(formatter);
        String tweetsFrom3days = "https://api.twitter.com/2/users/" + twitterUserDataTimeline.getId() + "/tweets?exclude=replies,retweets&start_time=" + formatDateTime + "Z&tweet.fields=created_at,text";
        HttpResponse responseTweets = null;
        HttpGet requestTweets = new HttpGet(tweetsFrom3days);
        requestTweets.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken);
        responseTweets = httpclient.execute(requestTweets);
        HttpEntity entityTweets = responseTweets.getEntity();
        String resultTweets = EntityUtils.toString(entityTweets);
        ObjectMapper objectMapperTweets = new ObjectMapper();
        return objectMapperTweets.readValue(resultTweets, Tweet.class);
    }

    private TwitterUser getTwitterUser(String bearerToken, CloseableHttpClient httpclient, TwitterUserDataTimeline twitterUserDataTimeline) throws IOException {
        HttpResponse responseUser = null;
        String userById = "https://api.twitter.com/2/users/" + twitterUserDataTimeline.getId() + "?user.fields=username,profile_image_url";
        HttpGet requestUser = new HttpGet(userById);
        requestUser.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken);
        responseUser = httpclient.execute(requestUser);
        HttpEntity entityUser = responseUser.getEntity();
        String resultUser = EntityUtils.toString(entityUser);
        ObjectMapper objectMapperUser = new ObjectMapper();
        return objectMapperUser.readValue(resultUser, TwitterUser.class);
    }

    private String decodeToken(String token) {
        return new String(Base64.getDecoder().decode(token.getBytes()));
    }

    @Override
    public boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}