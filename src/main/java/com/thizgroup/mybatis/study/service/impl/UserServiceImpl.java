package com.thizgroup.mybatis.study.service.impl;

import com.github.pagehelper.PageHelper;
import com.thizgroup.mybatis.study.dto.AddressDTO;
import com.thizgroup.mybatis.study.dto.PageBean;
import com.thizgroup.mybatis.study.dto.Sorter;
import com.thizgroup.mybatis.study.dto.UserDTO;
import com.thizgroup.mybatis.study.entity.User;
import com.thizgroup.mybatis.study.entity.UserExample;
import com.thizgroup.mybatis.study.entity.UserExample.Criteria;
import com.thizgroup.mybatis.study.mapper.UserMapper;
import com.thizgroup.mybatis.study.service.IAddressService;
import com.thizgroup.mybatis.study.service.IUserService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
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
  @Autowired
  private IAddressService addressService;

  @Override
  public List<UserDTO> findAll(UserDTO searchDTO, Sorter... sorters) {
    //方式一：
    /*UserExample example = new UserExample();
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
      List<User> userList = userMapper.selectByExample(example);
    }*/
    //方式二：
    List<User> userList = userMapper.findUserListByCondition(searchDTO);
    userList = userList == null ? new ArrayList<>() : userList;
    List<UserDTO> userDTOS = userList.stream().map( user -> convertEntityToDto(user)
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

  @Override
  public UserDTO findUserById(Long id) {
    return convertEntityToDto(findUserEntityById(id));
  }

  private User findUserEntityById(Long id) {
    //方式一：
    /*UserExample example = new UserExample();
    Criteria criteria = example.createCriteria();
    criteria.andIdEqualTo(id);
    List<User> users = userMapper.selectByExample(example);
    if(users != null && users.size()>0) {
      User user = users.get(0);
      return user;
    }*/
    //方式二 ：
    return userMapper.selectById(id);
  }

  @Override
  public void deleteUserById(Long id) {
    UserDTO userDTO = findUserById(id);
    if(userDTO == null) throw new RuntimeException("用户不存在");
    //方式一:
    /*UserExample example = new UserExample();
    example.createCriteria().andIdEqualTo(id);
    userMapper.deleteByExample(example);*/
    //方式二:
    userMapper.deleteById(id);
  }

  @Override
  public UserDTO saveOrUpdateUser(UserDTO userDTO) {
    //数据校验 todo
    AddressDTO addressDTO = null;
    if(userDTO.getAddressDTO()!=null){
      addressDTO = addressService.saveOrUpdateAddress(userDTO.getAddressDTO());
    }
    userDTO.setAddressDTO(addressDTO);

    //保存用户信息
    User user = convertDtoToEntity(userDTO);

    if(user.getId() == null){
      //保存
      user.setCreateDate(new Date());
      user.setModifyDate(new Date());
      //方式一：
      //userMapper.insert(user);
      //方式二：
      userMapper.addUser(user);
    }else {
      //更新
      User userOld = findUserEntityById(user.getId());
      if(userOld == null) throw new RuntimeException("user not found");
      userOld.setAddressId(user.getAddressId());
      userOld.setAge(user.getAge());
      userOld.setBirthday(user.getBirthday());
      userOld.setEmail(user.getEmail());
      userOld.setMobile(user.getMobile());
      userOld.setName(user.getName());
      //更新时间
      userOld.setModifyDate(new Date());
      UserExample example = new UserExample();
      example.createCriteria().andIdEqualTo(user.getId());
      userMapper.updateByExample(userOld,example);
      user = userOld;
    }

    return convertEntityToDto(user);
  }

  @Override
  public void updateUserEmail(Long id, String email) {
    User user = userMapper.selectById(id);
    if(user == null) throw new RuntimeException("user not found");
    userMapper.updateUserEmail(id,email);
  }

  @Override
  public int updateMobileByUserId(Long id, String mobile) {

    return userMapper.updateMobileByUserId(id,mobile);
  }

  private User convertDtoToEntity(UserDTO userDTO) {
    Assert.notNull(userDTO,"userDTO cannot be null");
    return User.builder()
        .id(userDTO.getId())
        .name(userDTO.getName())
        .age(userDTO.getAge())
        .birthday(userDTO.getBirthday())
        .addressId(userDTO.getAddressDTO() == null ? null : userDTO.getAddressDTO().getId())
        .email(userDTO.getEmail())
        .mobile(userDTO.getMobile())
        .build();
  }

  private UserDTO convertEntityToDto(User user) {
    return user == null ? null : UserDTO.builder()
        .id(user.getId())
        .name(user.getName())
        .mobile(user.getMobile())
        .email(user.getEmail())
        .addressDTO(user.getAddressId()== null?null:
            AddressDTO.builder().id(user.getAddressId()).build())
        .birthday(user.getBirthday())
        .createDate(user.getCreateDate())
        .modifyDate(user.getModifyDate())
        .age(user.getAge())
        .build();
  }

}
