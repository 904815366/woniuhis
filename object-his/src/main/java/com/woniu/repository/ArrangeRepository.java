package com.woniu.repository;

import com.woniu.mapper.mysql.ArrangeMysqlDao;
import com.woniu.mapper.redis.ArrangeRedisDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
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
}
