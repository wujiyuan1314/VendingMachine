package vend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.util.Page;
import vend.dao.CodeCatalogMapper;
import vend.dao.VendParaMapper;
import vend.entity.VendPara;
import vend.service.VendParaService;
@Service
public class VendParaServiceImpl implements VendParaService {
	@Autowired
	VendParaMapper vendParaMapper;
	@Override
	public int insertVendPara(VendPara vendPara) {
		// TODO Auto-generated method stub
		return vendParaMapper.insert(vendPara);
	}

	@Override
	public int editVendPara(VendPara vendPara) {
		// TODO Auto-generated method stub
		return vendParaMapper.updateByPrimaryKey(vendPara);
	}

	@Override
	public void delVendPara(int id) {
		// TODO Auto-generated method stub
		vendParaMapper.deleteByPrimaryKey(id);
	}

	@Override
	public VendPara getOne(int id) {
		// TODO Auto-generated method stub
		return vendParaMapper.selectByPrimaryKey(id);
	}
	public String selectByParaCode(String paraCode){
		return vendParaMapper.selectByParaCode(paraCode).getParaContent();
	}
}
