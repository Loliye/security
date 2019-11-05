package mikfuans.security.dao;


import mikfuans.security.bean.SysPermission;
import mikfuans.security.bean.SysPermissionCriteria;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface SysPermissionMapper {
    @SelectProvider(type=SysPermissionSqlProvider.class, method="countByExample")
    long countByExample(SysPermissionCriteria example);

    @DeleteProvider(type=SysPermissionSqlProvider.class, method="deleteByExample")
    int deleteByExample(SysPermissionCriteria example);

    @Delete({
        "delete from sys_permission",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sys_permission (name, description, ",
        "url, pid)",
        "values (#{name,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{url,jdbcType=VARCHAR}, #{pid,jdbcType=BIGINT})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(SysPermission record);

    @InsertProvider(type=SysPermissionSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(SysPermission record);

    @SelectProvider(type=SysPermissionSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType= JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType= JdbcType.VARCHAR),
        @Result(column="pid", property="pid", jdbcType= JdbcType.BIGINT)
    })
    List<SysPermission> selectByExample(SysPermissionCriteria example);

    @Select({
        "select",
        "id, name, description, url, pid",
        "from sys_permission",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType= JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType= JdbcType.VARCHAR),
        @Result(column="pid", property="pid", jdbcType= JdbcType.BIGINT)
    })
    SysPermission selectByPrimaryKey(Long id);

    @UpdateProvider(type=SysPermissionSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SysPermission record, @Param("example") SysPermissionCriteria example);

    @UpdateProvider(type=SysPermissionSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SysPermission record, @Param("example") SysPermissionCriteria example);

    @UpdateProvider(type=SysPermissionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysPermission record);

    @Update({
        "update sys_permission",
        "set name = #{name,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR},",
          "pid = #{pid,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysPermission record);
}