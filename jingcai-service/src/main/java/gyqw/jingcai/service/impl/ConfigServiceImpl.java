package gyqw.jingcai.service.impl;

import gyqw.jingcai.dao.ConfigsMapper;
import gyqw.jingcai.domain.Config;
import gyqw.jingcai.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ConfigServiceImpl implements ConfigService {
    private Logger logger = LoggerFactory.getLogger(ConfigServiceImpl.class);

    private ConfigsMapper configsMapper;

    @Autowired(required = false)
    public void setConfigsMapper(ConfigsMapper configsMapper) {
        this.configsMapper = configsMapper;
    }

    @Override
    public List<Config> getConfigByCategory(String categories) {
        List<Config> configList = new ArrayList<>();
        try {
            Condition condition = new Condition(Config.class);
            List<String> categoryList = Arrays.asList(categories.split(","));
            condition.createCriteria().andIn("cCategory", categoryList);
            condition.setOrderByClause("N_ORDER desc");
            configList = this.configsMapper.selectByCondition(condition);
            return configList;
        } catch (Exception e) {
            logger.error("getConfigByCategory", e);
            return configList;
        }
    }

    @Override
    public int updateConfig(List<Config> configList) {
        try {
            Date now = new Date();
            Integer res = 0;

            for (Config config : configList) {
                Condition condition = new Condition(Config.class);
                condition.createCriteria()
                        .andEqualTo("cCategory", config.getcCategory())
                        .andEqualTo("cKey", config.getcKey());
                config.setdUpdate(now);
                res += this.configsMapper.updateByConditionSelective(config, condition);
            }
            return res;
        } catch (Exception e) {
            logger.error("updateConfig error", e);
            return 0;
        }
    }
}
