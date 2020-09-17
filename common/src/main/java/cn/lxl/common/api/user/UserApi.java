package cn.lxl.common.api.user;


import cn.lxl.common.api.config.FeignFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户
 * @author xinleili
 */
@FeignClient(contextId = "userApi", name = "serverApi", qualifier = "userApi", path = "/userApi",
fallbackFactory = FeignFallbackImpl.class)
public interface UserApi {


    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    String getById(@RequestParam("id") String id);

    /**
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    String list();


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    String update(@RequestParam("id") String id);

}
