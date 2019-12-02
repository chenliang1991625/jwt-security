package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
//角色创建
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Role implements GrantedAuthority {

    private Long rid;
    private String name;


    @Override//获取角色名
    public String getAuthority() {
        return name;
    }
}