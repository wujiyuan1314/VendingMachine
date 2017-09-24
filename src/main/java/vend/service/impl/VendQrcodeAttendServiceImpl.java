package vend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import base.util.CacheUtils;
import base.util.Page;
import vend.dao.VendQrcodeAttendMapper;
import vend.entity.VendQrcodeAttend;
import vend.service.VendQrcodeAttendService;
@Service
public class VendQrcodeAttendServiceImpl implements VendQrcodeAttendService {
	@Autowired
	VendQrcodeAttendMapper vendQrcodeAttendMapper;
	@Override
	public List<VendQrcodeAttend> listVendQrcodeAttend(VendQrcodeAttend vendQrcodeAttend, Page page) {
		// TODO Auto-generated method stub
		int totalNumber = vendQrcodeAttendMapper.countVendQrcodeAttend(vendQrcodeAttend);
		page.setTotalNumber(totalNumber);
		String title=vendQrcodeAttend.getUsercode();
		String currentPage=Integer.toString(page.getCurrentPage());
		if(title==null){
			title="";
		}
		String key="key_listVendQrcodeAttend"+title+currentPage;
		List<VendQrcodeAttend> vendQrcodeAttends=(List<VendQrcodeAttend>)CacheUtils.get("qrcodeCache", key);
		if(vendQrcodeAttends==null){
			vendQrcodeAttends= vendQrcodeAttendMapper.listVendQrcodeAttend(vendQrcodeAttend, page);
			CacheUtils.put("qrcodeCache",key, vendQrcodeAttends);
		}	
		return vendQrcodeAttends;
	}

	@Override
	public int insertVendQrcodeAttend(VendQrcodeAttend vendQrcodeAttend) {
		// TODO Auto-generated method stub
		int isOk= vendQrcodeAttendMapper.insertSelective(vendQrcodeAttend);
		if(isOk==1){
			CacheUtils.clear();
		}
		return isOk;
	}

	@Override
	public int editVendQrcodeAttend(VendQrcodeAttend vendQrcodeAttend) {
		// TODO Auto-generated method stub
		int isOk= vendQrcodeAttendMapper.updateByPrimaryKeySelective(vendQrcodeAttend);
		if(isOk==1){
			CacheUtils.clear();
		}
		return isOk;
	}

	@Override
	public void delVendQrcodeAttend(int id) {
		// TODO Auto-generated method stub
		int isOk= vendQrcodeAttendMapper.deleteByPrimaryKey(id);
		if(isOk==1){
			CacheUtils.clear();
		}
	}

	@Override
	public int delVendQrcodeAttends(int[] ids) {
		// TODO Auto-generated method stub
		int isOk= vendQrcodeAttendMapper.deleteBatch(ids);
		if(isOk==1){
			CacheUtils.clear();
		}
		return isOk;
	}

	@Override
	@Cacheable(value="qrcodeCache")
	public VendQrcodeAttend getOne(int id) {
		// TODO Auto-generated method stub
		return vendQrcodeAttendMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<VendQrcodeAttend> findAll() {
		// TODO Auto-generated method stub
		String key="key_VendQrcodeAttend_findAll";
		List<VendQrcodeAttend> vendQrcodeAttends=(List<VendQrcodeAttend>)CacheUtils.get("qrcodeCache", key);
		if(vendQrcodeAttends==null){
			vendQrcodeAttends=vendQrcodeAttendMapper.findAll();
			CacheUtils.put("qrcodeCache",key, vendQrcodeAttends);
		}
		return vendQrcodeAttends;
	}

	@Override
	public List<VendQrcodeAttend> selectByParams(VendQrcodeAttend vendQrcodeAttend) {
		// TODO Auto-generated method stub
		String key="key_selectByParams"+vendQrcodeAttend.getUsercode();
		List<VendQrcodeAttend> vendQrcodeAttends=(List<VendQrcodeAttend>)CacheUtils.get("qrcodeCache", key);
		if(vendQrcodeAttends==null){
			vendQrcodeAttends=vendQrcodeAttendMapper.selectByParams(vendQrcodeAttend);
			CacheUtils.put("qrcodeCache",key, vendQrcodeAttends);
		}
		return vendQrcodeAttends;
	}

}
