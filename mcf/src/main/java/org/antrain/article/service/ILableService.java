package org.antrain.article.service;

import com.alibaba.fastjson.JSONObject;
import org.antrain.article.domain.Lable;
import org.antrain.article.vo.LableVo;
import org.antrain.article.bo.LableQueryBo;
import org.antrain.article.bo.LableAddBo;
import org.antrain.article.bo.LableEditBo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * 标签Service接口
 *
 * @author antrain
 * @date 2021-04-11
 */
public interface ILableService extends IService<Lable> {
	JSONObject getAll();
	/**
	 * 查询单个
	 * @return
	 */
	LableVo queryById(Long id);

	/**
	 * 查询列表
	 */
	List<LableVo> queryList(LableQueryBo bo);

	/**
	 * 根据新增业务对象插入标签
	 * @param bo 标签新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(LableAddBo bo);

	/**
	 * 根据编辑业务对象修改标签
	 * @param bo 标签编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(LableEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
