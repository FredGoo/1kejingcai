package gyqw.jingcai.controller;

import gyqw.jingcai.model.AdminModel;
import gyqw.jingcai.model.BaseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fred
 * 2018/9/10 下午10:18
 */
@RequestMapping("/admin")
@RestController
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseModel login(@RequestBody AdminModel adminModel) {
        BaseModel baseModel = new BaseModel();

        if ("admin".equals(adminModel.getUsername()) &&
                "yike123456".equals(adminModel.getPassword())) {
            baseModel.setResult(1);
        } else {
            baseModel.setResult(0);
        }

        return baseModel;
    }
}
