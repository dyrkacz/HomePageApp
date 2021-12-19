package pl.mdyrkacz.homepageapp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query("select u from User u where u.username = ?1 and u.enabled = 1")
    User findByUserNameEnabled(String name);

    Boolean existsByUsername(String username);
}