package org.antrain.user.mapper;

import org.antrain.user.domain.Integral;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户积分记录Mapper接口
 *
 * @author antrain
 * @date 2021-04-11
 */
public interface IntegralMapper extends BaseMapper<Integral> {

    @Select("SELECT id FROM `integral` WHERE  `user_id` = #{userId} AND remark = 'signIn' " +
            " AND DATE_FORMAT(NOW(),'%Y%m%d%') = DATE_FORMAT(`create_time`,'%Y%m%d%');")
    Long checkSignIn(@Param("userId") Long userId);
}
