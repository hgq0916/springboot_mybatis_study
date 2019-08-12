package com.thizgroup.mybatis.study.service.impl;

import com.thizgroup.mybatis.study.dto.AddressDTO;
import com.thizgroup.mybatis.study.entity.Address;
import com.thizgroup.mybatis.study.entity.AddressExample;
import com.thizgroup.mybatis.study.mapper.AddressMapper;
import com.thizgroup.mybatis.study.service.IAddressService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author gangquan.hu
 * @Package: com.thizgroup.mybatis.study.service.impl.AddressServiceImpl
 * @Description: TODO
 * @date 2019/8/12 15:07
 */
@Service
@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
public class AddressServiceImpl implements IAddressService {

  @Autowired
  private AddressMapper addressMapper;

  @Override
  public AddressDTO saveOrUpdateAddress(AddressDTO addressDTO) {
    //数据校验 todo
    Address address = convertDTOToEntity(addressDTO);
    if(address.getId() == null){
      //保存
      addressMapper.insert(address);
    }else {
      //更新
      Address addressOld = findAddressEntityById(address.getId());
      if(addressOld == null) throw new RuntimeException("address not found");
      addressOld.setCountry(address.getCountry());
      addressOld.setProvince(address.getProvince());
      addressOld.setCity(address.getCity());

      AddressExample example = new AddressExample();
      example.createCriteria().andIdEqualTo(addressOld.getId());
      addressMapper.updateByExample(addressOld,example);
      address = addressOld;
    }
    return convertEntityToDTO(address);
  }

  @Override
  public AddressDTO findAddressById(Long id) {
    return convertEntityToDTO(findAddressEntityById(id));
  }

  private AddressDTO convertEntityToDTO(Address address) {

    return address == null ? null :
    AddressDTO.builder()
        .id(address.getId())
        .country(address.getCountry())
        .province(address.getProvince())
        .city(address.getCity())
        .build();
  }

  private Address convertDTOToEntity(AddressDTO addressDTO) {

    Assert.notNull(addressDTO,"addressDTO cannot be null");

    return addressDTO == null ? null :
        Address.builder()
            .id(addressDTO.getId())
            .country(addressDTO.getCountry())
            .province(addressDTO.getProvince())
            .city(addressDTO.getCity())
            .build();
  }

  private Address findAddressEntityById(Long id) {
    AddressExample example = new AddressExample();
    example.createCriteria().andIdEqualTo(id);
    List<Address> addresses = addressMapper.selectByExample(example);
    if(addresses != null && addresses.size()>0) return addresses.get(0);
    return null;
  }

}
