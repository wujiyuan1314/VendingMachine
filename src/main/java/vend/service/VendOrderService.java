package vend.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import base.util.Page;
import vend.entity.VendOrder;

public interface VendOrderService {
	/**
	 * 根据输入信息条件查询订单列表，并分页显示
	 * @param vendOrder
	 * @param page
	 * @return
	 */
	List<VendOrder> listVendOrder(VendOrder vendOrder,String beginTime,String endTime,Page page);
	/**
	 * 按照参数查找订单信息
	 * @param vendOrder
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	List<VendOrder> selectByParams(VendOrder vendOrder,String beginTime,String endTime);
	/**
	 * 添加订单
	 * @param vendOrder
	 * @return
	 */
	int insertVendOrder(VendOrder vendOrder);
	/**
	 * 修改订单
	 * @param vendOrder
	 * @return
	 */
	int editVendOrder(VendOrder vendOrder);
	/**
	 * 删除一个订单
	 * @param id
	 */
	void delVendOrder(String orderId);
	/**
	 * 批量删除订单
	 * @param id
	 */
	int delVendOrders(String orderId[]);
	/**
	 * 根据ID查找一个订单
	 * @param id
	 * @return
	 */
	VendOrder getOne(String orderId);
	
}
