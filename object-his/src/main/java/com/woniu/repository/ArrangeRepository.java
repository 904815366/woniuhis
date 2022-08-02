package com.woniu.repository;

import com.woniu.mapper.mysql.ArrangeMysqlDao;
import com.woniu.mapper.redis.ArrangeRedisDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class ArrangeRepository {
    private final ArrangeMysqlDao arrangeMysqlDao;
    private final ArrangeRedisDao arrangeRedisDao;

    /**
     * 新增值班表(安排值班信息)
     * @param dutyuserid
     * @param checkListArr
     * @param createuserid
     * @param week
     * @return
     */
    public boolean addArr(Integer dutyuserid, String[] checkListArr,Integer createuserid,String week) {
        boolean b = true;
        Integer integer=1;
        if (checkListArr.length==0){
            return false;
        }
        for (String s : checkListArr) {
            if(week.equals("thisWeek")){
                integer = arrangeMysqlDao.addThis(dutyuserid, Integer.parseInt(s), createuserid);
            }else if (week.equals("nextWeek")){
                integer = arrangeMysqlDao.addNext(dutyuserid, Integer.parseInt(s), createuserid);
            }
                if (integer==1){
                    b=true;
                }else {
                    b=false;
                }
                if (b==false){
                    break;
                }
        }
        if (b==true){
            arrangeRedisDao.deleteById(dutyuserid);
        }
        return b;
    }

    public Boolean editArr(Integer dutyuserid, String[] checkListArr, Integer createuserid, String week) {
        boolean b = true;
        Integer integerAdd = 1;
        Integer integerDel = 1;
        //为空安排,只需删除数据库中原有的arrange信息
        if (checkListArr.length==0){
            if(week.equals("thisWeek")){
                integerDel = arrangeMysqlDao.delThisByDutyuserid(dutyuserid);
            }else if (week.equals("nextWeek")){
                integerDel = arrangeMysqlDao.delNextByDutyuserid(dutyuserid);
            }
            if (integerDel==1){
                b=true;
            }
        }else {
            //不为空安排,删除+新增
            if(week.equals("thisWeek")){
                //删除数据库中原有的arrange信息
                integerDel = arrangeMysqlDao.delThisByDutyuserid(dutyuserid);
            }else if (week.equals("nextWeek")){
                //删除数据库中原有的arrange信息
                integerDel = arrangeMysqlDao.delNextByDutyuserid(dutyuserid);
            }
            for (String s : checkListArr) {
                if (s.isEmpty()){
                    continue;
                }
                if(week.equals("thisWeek")){
                    integerAdd = arrangeMysqlDao.addThis(dutyuserid, Integer.parseInt(s), createuserid);
                }else if (week.equals("nextWeek")){
                    integerAdd = arrangeMysqlDao.addNext(dutyuserid, Integer.parseInt(s), createuserid);
                }
                if (integerAdd==1){
                    b=true;
                }else {
                    b=false;
                }
                if (b==false){
                    break;
                }
            }
        }
        if (b==true){
            arrangeRedisDao.deleteById(dutyuserid);
        }
        return b;
    }
}
