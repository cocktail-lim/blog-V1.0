package com.finn.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.finn.dto.MenuDTO;
import com.finn.entity.Menu;
import com.finn.mapper.MenuMapper;
import com.finn.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author finn
 * @since 2022-01-20
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    /*
    * @Description: 获取role能看到的菜单
    * @Param: [roleName]
    * @return:
    * @Author: Finn
    * @Date: 2022/1/21
    */
    @Override
    public List<MenuDTO> getMenuListByRoleName(String roleName) {
        // 要返回给前端的结果
        List<MenuDTO> returnMenuDTOList;
        // 获取所有的Menu
        List<Menu> menuList = this.baseMapper.getMenuList(roleName);
        // 存放子菜单
        HashMap<Integer, List<Menu>> childrenMap = new HashMap<>();
        // 生成子菜单List并存入HashMap
        for(Menu menu : menuList) {
            if(menu.getParentId() != 0){
                int parentId = menu.getParentId();
                childrenMap.put(
                        parentId,
                        menuList.stream()
                                .filter(item -> item.getParentId() == parentId)
                                .collect(Collectors.toList())
                );
            }
        }

        // 把父菜单放入
        returnMenuDTOList = this.convertMenuListToMenuDTOList(
                menuList
                .stream()
                .filter(item -> item.getParentId() == 0 || Objects.isNull(item.getParentId()))
                .collect(Collectors.toList()))
                .stream()
                .map(item -> item.setChildren(this.convertMenuListToMenuDTOList(childrenMap.getOrDefault(item.getMenuId(), Collections.emptyList()))))
                .collect(Collectors.toList());

        return returnMenuDTOList;
    }

    /*
    * @Description: 返回子菜单
    * @Param: [listMenu]
    * @return:
    * @Author: Finn
    * @Date: 2022/1/21
    */
    public List<Menu> listChildrenMenuSorted(List<Menu> listMenu){
        return listMenu.stream()
                .filter(item -> item.getParentId() != 0 || !Objects.isNull(item.getParentId()))
                .sorted(Comparator.comparing(Menu::getMenuSort)) //根据 menu_sort 进行排序
                .collect(Collectors.toList());
    }

    /* 
    * @Description: 把Menu类型的List转换成MenuDTO类型的List 
    * @Param: [listMenu] 
    * @return:  
    * @Author: Finn
    * @Date: 2022/1/22 
    */
    public List<MenuDTO> convertMenuListToMenuDTOList(List<Menu> listMenu){
        if (listMenu == null)
            return null;
        List<MenuDTO> listMenuDTO = new ArrayList<>();
        listMenu.stream()
                .filter(Objects::nonNull)
                .forEach(item -> {
                    MenuDTO menuDTO = new MenuDTO();
                    menuDTO.setMenuId(item.getId())
//                    .setDescription(item.getDescription())
                    .setMenuIcon(item.getMenuIcon())
                    .setMenuName(item.getMenuName())
//                    .setMenuSort(item.getMenuSort())
                    .setMenuUrl(item.getMenuUrl())
//                    .setParentId(item.getParentId())
                    ;
                    listMenuDTO.add(menuDTO);
                });
//        listMenuDTO.sort(Comparator.comparing(MenuDTO::getMenuSort));
        return listMenuDTO;
    }


}
