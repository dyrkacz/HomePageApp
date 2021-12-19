package pl.mdyrkacz.homepageapp.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mdyrkacz.homepageapp.user.User;

import java.util.List;

@Repository
public interface NewsRssRepository extends JpaRepository<NewsRss, Long> {

    NewsRss findByNewsCategoryName(String newsCategoryName);

}
