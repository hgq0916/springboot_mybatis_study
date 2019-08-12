package com.thizgroup.mybatis.study.service;

import com.thizgroup.mybatis.study.MybatisStudyApplication;
import com.thizgroup.mybatis.study.dto.AddressDTO;
import com.thizgroup.mybatis.study.dto.PageBean;
import com.thizgroup.mybatis.study.dto.PageRequest;
import com.thizgroup.mybatis.study.dto.Sorter;
import com.thizgroup.mybatis.study.dto.Sorter.Order;
import com.thizgroup.mybatis.study.dto.UserDTO;
import com.thizgroup.mybatis.study.utils.DateUtils;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
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
    userDTO.setName("张");
    PageBean<UserDTO> userListByPage = userService.findUserListByPage(userDTO,
        PageRequest.of(1, 5,Sorter.of("create_date", Order.ASC)));
    System.out.println("总记录数："+userListByPage.getTotalCount());
    System.out.println("总页数:"+userListByPage.getTotalPages());
    System.out.println("页码："+userListByPage.getPageNumber());
    System.out.println("每页条数："+userListByPage.getPageSize());
    System.out.println("数据列表:");
    userListByPage.getData().forEach(u-> System.out.println(u));
  }

  @Test
  public void findUserByIdTest(){
    UserDTO userDTO = userService.findUserById(2L);
    System.out.println(userDTO);
    Assert.assertEquals(userDTO.getId().longValue(),2L);
  }

  @Test
  public void deleteUserByIdTest(){
    userService.deleteUserById(4L);
  }

  @Test
  //@Rollback(value = false)
  public void saveOrUpdateUserTest(){
    UserDTO userDTO = UserDTO.builder()
        .name("赵六")
        .age(32)
        .birthday(DateUtils.parse("2013-08-08 12:12:12", "YYYY-MM-dd HH:mm:ss"))
        .email("zhaoliu@126.com")
        .mobile("18589786789")
        .addressDTO(null)
        .build();
    userService.saveOrUpdateUser(userDTO);
  }

  @Test
  //@Rollback(value = false)
  public void saveOrUpdateUserTest2(){
    AddressDTO adddressDTO = new AddressDTO();
    adddressDTO.setCountry("中国");
    adddressDTO.setProvince("河南");
    adddressDTO.setCity("郑州");
    UserDTO userDTO = UserDTO.builder()
        .id(4L)
        .name("王五")
        .age(30)
        .birthday(DateUtils.parse("2012-08-08 12:12:12", "YYYY-MM-dd HH:mm:ss"))
        .email("wangwu@126.com")
        .mobile("18789786789")
        .addressDTO(adddressDTO)
        .build();
    userService.saveOrUpdateUser(userDTO);
  }

  @Test
  //@Rollback(value = false)
  public void updateUserEmailTest(){
    userService.updateUserEmail(4L,"wangwu@qq.com");
  }

  @Test
  //@Rollback(value = false)
  public void updateMobileByUserId(){
    System.out.println("affect counts:"+userService.updateMobileByUserId(4L,"15134567890"));
  }

  @Test
  public void findUserDTOByUserId(){
    UserDTO userDTO = userService.findUserDTOByUserId(4L);
    System.out.println(userDTO);
  }

}
