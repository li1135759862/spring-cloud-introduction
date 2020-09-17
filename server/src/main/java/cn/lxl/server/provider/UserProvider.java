package cn.lxl.server.provider;

import cn.lxl.common.api.user.UserApi;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xinleili
 */
@Component
public class UserProvider implements UserApi {


    @Override
    public String getById(String id) {
        return "getById";
    }

    @Override
    public String list() {
        return "list";
    }

    @Override
    public String update(String id) {
        return "update";
    }
}
