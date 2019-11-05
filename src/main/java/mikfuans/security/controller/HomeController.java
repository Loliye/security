package mikfuans.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController
{
    @RequestMapping("/")
    public String index(Model model)
    {
        Map<String, Object> msg = new HashMap<>();
        msg.put("title", "标题12312");
        msg.put("content", "内容32323");
        msg.put("etraInfo", "欢迎来到HOME页面,您拥有 ROLE_HOME 权限");
        model.addAttribute("msg", msg);
        return "home";
    }

    @RequestMapping("/login-fail")
    public String loginFail(Model model)
    {
        return "login-fail";
    }

    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }

    @RequestMapping("/admin")
    @ResponseBody
    public String hello()
    {
        return "hello admin";
    }

    @RequestMapping("/test1")
    @ResponseBody
    public String test()
    {
        return "test admin";
    }
}
