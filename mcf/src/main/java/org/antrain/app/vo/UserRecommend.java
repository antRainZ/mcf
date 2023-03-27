package org.antrain.app.vo;

import lombok.Data;
import org.antrain.user.domain.User;
import java.util.Date;

@Data
public class UserRecommend implements Comparable<UserRecommend>{
    private Long id;
    private String nickname;
    private String avatar;
    private Date updateTime;
    private String username;
    private String interest;
    private String score;

    public void setUserRecommend(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.avatar = user.getAvatar();
        this.updateTime = user.getUpdateTime();
        this.username = user.getUsername();
        this.interest = user.getInterest();
    }

    @Override
    public int compareTo(UserRecommend o) {
        return o.getScore().compareTo(this.score);
    }
}
