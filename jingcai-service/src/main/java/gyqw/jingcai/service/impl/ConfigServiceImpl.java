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
import java.util.List;

@Service
public class ConfigServiceImpl implements ConfigService {
    private Logger logger = LoggerFactory.getLogger(ConfigServiceImpl.class);

    private ConfigsMapper configsMapper;

    @Autowired
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
}
