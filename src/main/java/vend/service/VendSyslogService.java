package vend.service;

import java.util.List;

import base.util.Page;
import vend.entity.VendSyslog;

public interface VendSyslogService {
	/**
	 * 根据输入信息条件查询日志，并分页显示
	 * @param vendSyslog
	 * @param page
	 * @return
	 */
	List<VendSyslog> listVendSyslog(VendSyslog vendSyslog, Page page);
	
	/**
	 * 修改一个日志
	 * @param vendSyslog
	 */
	int editVendSyslog(VendSyslog vendSyslog);
	/**
	 * 添加一个日志
	 * @param vendSyslog
	 */
	int insertVendSyslog(VendSyslog vendSyslog);
	
	/**
	 * 删除一个日志
	 * @param id
	 */
	int deleteVendSyslog(int id);
	
	/**
	 * 批量删除日志
	 * @param ids
	 */
	void deleteVendSyslogs(int[] ids);
	
	/**
	 * 根据ID获取日志信息
	 * @param id
	 * @return
	 */
	VendSyslog getOne(int id);
}
