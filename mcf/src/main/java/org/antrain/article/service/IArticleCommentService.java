package org.antrain.article.service;

import org.antrain.article.domain.ArticleComment;
import org.antrain.article.vo.ArticleCommentVo;
import org.antrain.article.bo.ArticleCommentQueryBo;
import org.antrain.article.bo.ArticleCommentAddBo;
import org.antrain.article.bo.ArticleCommentEditBo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * 文章评论Service接口
 *
 * @author antrain
 * @date 2021-04-11
 */
public interface IArticleCommentService extends IService<ArticleComment> {
	/**
	 * 查询单个
	 * @return
	 */
	ArticleCommentVo queryById(Long id);

	/**
	 * 查询列表
	 */
	List<ArticleCommentVo> queryList(ArticleCommentQueryBo bo);

	/**
	 * 根据新增业务对象插入文章评论
	 * @param bo 文章评论新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(ArticleCommentAddBo bo);

	/**
	 * 根据编辑业务对象修改文章评论
	 * @param bo 文章评论编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(ArticleCommentEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
