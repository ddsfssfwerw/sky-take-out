package com.sky.aspect;


import com.sky.annotation.autoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Entity;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 切面：填充公共字段
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    /**
     * 切入点
     */
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.autoFill)")
    public void autoFillPointCut() {
        //log.info("AutoFillPointCut");
    }

    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("自动填充公共字段...");


        //获取拦截方法的签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        autoFill autoFill = methodSignature.getMethod().getAnnotation(autoFill.class);
        OperationType operationType = autoFill.value();

        //获取拦截方法的实体参数
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return;
        }
        Object object = args[0];

        //准备赋值数据
        LocalDateTime localDateTime = LocalDateTime.now();
        long currentId = BaseContext.getCurrentId();

        //根据不同类型赋值
        if (operationType == OperationType.INSERT) {
            try {
                Method setcreatetime = object.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME,LocalDateTime.class);
                Method setcreateuser = object.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER,Long.class);
                Method setupdatetime = object.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setupdateuser = object.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                setcreatetime.invoke(object,localDateTime);
                setcreateuser.invoke(object,currentId);
                setupdatetime.invoke(object,localDateTime);
                setupdateuser.invoke(object,currentId);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }else if (operationType == OperationType.UPDATE) {
            try {
                Method setupdatetime = object.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setupdateuser = object.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                setupdatetime.invoke(object,localDateTime);
                setupdateuser.invoke(object,currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
