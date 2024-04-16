package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.vo.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author LLY
 * @InterfaceName UserService
 * @date 2024/4/16
 */
public interface UserService {

    /**
     * 登录
     * @param userLoginDTO
     * @return
     */
    User login(UserLoginDTO userLoginDTO);
}
