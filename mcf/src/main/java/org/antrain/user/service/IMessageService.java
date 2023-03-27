package org.antrain.user.service;

import org.antrain.user.domain.Message;
import org.antrain.user.vo.MessageVo;
import org.antrain.user.bo.MessageQueryBo;
import org.antrain.user.bo.MessageAddBo;
import org.antrain.user.bo.MessageEditBo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * 消息Service接口
 *
 * @author antrain
 * @date 2021-04-11
 */
public interface IMessageService extends IService<Message> {
	/**
	 * 查询单个
	 * @return
	 */
	MessageVo queryById(Long id);

	/**
	 * 查询列表
	 */
	List<MessageVo> queryList(MessageQueryBo bo);

	/**
	 * 根据新增业务对象插入消息
	 * @param bo 消息新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(MessageAddBo bo);

	/**
	 * 根据编辑业务对象修改消息
	 * @param bo 消息编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(MessageEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
