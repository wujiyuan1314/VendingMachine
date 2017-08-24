package vend.service;

import java.util.List;

import base.util.Page;
import vend.entity.CodeCatalog;

public interface CodeCatalogService {
	/**
	 * 根据输入信息条件查询参数列表，并分页显示
	 * @param codeCatalog
	 * @param page
	 * @return
	 */
	List<CodeCatalog> listCodeCatalog(CodeCatalog codeCatalog, Page page);
	
	/**
	 * 修改一个参数
	 * @param codeCatalog
	 */
	int editCodeCatalog(CodeCatalog codeCatalog);
	/**
	 * 添加一个参数
	 * @param codeCatalog
	 */
	int insertCodeCatalog(CodeCatalog codeCatalog);
	
	/**
	 * 删除一个参数
	 * @param id
	 */
	int deleteCodeCatalog(String id);
	
	/**
	 * 批量删除参数
	 * @param ids
	 */
	void deleteCodeCatalogs(String[] ids);
	
	/**
	 * 根据ID获取参数信息
	 * @param id
	 * @return
	 */
	CodeCatalog getCodeCatalogByID(String id);
}
