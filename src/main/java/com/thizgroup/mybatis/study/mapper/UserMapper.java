package com.thizgroup.mybatis.study.mapper;

import com.thizgroup.mybatis.study.dto.UserDTO;
import com.thizgroup.mybatis.study.entity.User;
import com.thizgroup.mybatis.study.entity.UserExample;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    @Select("select * from tb_user where id = #{id}")
    User selectById(@Param("id") Long id);

    @Update("update tb_user set email = #{email} where id = #{id}")
    void updateUserEmail(@Param("id")Long id ,@Param("email") String email);

    @Delete("delete from tb_user where id = #{id}")
    void deleteById(@Param("id") Long id);

    @SelectKey(resultType = Long.class,keyColumn = "id",before = false,statement = "SELECT LAST_INSERT_ID() AS id",keyProperty = "id")
    //@Options(useGeneratedKeys = true, keyProperty="id", keyColumn="id")
    @Insert("insert into tb_user (name,email,mobile,birthday,age,address_id,create_date,modify_date) values ("
        + " #{name},#{email},#{mobile},#{birthday},#{age},#{addressId},#{createDate},#{modifyDate} )")
    int addUser(User user);

    //注解动态sql开发
    @SelectProvider(type = UserMapperProvider.class,method = "findUserListByCondition")
    List<User> findUserListByCondition(UserDTO userDTO);

    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    int updateByExampleSelective(@Param("record") User record,
        @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    class UserMapperProvider {

        public String findUserListByCondition(UserDTO userDTO){
            StringBuffer sql = new StringBuffer(" select * from tb_user where 1=1 ");
            if(userDTO != null){
                if(StringUtils.isNotBlank(userDTO.getName())){
                    sql.append(" and name like CONCAT('%',CONCAT(#{name},'%')) ");
                }
            }
            return sql.toString();
        }
    }

}