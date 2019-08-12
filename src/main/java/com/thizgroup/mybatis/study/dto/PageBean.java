package com.thizgroup.mybatis.study.dto;

import java.util.List;
import lombok.Data;

@Data
public class PageBean<E>{

  /**
   * 分页：当前页
   */
  private Integer pageNumber;

  /**
   * 每页记录数
   */
  private Integer pageSize;
  Integer totalPages;
  Long totalCount;
  List<E> data = null;

  public PageBean(List<E> data) {
    this.data = data;
  }

  public PageBean() {
  }

}
