package com.thizgroup.mybatis.study.mapper;

import com.thizgroup.mybatis.study.entity.Address;
import com.thizgroup.mybatis.study.entity.AddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressMapper {
    long countByExample(AddressExample example);

    int deleteByExample(AddressExample example);

    int insert(Address record);

    int insertSelective(Address record);

    List<Address> selectByExample(AddressExample example);

    int updateByExampleSelective(@Param("record") Address record,
        @Param("example") AddressExample example);

    int updateByExample(@Param("record") Address record, @Param("example") AddressExample example);
}