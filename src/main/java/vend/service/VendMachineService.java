package vend.service;

import java.util.List;

import base.util.Page;
import vend.entity.VendMachine;

public interface VendMachineService {
	/**
	 * 根据输入信息条件查询机器列表，并分页显示
	 * @param vendMachine
	 * @param page
	 * @return
	 */
	List<VendMachine> listVendMachine(VendMachine vendMachine,Page page);
	/**
	 * 添加机器
	 * @param vendMachine
	 * @return
	 */
	int insertVendMachine(VendMachine vendMachine);
	/**
	 * 修改机器
	 * @param vendMachine
	 * @return
	 */
	int editVendMachine(VendMachine vendMachine);
	/**
	 * 删除一个机器
	 * @param id
	 */
	void delVendMachine(int id);
	/**
	 * 批量删除机器
	 * @param id
	 */
	int delVendMachines(int id[]);
	/**
	 * 根据ID查找一个机器
	 * @param id
	 * @return
	 */
	VendMachine getOne(int id);
	/**
	 * 查找全部
	 * @return
	 */
	List<VendMachine> findAll();
	
}
