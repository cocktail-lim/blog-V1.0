package com.finn.service;

import com.finn.dto.MenuDTO;
import com.finn.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author finn
 * @since 2022-01-20
 */
public interface MenuService extends IService<Menu> {

    /*
    * @Description: 根据用户角色来获取 menu
    * @Param:ss
    * @return:
    * @Author: Finn
    * @Date: 2022/1/21
    */
    List<MenuDTO> getMenuListByRoleName(String roleName);

}
