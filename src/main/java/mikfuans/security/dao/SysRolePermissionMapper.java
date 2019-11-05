package mikfuans.security.dao;


import mikfuans.security.bean.SysRolePermission;
import mikfuans.security.bean.SysRolePermissionCriteria;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface SysRolePermissionMapper {
    @SelectProvider(type=SysRolePermissionSqlProvider.class, method="countByExample")
    long countByExample(SysRolePermissionCriteria example);

    @DeleteProvider(type=SysRolePermissionSqlProvider.class, method="deleteByExample")
    int deleteByExample(SysRolePermissionCriteria example);

    @Delete({
        "delete from sys_role_permission",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sys_role_permission (role_id, permission_id)",
        "values (#{roleId,jdbcType=BIGINT}, #{permissionId,jdbcType=BIGINT})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(SysRolePermission record);

    @InsertProvider(type=SysRolePermissionSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(SysRolePermission record);

    @SelectProvider(type=SysRolePermissionSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
        @Result(column="role_id", property="roleId", jdbcType= JdbcType.BIGINT),
        @Result(column="permission_id", property="permissionId", jdbcType= JdbcType.BIGINT)
    })
    List<SysRolePermission> selectByExample(SysRolePermissionCriteria example);

    @Select({
        "select",
        "id, role_id, permission_id",
        "from sys_role_permission",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
        @Result(column="role_id", property="roleId", jdbcType= JdbcType.BIGINT),
        @Result(column="permission_id", property="permissionId", jdbcType= JdbcType.BIGINT)
    })
    SysRolePermission selectByPrimaryKey(Long id);

    @UpdateProvider(type=SysRolePermissionSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SysRolePermission record, @Param("example") SysRolePermissionCriteria example);

    @UpdateProvider(type=SysRolePermissionSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SysRolePermission record, @Param("example") SysRolePermissionCriteria example);

    @UpdateProvider(type=SysRolePermissionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysRolePermission record);

    @Update({
        "update sys_role_permission",
        "set role_id = #{roleId,jdbcType=BIGINT},",
          "permission_id = #{permissionId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysRolePermission record);
}