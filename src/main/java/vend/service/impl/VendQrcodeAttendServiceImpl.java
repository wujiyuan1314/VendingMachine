package vend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return vendQrcodeAttendMapper.listVendQrcodeAttend(vendQrcodeAttend, page);
	}

	@Override
	public int insertVendQrcodeAttend(VendQrcodeAttend vendQrcodeAttend) {
		// TODO Auto-generated method stub
		return vendQrcodeAttendMapper.insertSelective(vendQrcodeAttend);
	}

	@Override
	public int editVendQrcodeAttend(VendQrcodeAttend vendQrcodeAttend) {
		// TODO Auto-generated method stub
		return vendQrcodeAttendMapper.updateByPrimaryKeySelective(vendQrcodeAttend);
	}

	@Override
	public void delVendQrcodeAttend(int id) {
		// TODO Auto-generated method stub
		vendQrcodeAttendMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int delVendQrcodeAttends(int[] ids) {
		// TODO Auto-generated method stub
		return vendQrcodeAttendMapper.deleteBatch(ids);
	}

	@Override
	public VendQrcodeAttend getOne(int id) {
		// TODO Auto-generated method stub
		return vendQrcodeAttendMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<VendQrcodeAttend> findAll() {
		// TODO Auto-generated method stub
		return vendQrcodeAttendMapper.findAll();
	}

	@Override
	public List<VendQrcodeAttend> selectByParams(VendQrcodeAttend vendQrcodeAttend) {
		// TODO Auto-generated method stub
		return vendQrcodeAttendMapper.selectByParams(vendQrcodeAttend);
	}

}
