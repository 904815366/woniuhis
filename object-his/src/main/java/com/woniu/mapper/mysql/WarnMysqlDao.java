package com.woniu.mapper.mysql;

import com.woniu.entity.dto.WarnDto;
import com.woniu.entity.po.WarnPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lh
 * @since 2022-07-23
 */
@Mapper
public interface WarnMysqlDao extends BaseMapper<WarnPo> {
    /**
     * 查询患者医嘱
     * @param id
     * @return
     */
    List<WarnDto> selectAllByPatientId(Integer id);

    /**
     * 修改医嘱
     * @param registerid
     * @param warntime
     */
    void updateWarnById(@Param("registerid") Integer registerid,@Param("warntime") Date warntime);

    /**
     * 删除医嘱
     * @param id
     */
    void deleteWarnById(Integer id);

    /**
     * 更改医嘱状态
     * @param ids
     */
    void updateWarnStatus(@Param("ids") String[] ids,@Param("status") Integer status,@Param("nurseid")Integer nurseid);

    /**
     * 新增医嘱
     * @param warnPo
     * @return
     */
    Integer createWarn(WarnPo warnPo);

//    通过患者住院号查询该患者所有未执行的医嘱
    @Select("select * from warn where registerid=#{registerid} and status=#{status}")
    List<WarnDto> queryWarnsByRegisterid(@Param("registerid") Integer registerid,@Param("status") String status);
}
