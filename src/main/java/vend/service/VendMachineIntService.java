package vend.service;
import java.util.List;

import vend.entity.VendMachineInt;

public interface VendMachineIntService {
	/**
	 * 修改一个机器初始化信息
	 * @param vendMachineInt
	 */
	int editVendMachineInt(VendMachineInt vendMachineInt);
	/**
	 * 添加一个机器初始化信息
	 * @param vendMachineInt
	 */
	int insertVendMachineInt(VendMachineInt vendMachineInt);
	
	/**
	 * 删除一个机器初始化信息
	 * @param id
	 */
	int deleteVendMachineInt(int id);
	/**
	 * 根据ID获取机器初始化信息信息
	 * @param id
	 * @return
	 */
	VendMachineInt getOne(int id);
	/**
	 * 根据所属机器获取机器初始化信息信息
	 * @param id
	 * @return
	 */
    List<VendMachineInt> selectByBelongMachine(Integer belongMachine);
}
