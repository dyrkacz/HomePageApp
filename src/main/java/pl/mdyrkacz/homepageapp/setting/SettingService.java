package pl.mdyrkacz.homepageapp.setting;

import pl.mdyrkacz.homepageapp.user.User;

public interface SettingService {

    Setting findCurrentLocationByUser(User user);

    String findTwitterIdByUser(User user);

    String findTwitterTokenByUser(User user);

    void saveSetting(Setting setting, User user);

    Setting sendSettingCurrentLocation(User user);

    Setting sendSettingTwitterId(User user);

    void saveSettingEncoded(Setting setting, User user);
}
