package mikfuans.security.dao;


import mikfuans.security.bean.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author HanHui
 * @Create 2018-09-27 17:06
 * @Desc
 * @Modify By
 **/
@Mapper
public interface SysPermissionMapperCust {

    @Select("select p.* from sys_user u LEFT JOIN sys_user_role sru on u.id= sru.sys_user_id LEFT JOIN sys_role r on sru.sys_role_id=r.id LEFT JOIN sys_role_permission spr on spr.role_id=r.id  LEFT JOIN sys_permission p on p.id =spr.permission_id where u.id=#{userId}")
    List<SysPermission> findAll(@Param("userId") Long userId);
}
