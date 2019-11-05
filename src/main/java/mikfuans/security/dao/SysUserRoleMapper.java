package mikfuans.security.dao;


import mikfuans.security.bean.SysUserRole;
import mikfuans.security.bean.SysUserRoleCriteria;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface SysUserRoleMapper {
    @SelectProvider(type=SysUserRoleSqlProvider.class, method="countByExample")
    long countByExample(SysUserRoleCriteria example);

    @DeleteProvider(type=SysUserRoleSqlProvider.class, method="deleteByExample")
    int deleteByExample(SysUserRoleCriteria example);

    @Delete({
        "delete from sys_user_role",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sys_user_role (sys_user_id, sys_role_id)",
        "values (#{sysUserId,jdbcType=BIGINT}, #{sysRoleId,jdbcType=BIGINT})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(SysUserRole record);

    @InsertProvider(type=SysUserRoleSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(SysUserRole record);

    @SelectProvider(type=SysUserRoleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
        @Result(column="sys_user_id", property="sysUserId", jdbcType= JdbcType.BIGINT),
        @Result(column="sys_role_id", property="sysRoleId", jdbcType= JdbcType.BIGINT)
    })
    List<SysUserRole> selectByExample(SysUserRoleCriteria example);

    @Select({
        "select",
        "id, sys_user_id, sys_role_id",
        "from sys_user_role",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
        @Result(column="sys_user_id", property="sysUserId", jdbcType= JdbcType.BIGINT),
        @Result(column="sys_role_id", property="sysRoleId", jdbcType= JdbcType.BIGINT)
    })
    SysUserRole selectByPrimaryKey(Long id);

    @UpdateProvider(type=SysUserRoleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SysUserRole record, @Param("example") SysUserRoleCriteria example);

    @UpdateProvider(type=SysUserRoleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SysUserRole record, @Param("example") SysUserRoleCriteria example);

    @UpdateProvider(type=SysUserRoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysUserRole record);

    @Update({
        "update sys_user_role",
        "set sys_user_id = #{sysUserId,jdbcType=BIGINT},",
          "sys_role_id = #{sysRoleId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysUserRole record);
}