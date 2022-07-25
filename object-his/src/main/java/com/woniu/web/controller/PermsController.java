package com.woniu.web.controller;


import com.woniu.config.ResponseResult;
import com.woniu.entity.dto.PermsDto;
import com.woniu.mapper.mysql.PermsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
@Controller
@RequestMapping("/perms")
@RequiredArgsConstructor
public class PermsController {

    private final PermsMapper permsMapper;


    @GetMapping("/menu")
    public ResponseResult<List<PermsDto>> getPermsListByUsername(String username) {
        try {
            List<PermsDto> allMenu = permsMapper.getPermsListByUsername(username);

            //存放一级列表
            List<PermsDto> parentNodes = new ArrayList<>();
            for (PermsDto menu : allMenu) {
                if (menu.getParentid() == null) {
                    parentNodes.add(menu);
                }
            }

            for (PermsDto parent : parentNodes) {
                List<PermsDto> children = new ArrayList<>();
//            迭代所有的节点,找到自己的子节点
                for (PermsDto menu : allMenu) {
                    if (menu.getParentid() == parent.getId()) {
                        children.add(menu);
                    }
                }
                if (children.size()>0){
                    parent.setChildren(children);
                }
            }

            return new ResponseResult<List<PermsDto>>(parentNodes,"ok",200);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseResult<List<PermsDto>>(null,"加载导航栏失败",501);
        }

    }
}

