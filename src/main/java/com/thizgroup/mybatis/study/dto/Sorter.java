package com.thizgroup.mybatis.study.dto;


import lombok.Getter;
import org.springframework.util.Assert;

/**
 * @author gangquan.hu
 * @Package: com.thizgroup.mybatis.study.dto.Sorter
 * @Description: TODO
 * @date 2019/8/12 13:12
 */
@Getter
public class Sorter {

  private final String name;

  private final Order order;

  public String toString(){
    return this.name+" "+order.name();
  }

  private Sorter(String name,Sorter.Order order){
    Assert.notNull(name,"name cannot be null");
    Assert.notNull(order,"order cannot be null");
    this.name = name;
    this.order = order;
  }

  public static Sorter of(String name,Sorter.Order order){
    return new Sorter(name,order);
  }

  public static enum Order {
    DESC("desc"),
    ASC("asc");

    private String name;

    private Order(String name){
      this.name = name;
    }

    public String getName(){
      return name;
    }

  }

}
