package pl.mdyrkacz.homepageapp.user;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import pl.mdyrkacz.homepageapp.news.NewsRss;
import pl.mdyrkacz.homepageapp.role.Role;
import pl.mdyrkacz.homepageapp.twitter.TweetPage;
import pl.mdyrkacz.homepageapp.validation.CheckLogin;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CheckLogin(message = "A user with the given name already exists")
    @Column(nullable = false, unique = true, length = 60)
    @Size(min = 3, message = "Username must be at least 3 characters long")
    private String username;
    @Pattern(regexp = "(?!.*\\s)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,}", message = "The password must be 5 characters long,have one uppercase letter, one lowercase letter and one number")
    private String password;
    @NotNull
    @NotBlank(message = "Please enter your email address")
    @Email(message = "Wrong e-mail address")
    private String email;
    private int enabled;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @ManyToMany
    @ToString.Exclude
    private List<NewsRss> newsRssList;
    @Transient
    public List<TweetPage> tweetPageList = new ArrayList<>();
}
