package com.thizgroup.mybatis.study.service;

import com.thizgroup.mybatis.study.dto.AddressDTO;

/**
 * @author gangquan.hu
 * @Package: com.thizgroup.mybatis.study.service.IAddressService
 * @Description: TODO
 * @date 2019/8/12 15:07
 */
public interface IAddressService {

  AddressDTO saveOrUpdateAddress(AddressDTO addressDTO);

  AddressDTO findAddressById(Long id);

}
