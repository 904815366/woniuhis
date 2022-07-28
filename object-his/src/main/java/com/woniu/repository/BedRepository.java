package com.woniu.repository;

import com.woniu.entity.po.BedPo;
import com.woniu.entity.po.FamilyPo;
import com.woniu.entity.po.WardPo;
import com.woniu.mapper.mysql.BedMysqlDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BedRepository {

    private final BedMysqlDao bedMysqlDao;

   public List<FamilyPo> qureyFamiy(){
       List<FamilyPo> familyPoList = bedMysqlDao.qureyFamiy();
       return familyPoList;
   }

    public List<WardPo> qureyWardByfid(Integer fid){
        List<WardPo> wardPoList = bedMysqlDao.qureyWardByfid(fid);
        return wardPoList;
    }

    public List<BedPo> qureyBedBywid(Integer wid){
        List<BedPo> bedPoList = bedMysqlDao.qureyBedBywid(wid);
        return bedPoList;
    }

    public void upBadStatu(Integer bid){//修改床位信息
        bedMysqlDao.upBadstatu(bid);
    }


}
