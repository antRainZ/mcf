package org.antrain.article.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.antrain.community.domain.Invitation;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import org.antrain.article.bo.ArticleAddBo;
import org.antrain.article.bo.ArticleQueryBo;
import org.antrain.article.bo.ArticleEditBo;
import org.antrain.article.domain.Article;
import org.antrain.article.mapper.ArticleMapper;
import org.antrain.article.vo.ArticleVo;
import org.antrain.article.service.IArticleService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章Service业务层处理
 *
 * @author antrain
 * @date 2021-04-11
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Override
    public ArticleVo queryById(Long id){
        Article db = this.baseMapper.selectById(id);
        return BeanUtil.toBean(db, ArticleVo.class);
    }

    @Override
    public List<ArticleVo> queryList(ArticleQueryBo bo) {
        QueryWrapper<Article> lqw = new QueryWrapper<>();
        lqw.select("id","title","nickname","refine","status","labels","create_time");
        if (StrUtil.isNotBlank(bo.getTitle())) {
            lqw.like("title", bo.getTitle());
        }
        if (StrUtil.isNotBlank(bo.getNickname())) {
            lqw.like("nickname", bo.getNickname());
        }
        return entity2Vo(this.list(lqw));
    }

    /**
    * 实体类转化成视图对象
    *
    * @param collection 实体类集合
    * @return
    */
    private List<ArticleVo> entity2Vo(Collection<Article> collection) {
        List<ArticleVo> voList = collection.stream()
                .map(any -> BeanUtil.toBean(any, ArticleVo.class))
                .collect(Collectors.toList());
        if (collection instanceof Page) {
            Page<Article> page = (Page<Article>)collection;
            Page<ArticleVo> pageVo = new Page<>();
            BeanUtil.copyProperties(page,pageVo);
            pageVo.addAll(voList);
            voList = pageVo;
        }
        return voList;
    }

    @Override
    public Boolean insertByAddBo(ArticleAddBo bo) {
        Article add = BeanUtil.toBean(bo, Article.class);
        validEntityBeforeSave(add);
        return this.save(add);
    }

    @Override
    public Boolean updateByEditBo(ArticleEditBo bo) {
        Article update = BeanUtil.toBean(bo, Article.class);
        validEntityBeforeSave(update);
        return this.updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(Article entity){
        //TODO 做一些数据校验,如唯一约束
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return this.removeByIds(ids);
    }
}
