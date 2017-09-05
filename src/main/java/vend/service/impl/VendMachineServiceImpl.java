package vend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.util.DateUtil;
import base.util.Page;
import vend.dao.VendMachineMapper;
import vend.entity.VendMachine;
import vend.service.VendMachineService;

@Service
public class VendMachineServiceImpl implements VendMachineService {
	@Autowired
	VendMachineMapper vendMachineMapper;
	/**
	 * 根据输入信息条件查询机器列表，并分页显示
	 * @param vendMachine
	 * @param page
	 * @return
	 */
	public List<VendMachine> listVendMachine(VendMachine vendMachine,Page page){
		int totalNumber = vendMachineMapper.countVendMachine(vendMachine);
		page.setTotalNumber(totalNumber);
		return vendMachineMapper.listVendMachine(vendMachine, page);
	}
	/**
	 * 添加机器
	 * @param vendMachine
	 * @return
	 */
	public int insertVendMachine(VendMachine vendMachine){
		Date createTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		vendMachine.setCreateTime(createTime);
		vendMachine.setUpdateTime(createTime);
		return vendMachineMapper.insertSelective(vendMachine);
	}
	/**
	 * 修改机器
	 * @param vendMachine
	 * @return
	 */
	public int editVendMachine(VendMachine vendMachine){
		return vendMachineMapper.updateByPrimaryKeySelective(vendMachine);
	}
	/**
	 * 删除一个机器
	 * @param id
	 */
	public void delVendMachine(int id){
		vendMachineMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 批量删除机器
	 * @param id
	 */
	public int delVendMachines(int ids[]){
		return vendMachineMapper.deleteBatch(ids);
	}
	/**
	 * 根据ID查找一个机器
	 * @param id
	 * @return
	 */
	public VendMachine getOne(int id){
		return vendMachineMapper.selectByPrimaryKey(id);
	}
	/**
	 * 查找全部
	 */
	public List<VendMachine> findAll() {
		// TODO Auto-generated method stub
		return vendMachineMapper.findAll();
	}
}
