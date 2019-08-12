package com.thizgroup.mybatis.study.service;

import com.thizgroup.mybatis.study.dto.UserDTO;
import org.junit.Test;

/**
 * @author gangquan.hu
 * @Package: com.thizgroup.mybatis.study.service.TestClass
 * @Description: TODO
 * @date 2019/8/12 19:18
 */
public class TestClass {

  @Test
  public void fun(){
    UserDTO userDTO = new UserDTO(){{
      setName("zhangsan");
      setAge(15);
    }};
    System.out.println(userDTO);
  }

  /**
   * 反编译结果如下：
   *
   package com.thizgroup.mybatis.study.service;

   import com.thizgroup.mybatis.study.dto.UserDTO;

   class TestClass$1 extends UserDTO {
   final  TestClass this$0;

   TestClass$1(TestClass this$02) {
   this.this$0 = this$02;
   setName("zhangsan");
   setAge(Integer.valueOf(15));
   }
   }
   */



}
