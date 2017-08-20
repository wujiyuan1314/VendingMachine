package vend.dao;

import vend.entity.VendShopQrcode;

public interface VendShopQrcodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VendShopQrcode record);

    int insertSelective(VendShopQrcode record);

    VendShopQrcode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VendShopQrcode record);

    int updateByPrimaryKey(VendShopQrcode record);
}