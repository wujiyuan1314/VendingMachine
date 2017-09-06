package vend.dao;

import vend.entity.VendPara;

public interface VendParaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VendPara record);

    int insertSelective(VendPara record);

    VendPara selectByPrimaryKey(Integer id);
    
    VendPara selectByParaCode(String paraCode);

    int updateByPrimaryKeySelective(VendPara record);

    int updateByPrimaryKey(VendPara record);
}