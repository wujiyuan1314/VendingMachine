package vend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.util.CacheUtils;
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
		String title=codeCatalog.getCodename();
		String currentPage=Integer.toString(page.getCurrentPage());
		if(title==null){
			title="";
		}
		String key="key_listCodeCatalog"+title+currentPage;
		List<CodeCatalog> codeCatalogs=(List<CodeCatalog>)CacheUtils.get("codeCache", key);
		if(codeCatalogs==null){
			int totalNumber = codeCatalogMapper.countCodeCatalog(codeCatalog);
			page.setTotalNumber(totalNumber);
			codeCatalogs = codeCatalogMapper.listCodeCatalog(codeCatalog, page);;
			CacheUtils.put("codeCache",key, codeCatalogs);
		}
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
		String key="key_CodeCatalog_getCodeCatalogByID"+id;
		CodeCatalog codeCatalog=(CodeCatalog)CacheUtils.get("codeCache", key);
		if(codeCatalog==null){
			codeCatalog=codeCatalogMapper.selectByPrimaryKey(id);
			CacheUtils.put("codeCache", key, codeCatalog);
		}
		return codeCatalog;
	}

}
