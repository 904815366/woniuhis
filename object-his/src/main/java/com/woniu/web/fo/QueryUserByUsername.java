package com.woniu.web.fo;

import com.woniu.entity.dto.UserDto;
import com.woniu.entity.po.UserPo;
import com.woniu.service.UserService;
import com.woniu.util.ApplicationContextHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryUserByUsername {
    private String checkUsername;
    public void getUserByUsername() throws Exception {
        UserService userService = ApplicationContextHolder.getApplicationContext().getBean(UserService.class);
        UserDto userByUsername = userService.getUserByUsername(checkUsername);
        if (userByUsername!=null){
            throw new Exception("用户名已存在");
        }
    }
}
