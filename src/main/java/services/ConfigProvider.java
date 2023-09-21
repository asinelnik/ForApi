package services;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    Config config = readConfig();
    static Config readConfig(){
        return ConfigFactory.load("application.conf");
    }

    String URL = readConfig().getString("url");
    String USER_LOGIN = readConfig().getString("userParams.user.login");
    String USER_PASSWORD = readConfig().getString("userParams.user.password");
    String ADMIN_LOGIN = readConfig().getString("userParams.admin.login");
    String ADMIN_PASSWORD = readConfig().getString("userParams.admin.password");
}
