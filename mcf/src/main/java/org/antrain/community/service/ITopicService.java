package org.antrain.community.service;

import org.antrain.community.domain.Topic;
import org.antrain.community.vo.TopicVo;
import org.antrain.community.bo.TopicQueryBo;
import org.antrain.community.bo.TopicAddBo;
import org.antrain.community.bo.TopicEditBo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * 主题Service接口
 *
 * @author antrain
 * @date 2021-04-11
 */
public interface ITopicService extends IService<Topic> {
	/**
	 * 查询单个
	 * @return
	 */
	TopicVo queryById(Long id);

	/**
	 * 查询列表
	 */
	List<TopicVo> queryList(TopicQueryBo bo);

	/**
	 * 根据新增业务对象插入主题
	 * @param bo 主题新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TopicAddBo bo);

	/**
	 * 根据编辑业务对象修改主题
	 * @param bo 主题编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TopicEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
