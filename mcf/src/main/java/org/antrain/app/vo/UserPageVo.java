package org.antrain.app.vo;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

@Data
public class UserPageVo {
    private Integer page=1;
    private Integer limit=5;
    private String  name="";
    public void check(){
        if (page==null || page < 1) {
            page = 1;
        }
        if (limit==null || limit<1) {
            limit = 5;
        }
        if (StrUtil.isBlank(name)) {
            name = "";
        }
    }
}
