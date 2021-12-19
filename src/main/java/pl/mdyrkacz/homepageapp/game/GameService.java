package pl.mdyrkacz.homepageapp.game;

import pl.mdyrkacz.homepageapp.user.User;

import java.util.List;

public interface GameService {

    Game findById(Long id);

    List<Game> findAllByUser(User user);

    void saveGame(Game game, User user);

    void deleteGame(Game game);

}
