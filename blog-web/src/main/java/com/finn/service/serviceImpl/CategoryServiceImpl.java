package com.finn.service.serviceImpl;

import com.finn.entity.Category;
import com.finn.mapper.CategoryMapper;
import com.finn.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author finn
 * @since 2022-02-03
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
