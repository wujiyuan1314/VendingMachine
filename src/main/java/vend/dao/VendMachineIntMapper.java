package vend.dao;

import java.util.List;

import vend.entity.VendMachineInt;

public interface VendMachineIntMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VendMachineInt record);

    int insertSelective(VendMachineInt record);

    VendMachineInt selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VendMachineInt record);

    int updateByPrimaryKey(VendMachineInt record);
    /**下为自定义方法 */ 
    List<VendMachineInt> selectByBelongMachine(Integer belongMachine);
}