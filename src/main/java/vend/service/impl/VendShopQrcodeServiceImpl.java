package vend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import base.util.Page;
import vend.dao.VendShopQrcodeMapper;
import vend.entity.VendShopQrcode;
import vend.service.VendShopQrcodeService;
@Service
public class VendShopQrcodeServiceImpl implements VendShopQrcodeService {
	@Autowired
	VendShopQrcodeMapper vendShopQrcodeMapper;
	@Override
	@Cacheable(value="qrcodeCache")
	public List<VendShopQrcode> listVendShopQrcode(VendShopQrcode vendShopQrcode, Page page) {
		// TODO Auto-generated method stub
		int totalNumber = vendShopQrcodeMapper.countVendShopQrcode(vendShopQrcode);
		page.setTotalNumber(totalNumber);
		return vendShopQrcodeMapper.listVendShopQrcode(vendShopQrcode, page);
	}

	@Override
	public int insertVendShopQrcode(VendShopQrcode vendShopQrcode) {
		// TODO Auto-generated method stub
		return vendShopQrcodeMapper.insertSelective(vendShopQrcode);
	}

	@Override
	public int editVendShopQrcode(VendShopQrcode vendShopQrcode) {
		// TODO Auto-generated method stub
		return vendShopQrcodeMapper.updateByPrimaryKeySelective(vendShopQrcode);
	}

	@Override
	public void delVendShopQrcode(int id) {
		// TODO Auto-generated method stub
		vendShopQrcodeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int delVendShopQrcodes(int[] ids) {
		// TODO Auto-generated method stub
		return vendShopQrcodeMapper.deleteBatch(ids);
	}

	@Override
	@Cacheable(value="qrcodeCache")
	public VendShopQrcode getOne(int id) {
		// TODO Auto-generated method stub
		return vendShopQrcodeMapper.selectByPrimaryKey(id);
	}

	@Override
	@Cacheable(value="qrcodeCache")
	public List<VendShopQrcode> findAll() {
		// TODO Auto-generated method stub
		return vendShopQrcodeMapper.findAll();
	}
	/**
	 * 按照类型查找
	 * @param extend2
	 * @return
	 */
	@Cacheable(value="qrcodeCache")
	public List<VendShopQrcode> selectByType(String extend2){
		return vendShopQrcodeMapper.selectByType(extend2);
	}

}
