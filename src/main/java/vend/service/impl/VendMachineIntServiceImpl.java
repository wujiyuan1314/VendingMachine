package vend.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vend.dao.VendMachineIntMapper;
import vend.entity.VendMachineInt;
import vend.service.VendMachineIntService;

@Service
public class VendMachineIntServiceImpl implements VendMachineIntService{
	@Autowired
	VendMachineIntMapper vendMachineIntMapper;
	/**
	 * 修改一个机器初始化信息
	 * @param vendMachineInt
	 */
	public int editVendMachineInt(VendMachineInt vendMachineInt){
		return vendMachineIntMapper.updateByPrimaryKeySelective(vendMachineInt);
	}
	/**
	 * 添加一个机器初始化信息
	 * @param vendMachineInt
	 */
	public int insertVendMachineInt(VendMachineInt vendMachineInt){
		return vendMachineIntMapper.insertSelective(vendMachineInt);
	}
	
	/**
	 * 删除一个机器初始化信息
	 * @param id
	 */
	public int deleteVendMachineInt(int id){
		return vendMachineIntMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 根据ID获取机器初始化信息信息
	 * @param id
	 * @return
	 */
    public VendMachineInt getOne(int id){
    	return vendMachineIntMapper.selectByPrimaryKey(id);
	}
    
    /**
	 * 根据所属机器获取机器初始化信息信息
	 * @param id
	 * @return
	 */
    public List<VendMachineInt> selectByBelongMachine(Integer belongMachine){
    	return vendMachineIntMapper.selectByBelongMachine(belongMachine);
	}
}
