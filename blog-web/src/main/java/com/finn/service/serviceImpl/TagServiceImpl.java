package com.finn.service.serviceImpl;

import com.finn.entity.Article;
import com.finn.entity.ArticleTag;
import com.finn.entity.Category;
import com.finn.entity.Tag;
import com.finn.enums.ResultEnums;
import com.finn.mapper.TagMapper;
import com.finn.service.CategoryService;
import com.finn.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.finn.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author finn
 * @since 2022-02-03
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
