package cn.lxl.common.api.user;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 权限
 * @author xinleili
 */
@FeignClient(contextId = "authorityApi", name = "authorityApi",
        path = "/authorityApi")
public interface AuthorityApi {
}
