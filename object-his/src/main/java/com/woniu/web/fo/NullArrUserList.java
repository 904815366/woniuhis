package com.woniu.web.fo;

import com.woniu.entity.dto.ArrangeDto;
import com.woniu.entity.dto.NullArrUserDto;
import com.woniu.entity.po.UserPo;
import com.woniu.mapper.mysql.ArrangeMysqlDao;
import com.woniu.mapper.mysql.UserMysqlDao;
import com.woniu.util.ApplicationContextHolder;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import java.util.List;
@Data
public class NullArrUserList {
    private String searchName;
    private Integer searchRoleid;
    private Integer searchFamilyid;
    private String week;
    public List<NullArrUserDto> getList() {
        ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
        UserMysqlDao userMysqlDao = applicationContext.getBean(UserMysqlDao.class);
        List<NullArrUserDto> userList = null;
        UserPo userPo = new UserPo();
        if(!StringUtils.isEmpty(searchName)){
            userPo.setName(searchName);
        }else {
            userPo.setName("");
        }
        if (!StringUtils.isEmpty(searchRoleid)){
            userPo.setRoleid(searchRoleid);
        }
        if (!StringUtils.isEmpty(searchFamilyid)){
            userPo.setFamilyid(searchFamilyid);
        }
        if (week.equals("thisWeek")){
            userList = userMysqlDao.getThisList(userPo);
        }else if (week.equals("nextWeek")){
            userList = userMysqlDao.getNextList(userPo);
        }
         return userList;
    }
}
