package org.antrain.user.mapper;

import com.baomidou.mybatisplus.annotation.SqlParser;
import org.antrain.app.vo.FriendVo;
import org.antrain.user.domain.Apply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 好友申请Mapper接口
 *
 * @author antrain
 * @date 2021-04-11
 */
public interface ApplyMapper extends BaseMapper<Apply> {
    @Update("UPDATE `apply`  SET status='Y' where id = #{id} and accept_id =#{userId} ")
    @SqlParser(filter = true)
    int accept(@Param("id") Long id, @Param("userId") Long userId);
    @Update("UPDATE `apply`  SET status='R' where id = #{id} and accept_id =#{userId} ")
    @SqlParser(filter = true)
    int reject(@Param("id") Long id, @Param("userId") Long userId);
    @Update("UPDATE `apply`  SET status='D' where id = #{id} and accept_id =#{userId} ")
    @SqlParser(filter = true)
    int deleteById(@Param("id") Long id, @Param("userId") Long userId);

//    @Select("SELECT * FROM `apply` a INNER JOIN `user` u ON a.send_id = u.id INNER JOIN `user` u2 ON a.accept_id = u2.id")
//    List<FriendVo> getMyFriend();
}
