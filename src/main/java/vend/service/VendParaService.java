package vend.service;

import java.util.List;

import base.util.Page;
import vend.entity.VendPara;

public interface VendParaService {
	/**
	 * 根据输入信息条件查询参数列表，并分页显示
	 * @param codeCatalog
	 * @param page
	 * @return
	 */
	List<VendPara> listVendPara(VendPara vendPara, Page page);
	/**
	 * 添加参数
	 * @param vendPara
	 * @return
	 */
	int insertVendPara(VendPara vendPara);
	/**
	 * 修改参数
	 * @param vendPara
	 * @return
	 */
	int editVendPara(VendPara vendPara);
	/**
	 * 删除一个参数
	 * @param id
	 */
	void delVendPara(int id);
	
	/**
	 * 根据ID查找一个参数
	 * @param id
	 * @return
	 */
	VendPara getOne(int id);
	/**
	 * 按照code查找code的值
	 * @param paraCode
	 * @return
	 */
	String selectByParaCode(String paraCode);
}
