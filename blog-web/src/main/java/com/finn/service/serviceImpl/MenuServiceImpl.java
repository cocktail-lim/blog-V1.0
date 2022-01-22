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
        List<MenuDTO> returnMenuList;
        // 获取所有的Menu
        List<Menu> menuList = this.baseMapper.getMenuList(roleName);
        // 存放子菜单
        HashMap<Integer, List<Menu>> childrenMap = new HashMap<>();
        // 获取子菜单
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






        menuList.stream()
                .filter((Menu::getParentId)!=0)
                .




        // 先把父菜单放入
        returnMenuList = this.convertMenuListToMenuDTOList(
                menuList.stream()
                .filter(
                        item -> item.getParentId() == 0 || Objects.isNull(item.getParentId())
                )
                .collect(Collectors.toList())
        )
        ;





        // 获取子菜单
        List<MenuDTO> childrenMenuDTOList = this.convertMenuListToMenuDTOList(this.listChildrenMenuSorted(menuList));
        List

        HashMap<Integer, List<MenuDTO>> childrenMenuDTOMap = new HashMap<>();
        for(MenuDTO dto :childrenMenuDTOList) {
            childrenMenuDTOMap.put(dto.getMenuId(), )

        }
//
//        List<MenuDTO> childrenMenuDTOList;

        // 把子菜单过滤出好来后赋值给每个父栏目的children
        menuList.stream()
                .filter(item -> {
                    return item.getParentId() == 0; // 拿到父节点
                })
                .map(parent -> {
                })
                .collect(Collectors.toList());

        return returnMenuList;
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
        MenuDTO menuDTO = new MenuDTO();
        List<MenuDTO> listMenuDTO = new ArrayList<>();
        listMenu.stream()
                .filter(Objects::isNull)
                .map(item -> {
                    menuDTO.setMenuId(item.getMenuId())
                    .setDescription(item.getDescription())
                    .setMenuIcon(item.getMenuIcon())
                    .setMenuName(item.getMenuName())
                    .setMenuSort(item.getMenuSort())
                    .setMenuUrl(item.getMenuUrl())
                    .setParentId(item.getParentId())
                    ;
                    listMenuDTO.add(menuDTO);
                    return item;
                });
        listMenuDTO.sort(Comparator.comparing(MenuDTO::getMenuSort));
        return listMenuDTO;
    }


}
