package vend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.util.DateUtil;
import base.util.Function;
import base.util.Page;
import vend.dao.VendOrderMapper;
import vend.entity.VendOrder;
import vend.service.VendOrderService;

@Service
public class VendOrderServiceImpl implements VendOrderService {
	@Autowired
	VendOrderMapper vendOrderMapper;
	/**
	 * 根据输入信息条件查询订单列表，并分页显示
	 * @param vendOrder
	 * @param page
	 * @return
	 */
	public List<VendOrder> listVendOrder(VendOrder vendOrder,Page page){
		int totalNumber = vendOrderMapper.countVendOrder(vendOrder);
		page.setTotalNumber(totalNumber);
		return vendOrderMapper.listVendOrder(vendOrder, page);
	}
	/**
	 * 添加订单
	 * @param vendOrder
	 * @return
	 */
	public int insertVendOrder(VendOrder vendOrder){
		Date createTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		String orderId=Function.getOrderId();
		vendOrder.setUsercode(orderId);
		vendOrder.setCreateTime(createTime);
		return vendOrderMapper.insertSelective(vendOrder);
	}
	/**
	 * 修改订单
	 * @param vendOrder
	 * @return
	 */
	public int editVendOrder(VendOrder vendOrder){
		return vendOrderMapper.updateByPrimaryKeySelective(vendOrder);
	}
	/**
	 * 删除一个订单
	 * @param id
	 */
	public void delVendOrder(String orderId){
		vendOrderMapper.deleteByPrimaryKey(orderId);
	}
	/**
	 * 批量删除订单
	 * @param id
	 */
	public int delVendOrders(String orderIds[]){
		return vendOrderMapper.deleteBatch(orderIds);
	}
	/**
	 * 根据ID查找一个订单
	 * @param id
	 * @return
	 */
	public VendOrder getOne(String orderId){
		return vendOrderMapper.selectByPrimaryKey(orderId);
	}
}
