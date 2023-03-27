package org.antrain.common.exception.user;

import org.antrain.common.exception.BaseException;

/**
 * 用户信息异常类
 * 
 * @author antrain
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
