package com.thizgroup.mybatis.study.service.impl;

import com.github.pagehelper.PageHelper;
import com.thizgroup.mybatis.study.dto.PageBean;
import com.thizgroup.mybatis.study.dto.Sorter;
import com.thizgroup.mybatis.study.dto.UserDTO;
import com.thizgroup.mybatis.study.entity.User;
import com.thizgroup.mybatis.study.entity.UserExample;
import com.thizgroup.mybatis.study.entity.UserExample.Criteria;
import com.thizgroup.mybatis.study.mapper.UserMapper;
import com.thizgroup.mybatis.study.service.IUserService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author gangquan.hu
 * @Package: com.thizgroup.mybatis.study.service.impl.UserServiceImpl
 * @Description: TODO
 * @date 2019/8/12 10:21
 */
@Service
@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
public class UserServiceImpl implements IUserService {

  @Autowired
  private UserMapper userMapper;

 /* @Override
  public List<UserDTO> findAll(UserDTO searchDTO) {
    findAll(searchDTO);
  }*/

  @Override
  public List<UserDTO> findAll(UserDTO searchDTO, Sorter... sorters) {
    UserExample example = new UserExample();
    if(searchDTO != null){
      Criteria criteria = example.createCriteria();
      if(!StringUtils.isEmpty(searchDTO.getName())){
        criteria.andNameLike("%"+searchDTO.getName()+"%");
      }
    }
    //排序
    if(sorters != null && sorters.length>0){
      StringBuffer orderString = new StringBuffer();
      for(Sorter sorter:sorters){
        orderString.append(" ").append(sorter.toString()).append(" ");
      }
      example.setOrderByClause(orderString.toString());
    }

    List<User> userList = userMapper.selectByExample(example);
    userList = userList == null ? new ArrayList<>() : userList;
    List<UserDTO> userDTOS = userList.stream().map(
        user ->
            UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .birthday(user.getBirthday())
                .addressId(user.getAddressId())
                .createDate(user.getCreateDate())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .modifyDate(user.getModifyDate())
                .build()
    ).collect(Collectors.toList());
    return userDTOS;
  }

  @Override
  public PageBean<UserDTO> findUserListByPage(UserDTO searchDTO, com.thizgroup.mybatis.study.dto.PageRequest pageRequest) {
    com.github.pagehelper.Page<User> startPage = PageHelper
        .startPage(pageRequest.getPageNumber(), pageRequest.getPageSize(), true);
    List<UserDTO> userDTOS = findAll(searchDTO,pageRequest.getSorterList().toArray(new Sorter[]{}));

    PageBean<UserDTO> page = new PageBean<>(userDTOS);
    page.setPageNumber(startPage.getPageNum());
    page.setPageSize(startPage.getPageSize());
    page.setTotalCount(startPage.getTotal());
    page.setTotalPages(startPage.getPages());
    return page;
  }
}
