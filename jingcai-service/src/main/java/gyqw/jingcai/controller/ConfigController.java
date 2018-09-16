package gyqw.jingcai.controller;

import gyqw.jingcai.domain.Config;
import gyqw.jingcai.model.BaseModel;
import gyqw.jingcai.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/config")
@RestController
public class ConfigController {
    private Logger logger = LoggerFactory.getLogger(ConfigController.class);

    private ConfigService configService;

    @Autowired
    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

    @RequestMapping(value = "/getConfigByCategories", method = RequestMethod.GET)
    public BaseModel getConfigByCategory(@RequestParam("categories") String categories) {
        BaseModel baseModel = new BaseModel();
        baseModel.setResult(this.configService.getConfigByCategory(categories));
        return baseModel;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseModel update(@RequestBody List<Config> configList) {
        BaseModel baseModel = new BaseModel();
        baseModel.setResult(this.configService.updateConfig(configList));
        return baseModel;
    }
}
