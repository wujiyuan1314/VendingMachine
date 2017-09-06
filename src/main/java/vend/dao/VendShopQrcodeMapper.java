package vend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import base.util.Page;
import vend.entity.VendShopQrcode;

public interface VendShopQrcodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VendShopQrcode record);

    int insertSelective(VendShopQrcode record);

    VendShopQrcode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VendShopQrcode record);

    int updateByPrimaryKey(VendShopQrcode record);
    /**下面是自定义方法*/
    List<VendShopQrcode> listVendShopQrcode(@Param("vendShopQrcode") VendShopQrcode vendShopQrcode, @Param("page") Page page);
    
    int countVendShopQrcode(VendShopQrcode vendShopQrcode);
    
    int deleteBatch(int ids[]);
    
    List<VendShopQrcode> findAll();
    
    List<VendShopQrcode> selectByType(String extend2);
}