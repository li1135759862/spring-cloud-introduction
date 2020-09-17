package cn.lxl.common.api.user;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 角色
 * @author xinleili
 */
@FeignClient(contextId = "roleApi", name = "roleApi", qualifier = "roleApi", path = "/roleApi")
public interface RoleApi {
}
