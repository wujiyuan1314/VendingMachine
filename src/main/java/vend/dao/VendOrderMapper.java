package vend.dao;

import vend.entity.VendOrder;

public interface VendOrderMapper {
    int insert(VendOrder record);

    int insertSelective(VendOrder record);
}