package pl.mdyrkacz.homepageapp.game;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mdyrkacz.homepageapp.user.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    @Override
    public List<Game> findAllByUser(User user) {
        return gameRepository.findAllByUser(user);
    }

    @Override
    public Game findById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    @Override
    public void saveGame(Game game, User user) {
        game.setUser(user);
        gameRepository.save(game);
    }

    @Override
    public void deleteGame(Game game) {
        gameRepository.delete(game);
    }
}
