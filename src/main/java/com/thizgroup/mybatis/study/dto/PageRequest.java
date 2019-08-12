package com.thizgroup.mybatis.study.dto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.Getter;

/**
 * @author gangquan.hu
 * @Package: com.thizgroup.mybatis.study.dto.PageRequest
 * @Description: TODO
 * @date 2019/8/12 13:06
 */
@Getter
public class PageRequest {

  /**
   * 分页：当前页
   */
  private Integer pageNumber;

  /**
   * 每页记录数
   */
  private Integer pageSize;

  private  List<Sorter> sorterList;

  private PageRequest(Integer pageNumber,Integer pageSize){
    this.pageNumber = pageNumber;
    this.pageSize = pageSize;
    this.sorterList = Collections.emptyList();
  }

  private PageRequest(Integer pageNumber,Integer pageSize,Sorter ...sorters){
    this(pageNumber,pageSize);
    this.sorterList = Arrays.asList(sorters);
  }

  public static PageRequest of(Integer pageNumber,Integer pageSize){
    return new PageRequest(pageNumber,pageSize);
  }

  public static PageRequest of(Integer pageNumber,Integer pageSize,Sorter ...sorters){
    return new PageRequest(pageNumber,pageSize,sorters);
  }

}
