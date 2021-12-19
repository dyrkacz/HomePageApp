package pl.mdyrkacz.homepageapp.news;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsRssServiceImpl implements NewsRssService {
    private final NewsRssRepository newsRssRepository;

    @Override
    public List<NewsRss> findAll() {
        return newsRssRepository.findAll();
    }

    @Override
    public NewsRss findByNewsCategoryName(String newsCategoryName) {
        return newsRssRepository.findByNewsCategoryName(newsCategoryName);
    }

    @Override
    public NewsRss findById(Long id) {
        return newsRssRepository.findById(id).orElse(null);
    }
}
