package vend.service;
import java.util.List;

import vend.entity.CodeLibrary;

public interface CodeLibraryService {
	/**
	 * 根据输入信息条件查询参数列表，并分页显示
	 * @param codeLibrary
	 * @param page
	 * @return
	 */
	List<CodeLibrary> listCodeLibrary(CodeLibrary codeLibrary);
	/**
	 * 按codeno查询参数信息
	 * @param codeLibrary
	 */
	List<CodeLibrary> selectByCodeNo(String codeno);
	
	/**
	 * 修改一本参数
	 * @param codeLibrary
	 */
	int editCodeLibrary(CodeLibrary codeLibrary);
	/**
	 * 添加一本参数
	 * @param codeLibrary
	 */
	int insertCodeLibrary(CodeLibrary codeLibrary);
	
	/**
	 * 删除一本参数
	 * @param id
	 */
	int deleteCodeLibrary(String id);
	
	/**
	 * 批量删除参数
	 * @param ids
	 */
	void deleteCodeLibrarys(String[] ids);
	
	/**
	 * 根据ID获取参数信息
	 * @param id
	 * @return
	 */
	CodeLibrary getCodeLibraryByID(String id);
}
