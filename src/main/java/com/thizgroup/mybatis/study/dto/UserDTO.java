package com.thizgroup.mybatis.study.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gangquan.hu
 * @Package: com.thizgroup.mybatis.study.dto.UserDTO
 * @Description: TODO
 * @date 2019/8/12 10:19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

  private Long id;

  private String name;

  private Integer age;

  private Date birthday;

  private String email;

  private String mobile;

  private Date createDate;

  private Date modifyDate;

  private AddressDTO addressDTO;
}
