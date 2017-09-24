package vend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import base.util.Page;
import vend.entity.VendMachine;

public interface VendMachineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VendMachine record);

    int insertSelective(VendMachine record);

    VendMachine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VendMachine record);

    int updateByPrimaryKey(VendMachine record);
    /**下为自定义方法 */
    List<VendMachine> listVendMachine(@Param("vendMachine") VendMachine vendMachine, @Param("page") Page page);
    
    int countVendMachine(VendMachine vendMachine);
    
    int deleteBatch(int ids[]);
    
    List<VendMachine> findAll();
    
    VendMachine selectByMachineCode(String machineCode);
    
    VendMachine selectByMachineId(String machineId);
    
    List<VendMachine> selectByUsercode(String usercodelist[]);
    
    List<VendMachine> selectByUsercode1();
}