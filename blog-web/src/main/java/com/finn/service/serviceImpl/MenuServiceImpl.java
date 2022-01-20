package com.finn.service.serviceImpl;

import com.finn.entity.Menu;
import com.finn.mapper.MenuMapper;
import com.finn.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
