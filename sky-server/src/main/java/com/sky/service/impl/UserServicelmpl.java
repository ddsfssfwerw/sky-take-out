package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sky.constant.MessageConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.exception.LoginFailedException;
import com.sky.mapper.UserMapper;
import com.sky.properties.WeChatProperties;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LLY
 * @className UserServicelmpl
 * @date 2024/4/16
 */
@Service
@Slf4j
public class UserServicelmpl implements UserService {

    public static String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WeChatProperties properties;

    /**
     * 用户登录，根据微信授权码
     * @param userLoginDTO
     * @return
     */
    @Override
    public User login(UserLoginDTO userLoginDTO) {
        String code = userLoginDTO.getCode();
        String openid = getOpenId(code);

        if (openid == null) {
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }
        User user = userMapper.getByOpenid(openid);
        if (user == null) {
            user = new User();
            user.setOpenid(openid);
            user.setCreateTime(LocalDateTime.now());
            userMapper.insert(user);
        }
        return user;
    }

    /**
     * 获取用户openid
     * @param code
     * @return
     */
    private String getOpenId(String code) {
        Map map = new HashMap();
        map.put("appid", properties.getAppid());
        map.put("secret", properties.getSecret());
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");

        String json = HttpClientUtil.doGet(WX_LOGIN,map);
        log.info("微信登陆返回结果为:{}", json);

        JSONObject jsonObject = JSON.parseObject(json);
        String openId = jsonObject.getString("openid");
        log.info("微信用户openId为:{}", openId);
        return openId;
    }
}
