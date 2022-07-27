package com.woniu.web.fo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.entity.dto.RegisterDto;
import com.woniu.entity.po.RegisterPo;
import com.woniu.mapper.mysql.RegisterMysqlDao;
import com.woniu.util.ApplicationContextHolder;
import com.woniu.entity.converter.RegisterConverter;
import lombok.Data;
import org.springframework.context.ApplicationContext;

import java.util.List;

@Data
public class QueryPageInfo {
    private Integer pageNum;
    private Integer pageSize;
    private String name;
    private String status;

    public PageInfo<RegisterDto> exec() {
        ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
        RegisterConverter registerConverter = applicationContext.getBean(RegisterConverter.class);
        RegisterMysqlDao registerMysqlDao = applicationContext.getBean(RegisterMysqlDao.class);
        PageHelper.startPage(pageNum, pageSize);
        if (name.isEmpty()) {
            name = "";
        }
        QueryWrapper<RegisterPo> wrapper = new QueryWrapper<>();
        wrapper.like("name", name);
        if (!status.equals("-1") && !status.equals("")) {
            wrapper.eq("status", status);
        }
        List<RegisterPo> pos = registerMysqlDao.selectList(wrapper);
        PageInfo<RegisterPo> poPageInfo = new PageInfo<>(pos);
        PageInfo<RegisterDto> dtoPageInfo = registerConverter.from(poPageInfo);
        return dtoPageInfo;
    }




}
