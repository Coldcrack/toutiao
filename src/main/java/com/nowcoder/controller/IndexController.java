package com.nowcoder.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nowcoder.model.User;

@Controller
public class IndexController {

	@RequestMapping(path = { "/", "/index" })
	@ResponseBody
	public String index() {
		return "Hello Hdu";
	}
	
	/**
	 * 127.0.0.1:8080/profile/12/22?key=xx&type=33
	 * value值为访问路径，而且带有参数  （?之前的）
	 * PathVariable 为path路径里的变量
	 * RequestParam 不属于path里的参数，request参数
	 * @param groupId
	 * @param userId
	 * @param type
	 * @param key
	 * @return
	 */
	@RequestMapping(value = { "/profile/{groupId}/{userId}" })
	@ResponseBody
	public String profile(@PathVariable("groupId") String groupId, @PathVariable("userId") int userId,
			@RequestParam(value = "type", defaultValue = "1") int type,
			@RequestParam(value = "key", defaultValue = "nowcoder") String key) {
		return String.format("GID{%s},UID{%d},TYPE{%d},KEY{%s}", groupId, userId, type, key);
	}
	
	//返回到定义的模板
	@RequestMapping(value = {"/vm"})
    public String news(Model model) {
        model.addAttribute("value1", "vv1");
        List<String> colors = Arrays.asList(new String[]{"RED", "GREEN", "BLUE"});

        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < 4; ++i) {
            map.put(String.valueOf(i), String.valueOf(i * i));
        }

        model.addAttribute("colors", colors);
        model.addAttribute("map", map);
        model.addAttribute("user", new User("Jim"));

        return "news";
    }
}
