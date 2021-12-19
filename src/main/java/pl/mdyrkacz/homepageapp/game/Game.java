package pl.mdyrkacz.homepageapp.game;

import lombok.Getter;
import lombok.Setter;
import pl.mdyrkacz.homepageapp.user.User;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20)
    @Size(max = 20, message = "The name of the game must be max 20 characters long")
    private String gameName;
    @Column(length = 6)
    @Size(min = 6, max = 6, message = "The game id consists of 6 characters")
    private String gameId;
    @ManyToOne
    private User user;

}
