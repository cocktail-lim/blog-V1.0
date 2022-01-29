package com.finn.service;

import com.finn.dto.RoleSelectListDTO;
import com.finn.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author finn
 * @since 2022-01-18
 */
@Service
public interface RoleService extends IService<Role> {
    /* 
    * @Description: 用户角色下拉列表选项 角色名 个数
    * @Param: []
    * @return:  
    * @Author: Finn
    * @Date: 2022/1/27 
    */
    List<RoleSelectListDTO> getUserRoleSelectList();


}
