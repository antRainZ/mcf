package org.antrain.article.service;

import org.antrain.article.domain.Article;
import org.antrain.article.vo.ArticleVo;
import org.antrain.article.bo.ArticleQueryBo;
import org.antrain.article.bo.ArticleAddBo;
import org.antrain.article.bo.ArticleEditBo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * 文章Service接口
 *
 * @author antrain
 * @date 2021-04-11
 */
public interface IArticleService extends IService<Article> {
	/**
	 * 查询单个
	 * @return
	 */
	ArticleVo queryById(Long id);

	/**
	 * 查询列表
	 */
	List<ArticleVo> queryList(ArticleQueryBo bo);

	/**
	 * 根据新增业务对象插入文章
	 * @param bo 文章新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(ArticleAddBo bo);

	/**
	 * 根据编辑业务对象修改文章
	 * @param bo 文章编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(ArticleEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
