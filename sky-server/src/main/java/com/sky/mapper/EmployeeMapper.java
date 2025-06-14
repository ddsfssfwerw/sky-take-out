package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.aspect.AutoFillAspect;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.annotation.*;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 新增员工
     * @param employee
     * @return
     */
    @Insert("insert into employee (name,username,password,phone,sex,id_number,status,create_time,update_time,create_user,update_user) " +
            "values " +
            "(#{name},#{username},#{password},#{phone},#{sex},#{idNumber},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @autoFill(value = OperationType.INSERT)
    void insert(Employee employee);

    /**
     * 分页查询员工
     *
     *
     * @param employeePageQueryDTO
     * @return
     */
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 通用方法，根据主键修改属性
     * @param employee
     */
    @autoFill(value = OperationType.UPDATE)
    void update(Employee employee);

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    @Select("select * from employee where id=#{id}")
    Employee getById(long id);
}
