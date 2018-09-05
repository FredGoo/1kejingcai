package gyqw.jingcai.controller;

import gyqw.jingcai.model.BaseModel;
import gyqw.jingcai.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
