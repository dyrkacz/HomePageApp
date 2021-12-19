package pl.mdyrkacz.homepageapp.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mdyrkacz.homepageapp.user.User;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findAllByUser(User user);

}
