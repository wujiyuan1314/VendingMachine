package vend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import base.util.CacheUtils;
import vend.dao.CodeLibraryMapper;
import vend.entity.CodeLibrary;
import vend.service.CodeLibraryService;
@Service
public class CodeLibraryServiceImpl implements CodeLibraryService {
	@Autowired
	CodeLibraryMapper codeLibraryMapper;
	@Override
	@Cacheable(value="codeCache")
	public List<CodeLibrary> listCodeLibrary(CodeLibrary codeLibrary) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Cacheable(value="codeCache")
	public List<CodeLibrary> selectByCodeNo(String codeno) {
		// TODO Auto-generated method stub
		String key="key_selectByCodeNo"+codeno;
		List<CodeLibrary> codeLibrarys=(List<CodeLibrary>)CacheUtils.get("codeCache", key);
		if(codeLibrarys==null){
			codeLibrarys=codeLibraryMapper.selectByCodeNo(codeno);
			CacheUtils.put("codeCache", key, codeLibrarys);
		}
		return codeLibrarys;
	}

	@Override
	public int editCodeLibrary(CodeLibrary codeLibrary) {
		// TODO Auto-generated method stub
		return codeLibraryMapper.updateByPrimaryKeySelective(codeLibrary);
	}

	@Override
	public int insertCodeLibrary(CodeLibrary codeLibrary) {
		// TODO Auto-generated method stub
		return codeLibraryMapper.insertSelective(codeLibrary);
	}

	@Override
	public int deleteCodeLibrary(String id) {
		// TODO Auto-generated method stub
		return codeLibraryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteCodeLibrarys(String[] ids) {
		// TODO Auto-generated method stub
        for(int i=0;i<ids.length;i++){
        	codeLibraryMapper.deleteByPrimaryKey(ids[i]);
        }
	}

	@Override
	@Cacheable(value="codeCache")
	public CodeLibrary getCodeLibraryByID(String id) {
		// TODO Auto-generated method stub
		String key="key_getCodeLibraryByID"+id;
		CodeLibrary codeLibrary=(CodeLibrary)CacheUtils.get("codeCache", key);
		if(codeLibrary==null){
			codeLibrary=codeLibraryMapper.selectByPrimaryKey(id);
			CacheUtils.put("codeCache", key, codeLibrary);
		}
		return codeLibrary;
	}

}
