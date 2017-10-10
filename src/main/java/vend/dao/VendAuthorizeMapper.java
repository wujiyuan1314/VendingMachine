package vend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import base.util.Page;
import vend.entity.VendAuthorize;

public interface VendAuthorizeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VendAuthorize record);

    int insertSelective(VendAuthorize record);

    VendAuthorize selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VendAuthorize record);

    int updateByPrimaryKey(VendAuthorize record);
    /**下为自定义方法 */
    List<VendAuthorize> listVendAuthorize(@Param("vendAuthorize") VendAuthorize vendAuthorize, @Param("page") Page page);
    
    int countVendAuthorize(VendAuthorize vendAuthorize);
    
    List<VendAuthorize> findAll();
    
    List<VendAuthorize> selectByUsercode(String usercode);
}