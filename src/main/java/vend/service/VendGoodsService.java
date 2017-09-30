package vend.service;

import java.util.List;

import base.util.Page;
import vend.entity.VendGoods;
import vend.entity.VendMachine;
import vend.entity.VendOrder;

public interface VendGoodsService {
	/**
	 * 根据输入信息条件查询商品列表，并分页显示
	 * @param vendGoods
	 * @param page
	 * @return
	 */
	List<VendGoods> listVendGoods(VendGoods vendGoods,Page page);
	/**
	 * 添加商品
	 * @param vendGoods
	 * @return
	 */
	int insertVendGoods(VendGoods vendGoods);
	/**
	 * 修改商品
	 * @param vendGoods
	 * @return
	 */
	int editVendGoods(VendGoods vendGoods);
	/**
	 * 删除一个商品
	 * @param id
	 */
	void delVendGoods(int id);
	/**
	 * 批量删除商品
	 * @param id
	 */
	int delVendGoodss(int id[]);
	/**
	 * 根据ID查找一个商品
	 * @param id
	 * @return
	 */
	VendGoods getOne(int id);
	/**
	 * 查找全部
	 * @return
	 */
	List<VendGoods> findAll();
	/**
	 * 售卖指令
	 * @param vendMachine
	 * @param vendGoods
	 * @param vendOrder
	 */
	void sellGoods(VendMachine vendMachine,VendGoods vendGoods,VendOrder vendOrder,int heat);
	
}
