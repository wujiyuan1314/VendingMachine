package vend.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.util.CacheUtils;
import base.util.DateUtil;
import base.util.HttpClientUtil;
import base.util.Page;
import base.util.SysPara;
import net.sf.json.JSONObject;
import vend.dao.VendGoodsMapper;
import vend.entity.VendGoods;
import vend.entity.VendMachine;
import vend.entity.VendOrder;
import vend.service.VendGoodsService;

@Service
public class VendGoodsServiceImpl implements VendGoodsService {
	@Autowired
	VendGoodsMapper vendGoodsMapper;
	/**
	 * 商品列表
	 * @param vendGoods
	 * @param page
	 * @return
	 */                                                                   
	public List<VendGoods> listVendGoods(VendGoods vendGoods,Page page){
		int totalNumber = vendGoodsMapper.countVendGoods(vendGoods);
		page.setTotalNumber(totalNumber);
		String title=vendGoods.getGoodsName();
		String currentPage=Integer.toString(page.getCurrentPage());
		if(title==null){
			title="";
		}
		String key="key_listVendGoods"+title+currentPage;
		List<VendGoods> vendGoodss=(List<VendGoods>)CacheUtils.get("goodsCache", key);
		if(vendGoodss==null){
			vendGoodss= vendGoodsMapper.listVendGoods(vendGoods, page);
			CacheUtils.put("goodsCache",key, vendGoodss);
		}
		return vendGoodss;
	}
	/**
	 * 添加
	 * @param vendGoods
	 * @return
	 */
	public int insertVendGoods(VendGoods vendGoods){
		Date createTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		vendGoods.setCreateTime(createTime);
		vendGoods.setUpdateTime(createTime);
		int isOk= vendGoodsMapper.insertSelective(vendGoods);
		if(isOk==1){
			CacheUtils.clear();
		}
		return isOk;
	}
	/**
	 * 修改
	 * @param vendGoods
	 * @return
	 */
	public int editVendGoods(VendGoods vendGoods){
		int isOk=vendGoodsMapper.updateByPrimaryKeySelective(vendGoods);
		if(isOk==1){
			CacheUtils.clear();
		}
		return isOk;
	}
	/**
	 * 删除
	 * @param id
	 */
	public void delVendGoods(int id){
		int isOk=vendGoodsMapper.deleteByPrimaryKey(id);
		if(isOk==1){
			CacheUtils.clear();
		}
	}
	/**
	 * 批量删除
	 * @param id
	 */
	public int delVendGoodss(int ids[]){
		int isOk=vendGoodsMapper.deleteBatch(ids);
		if(isOk==1){
			CacheUtils.clear();
		}
		return isOk;
	}
	/**
	 * 得到一个商品
	 * @param id
	 * @return
	 */
	public VendGoods getOne(int id){
		String key="key_Goods_getOne"+id;
		VendGoods vendGoods=(VendGoods)CacheUtils.get("goodsCache", key);
		if(vendGoods==null){
			vendGoods=vendGoodsMapper.selectByPrimaryKey(id);
		}
		return vendGoods;
	}
	/**
	 * 查找全部
	 */
	public List<VendGoods> findAll() {
		// TODO Auto-generated method stub
		String key="key_Goods_findAll";
		List<VendGoods> vendGoodss=(List<VendGoods>)CacheUtils.get("goodsCache", key);
		if(vendGoodss==null){
			vendGoodss=vendGoodsMapper.findAll();
			CacheUtils.put("goodsCache", key, vendGoodss);
		}
		return vendGoodss;
	}
	/**
	 * 售卖商品指令
	 * @param vendMachine
	 * @param vendGoods
	 * @param orderId
	 */
	public void sellGoods(VendMachine vendMachine,VendGoods vendGoods,VendOrder vendOrder){
		JSONObject payload = new JSONObject();
		payload.accumulate("device_id", vendMachine.getMachineId());
		payload.accumulate("operation", "sell");
		payload.accumulate("order", vendOrder.getOrderId());
		//商品详情
		JSONObject orderGoods = new JSONObject();
		orderGoods.accumulate("chNo", vendGoods.getId());
		orderGoods.accumulate("count", vendOrder.getNum());
		//商品参数详情
		JSONObject params = new JSONObject();
		params.accumulate("selfCup", 1);
		orderGoods.accumulate("params", params);
		
		payload.accumulate("orderGoods", orderGoods);
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("id", vendMachine.getMachineId());
		dataMap.put("payload", payload);
		try {
			String retMsg = HttpClientUtil.httpPostRequest(SysPara.midPublishUrl,dataMap);
			if(StringUtils.isNotBlank(retMsg)){
				JSONObject retJson = JSONObject.fromObject(retMsg);	
				String retCode = retJson.getString("errCode");
				if(retCode.equals("0")){
					System.out.println("售卖成功:" + retJson.getString("msg"));
				}else{
					System.out.println("售卖失败:" + retJson.getString("msg"));
				}
			}
	    }catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		    e.printStackTrace();
	    }
	}
}
