package com.thizgroup.mybatis.study.service;

import com.thizgroup.mybatis.study.MybatisStudyApplication;
import com.thizgroup.mybatis.study.dto.PageBean;
import com.thizgroup.mybatis.study.dto.PageRequest;
import com.thizgroup.mybatis.study.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gangquan.hu
 * @Package: com.thizgroup.mybatis.study.service.UserServiceImplTest
 * @Description: TODO
 * @date 2019/8/12 10:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MybatisStudyApplication.class)
@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
public class UserServiceImplTest {

  @Autowired
  private IUserService userService;

  @Test
  public void findAllTest(){
    UserDTO userDTO = new UserDTO();
    userDTO.setName("张");
    List<UserDTO> userDTOList = userService.findAll(userDTO);
    userDTOList = userDTOList == null ? new ArrayList<>() : userDTOList;
    userDTOList.forEach(u -> System.out.println(u));
  }

  @Test
  public void findUserListByPageTest(){
    UserDTO userDTO = new UserDTO();
    //userDTO.setName("张");
    PageBean<UserDTO> userListByPage = userService.findUserListByPage(userDTO, PageRequest.of(1, 5));
    System.out.println("总记录数："+userListByPage.getTotalCount());
    System.out.println("总页数:"+userListByPage.getTotalPages());
    System.out.println("页码："+userListByPage.getPageNumber());
    System.out.println("每页条数："+userListByPage.getPageSize());
    System.out.println("数据列表:");
    userListByPage.getData().forEach(u-> System.out.println(u));
  }

  public static void main(String[] args) {
    System.out.println(args);
  }
}
