package vend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.util.Page;
import vend.dao.CodeCatalogMapper;
import vend.entity.CodeCatalog;
import vend.service.CodeCatalogService;
@Service
public class CodeCatalogServiceImpl implements CodeCatalogService {
	@Autowired
	CodeCatalogMapper codeCatalogMapper;
	@Override
	public List<CodeCatalog> listCodeCatalog(CodeCatalog codeCatalog, Page page) {
		// TODO Auto-generated method stub
		int totalNumber = codeCatalogMapper.countCodeCatalog(codeCatalog);
		page.setTotalNumber(totalNumber);
		List<CodeCatalog> codeCatalogs = codeCatalogMapper.listCodeCatalog(codeCatalog, page);
		return codeCatalogs;
	}

	@Override
	public int editCodeCatalog(CodeCatalog codeCatalog) {
		// TODO Auto-generated method stub
		return codeCatalogMapper.updateByPrimaryKeySelective(codeCatalog);
	}

	@Override
	public int insertCodeCatalog(CodeCatalog codeCatalog) {
		// TODO Auto-generated method stub
		return codeCatalogMapper.insertSelective(codeCatalog);
	}

	@Override
	public int deleteCodeCatalog(String id) {
		// TODO Auto-generated method stub
		return codeCatalogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteCodeCatalogs(String[] ids) {
		// TODO Auto-generated method stub
		for(int i=0;i<ids.length;i++){
			codeCatalogMapper.deleteByPrimaryKey(ids[i]);
		}
	}

	@Override
	public CodeCatalog getCodeCatalogByID(String id) {
		// TODO Auto-generated method stub
		return codeCatalogMapper.selectByPrimaryKey(id);
	}

}
