package mikfuans.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author HanHui
 * @Create 2018-09-27 15:40
 * @Desc
 * @Modify By
 **/
@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping("get")
    public String get() {
        return "get success";
    }

    @RequestMapping("post")
    public String post() {
        return "post success";
    }

    @RequestMapping("del")
    public String del() {
        return "del success";
    }
}
