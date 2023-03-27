package org.antrain.app.vo;

import lombok.Data;

@Data
public class MessagePageVo {
    private Integer page=1;
    private Integer limit=5;
    private Long id;
    public void check(){
        if (page==null || page < 1) {
            page = 1;
        }
        if (limit==null || limit<1) {
            limit = 5;
        }
    }
}
