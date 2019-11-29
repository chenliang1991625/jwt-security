package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author:wjup
 * @Date: 2018/9/26 0026
 * @Time: 14:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
//@Component
public class  User implements Serializable {
    private Long id;
    private String userName;
    private String passWord;
    private String realName;


}
