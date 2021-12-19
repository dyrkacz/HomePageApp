package pl.mdyrkacz.homepageapp.setting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mdyrkacz.homepageapp.user.User;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {

    Setting findByUserAndSettingKey(User user, String key);

}
