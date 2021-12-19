package pl.mdyrkacz.homepageapp.setting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mdyrkacz.homepageapp.user.User;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class SettingServiceImpl implements SettingService {
    private final SettingRepository settingRepository;

    @Override
    public Setting findCurrentLocationByUser(User user) {
        return settingRepository.findByUserAndSettingKey(user, "currentLocation");
    }

    @Override
    public String findTwitterIdByUser(User user) {
        if (settingRepository.findByUserAndSettingKey(user, "twitterId") != null) {
            return settingRepository.findByUserAndSettingKey(user, "twitterId").getSettingValue();
        }
        return null;
    }

    @Override
    public String findTwitterTokenByUser(User user) {
        if (settingRepository.findByUserAndSettingKey(user, "twitterToken") != null) {
            return settingRepository.findByUserAndSettingKey(user, "twitterToken").getSettingValue();
        }
        return null;
    }

    @Override
    public void saveSetting(Setting setting, User user) {
        setting.setUser(user);
        settingRepository.save(setting);
    }

    @Override
    public Setting sendSettingCurrentLocation(User user) {
        if (settingRepository.findByUserAndSettingKey(user, "currentLocation") != null) {
            return (settingRepository.findByUserAndSettingKey(user, "currentLocation"));
        } else {
            return new Setting();
        }
    }

    @Override
    public Setting sendSettingTwitterId(User user) {
        if (settingRepository.findByUserAndSettingKey(user, "twitterId") != null) {
            return (settingRepository.findByUserAndSettingKey(user, "twitterId"));
        } else {
            return new Setting();
        }
    }

    @Override
    public void saveSettingEncoded(Setting setting, User user) {
        setting.setUser(user);
        setting.setSettingValue(Base64.getEncoder().encodeToString(setting.getSettingValue().getBytes()));
        settingRepository.save(setting);
    }
}
