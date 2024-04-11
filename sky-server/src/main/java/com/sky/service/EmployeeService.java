package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);


    /**
     * 员工登录
     * @param employeeDTO
     * @return
     */

    void save(EmployeeDTO employeeDTO);

    /**
     * 员工登录
     *
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 启用/禁用员工账号
     * @param status
     * @param id
     */
    void startorStop(Integer status, long id);

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    Employee getById(long id);

    /**
     * 修改员工属性
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);
}
