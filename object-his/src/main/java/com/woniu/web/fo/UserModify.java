package com.woniu.web.fo;

import com.woniu.entity.po.UserPo;
import com.woniu.service.UserService;
import com.woniu.util.ApplicationContextHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModify {
    private Integer id;
    private Integer familyid;
    private String name;
    private String sex;
    private Integer age;
    private String card;
    private String phone;
    private Integer roleid;
    private String level;
    private String status;


    public void userModify() throws Exception {
        ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
        UserService userService = applicationContext.getBean(UserService.class);
        UserPo userPo = new UserPo();
        userPo.setId(id);
        userPo.setFamilyid(familyid);
        userPo.setName(name);
        userPo.setSex(sex);
        userPo.setAge(age);
        userPo.setCard(card);
        userPo.setPhone(phone);
        userPo.setRoleid(roleid);
        userPo.setLevel(level);
        userPo.setStatus(status);
        Integer integer = userService.modifyById(userPo);
        if (integer!=1){
            throw new Exception("信息修改失败");
        }
    }
}
