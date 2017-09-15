package vend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import base.util.Page;
import vend.entity.VendPara;

public interface VendParaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VendPara record);

    int insertSelective(VendPara record);

    VendPara selectByPrimaryKey(Integer id);
    
    VendPara selectByParaCode(String paraCode);

    int updateByPrimaryKeySelective(VendPara record);

    int updateByPrimaryKey(VendPara record);
    // 下面为自定义方法
 	List<VendPara> listVendPara(@Param("vendPara") VendPara vendPara, @Param("page") Page page);
 	
 	int countVendPara(VendPara vendPara);
}