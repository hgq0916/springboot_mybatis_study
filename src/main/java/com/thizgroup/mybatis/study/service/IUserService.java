package com.thizgroup.mybatis.study.service;

import com.thizgroup.mybatis.study.dto.PageBean;
import com.thizgroup.mybatis.study.dto.PageRequest;
import com.thizgroup.mybatis.study.dto.UserDTO;
import java.util.List;

/**
 * @author gangquan.hu
 * @Package: com.thizgroup.mybatis.study.service.IUserService
 * @Description: 用户服务
 * @date 2019/8/12 9:35
 */
public interface IUserService {

  List<UserDTO> findAll(UserDTO searchDTO);

  PageBean<UserDTO> findUserListByPage(UserDTO searchDTO, PageRequest pageRequest);

}
