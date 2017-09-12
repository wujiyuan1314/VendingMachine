package vend.service;

import vend.entity.VendPara;

public interface VendParaService {
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
