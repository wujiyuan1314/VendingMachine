package vend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import base.util.CacheUtils;
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
	public List<VendOrder> listVendOrder(VendOrder vendOrder,String beginTime,String endTime,Page page){
		/*int totalNumber = vendOrderMapper.countVendOrder(vendOrder,beginTime,endTime);
		page.setTotalNumber(totalNumber);
		String title=vendOrder.getUsercode()+beginTime+endTime;
		String currentPage=Integer.toString(page.getCurrentPage());
		if(title==null){
			title="";
		}
		String key="key_listVendOrder"+title+currentPage;
		List<VendOrder> vendOrders=(List<VendOrder>)CacheUtils.get("orderCache", key);
		if(vendOrders==null){
			vendOrders=vendOrderMapper.listVendOrder(vendOrder,beginTime,endTime,page);
			CacheUtils.put("orderCache",key, vendOrders);
		}
		return vendOrders;*/
		int totalNumber = vendOrderMapper.countVendOrder(vendOrder,beginTime,endTime);
		page.setTotalNumber(totalNumber);
		List<VendOrder> vendOrders=vendOrderMapper.listVendOrder(vendOrder,beginTime,endTime,page);
		return vendOrders;
	}
	/**
	 * 按照参数查找订单信息
	 * @param vendOrder
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<VendOrder> selectByParams(VendOrder vendOrder,String mochinecodeArray[],String beginTime,String endTime){
		String key="key_selectByParams"+vendOrder.getUsercode()+mochinecodeArray.length+beginTime+endTime;
		List<VendOrder> vendOrders=(List<VendOrder>)CacheUtils.get("orderCache", key);
		if(vendOrders==null){
			vendOrders=vendOrderMapper.selectByParams(vendOrder,mochinecodeArray,beginTime, endTime);
			CacheUtils.put("orderCache",key, vendOrders);
		}
		return vendOrders;
	}
	/**
	 * 按照参数查找订单信息
	 * @param vendOrder
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<VendOrder> selectByParams1(VendOrder vendOrder,String beginTime,String endTime){
		String key="key_selectByParams"+vendOrder.getUsercode()+beginTime+endTime;
		List<VendOrder> vendOrders=(List<VendOrder>)CacheUtils.get("orderCache", key);
		if(vendOrders==null){
			vendOrders=vendOrderMapper.selectByParams1(vendOrder,beginTime, endTime);
			CacheUtils.put("orderCache",key, vendOrders);
		}
		return vendOrders;
	}
	/**
	 * 添加订单
	 * @param vendOrder
	 * @return
	 */
	public int insertVendOrder(VendOrder vendOrder){
		int isOk= vendOrderMapper.insertSelective(vendOrder);
		if(isOk==1){
			CacheUtils.clear();
		}
		return isOk;
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
		int isOk= vendOrderMapper.deleteByPrimaryKey(orderId);
		if(isOk==1){
			CacheUtils.clear();
		}
	}
	/**
	 * 批量删除订单
	 * @param id
	 */
	public int delVendOrders(String orderIds[]){
		int isOk= vendOrderMapper.deleteBatch(orderIds);
		if(isOk==1){
			CacheUtils.clear();
		}
		return isOk;
	}
	/**
	 * 根据ID查找一个订单
	 * @param id
	 * @return
	 */
	@Cacheable(value="orderCache")
	public VendOrder getOne(String orderId){
		return vendOrderMapper.selectByPrimaryKey(orderId);
	}
}
