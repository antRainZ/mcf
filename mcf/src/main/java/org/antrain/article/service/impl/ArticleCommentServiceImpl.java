package org.antrain.article.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import org.antrain.article.bo.ArticleCommentAddBo;
import org.antrain.article.bo.ArticleCommentQueryBo;
import org.antrain.article.bo.ArticleCommentEditBo;
import org.antrain.article.domain.ArticleComment;
import org.antrain.article.mapper.ArticleCommentMapper;
import org.antrain.article.vo.ArticleCommentVo;
import org.antrain.article.service.IArticleCommentService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章评论Service业务层处理
 *
 * @author antrain
 * @date 2021-04-11
 */
@Service
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements IArticleCommentService {

    @Override
    public ArticleCommentVo queryById(Long id){
        ArticleComment db = this.baseMapper.selectById(id);
        return BeanUtil.toBean(db, ArticleCommentVo.class);
    }

    @Override
    public List<ArticleCommentVo> queryList(ArticleCommentQueryBo bo) {
        LambdaQueryWrapper<ArticleComment> lqw = Wrappers.lambdaQuery();
        lqw.like(StrUtil.isNotBlank(bo.getArticleTitle()), ArticleComment::getArticleTitle, bo.getArticleTitle());
        lqw.like(StrUtil.isNotBlank(bo.getNickname()), ArticleComment::getNickname, bo.getNickname());
        return entity2Vo(this.list(lqw));
    }

    /**
    * 实体类转化成视图对象
    *
    * @param collection 实体类集合
    * @return
    */
    private List<ArticleCommentVo> entity2Vo(Collection<ArticleComment> collection) {
        List<ArticleCommentVo> voList = collection.stream()
                .map(any -> BeanUtil.toBean(any, ArticleCommentVo.class))
                .collect(Collectors.toList());
        if (collection instanceof Page) {
            Page<ArticleComment> page = (Page<ArticleComment>)collection;
            Page<ArticleCommentVo> pageVo = new Page<>();
            BeanUtil.copyProperties(page,pageVo);
            pageVo.addAll(voList);
            voList = pageVo;
        }
        return voList;
    }

    @Override
    public Boolean insertByAddBo(ArticleCommentAddBo bo) {
        ArticleComment add = BeanUtil.toBean(bo, ArticleComment.class);
        validEntityBeforeSave(add);
        return this.save(add);
    }

    @Override
    public Boolean updateByEditBo(ArticleCommentEditBo bo) {
        ArticleComment update = BeanUtil.toBean(bo, ArticleComment.class);
        validEntityBeforeSave(update);
        return this.updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(ArticleComment entity){
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
