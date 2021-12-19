package pl.mdyrkacz.homepageapp.news;

import java.util.List;

public interface NewsRssService {

    List<NewsRss> findAll();

    NewsRss findByNewsCategoryName(String newsCategoryName);

    NewsRss findById(Long id);

}
