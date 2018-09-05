package gyqw.jingcai.service;

import gyqw.jingcai.domain.Config;

import java.util.List;

public interface ConfigService {

    List<Config> getConfigByCategory(String category);
}
