package com.thizgroup.mybatis.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gangquan.hu
 * @Package: com.thizgroup.mybatis.study.dto.AddressDTO
 * @Description: TODO
 * @date 2019/8/12 14:45
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

  private Long id;

  private String country;

  private String province;

  private String city;

}
