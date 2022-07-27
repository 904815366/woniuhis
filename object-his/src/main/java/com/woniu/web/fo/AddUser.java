package com.woniu.web.fo;

import com.woniu.entity.po.UserPo;
import com.woniu.service.UserService;
import com.woniu.util.ApplicationContextHolder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class AddUser {
    private Integer familyid;
    private String username;
    private String password;
    private String name;
    private String sex;
    private Integer age;
    private String card;
    private String phone;
    private Integer roleid;
    private String level;
    private String status;

    public void addUser() throws Exception {
        UserService userService = ApplicationContextHolder
                .getApplicationContext().getBean(UserService.class);
        PasswordEncoder passwordEncoder = ApplicationContextHolder
                .getApplicationContext().getBean(PasswordEncoder.class);
        UserPo userPo = new UserPo();
        userPo.setFamilyid(familyid);
        userPo.setUsername(username);
        //密码加密后存入
        userPo.setPassword(passwordEncoder.encode(password));
        userPo.setName(name);
        userPo.setSex(sex);
        userPo.setAge(age);
        userPo.setCard(card);
        userPo.setPhone(phone);
        userPo.setRoleid(roleid);
        userPo.setLevel(level);
        userPo.setStatus(status);
        Integer integer = userService.addUser(userPo);
        if (integer==0){
            throw new Exception("录入信息失败");
        }
    }
}
