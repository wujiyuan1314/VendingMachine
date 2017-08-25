package vend.service;
import java.util.List;

import vend.entity.Menuitem;

public interface MenuitemService {
	int editMenuitem(Menuitem menuitem);
	/**
	 * 添加一个菜单
	 * @param menuitem
	 */
	int insertMenuitem(Menuitem menuitem);
	
	/**
	 * 删除一个菜单
	 * @param id
	 */
	int deleteMenuitem(int id);
	
	/**
	 * 批量删除菜单
	 * @param ids
	 */
	void deleteMenuitems(int[] ids);
	
	/**
	 * 根据ID获取菜单信息
	 * @param id
	 * @return
	 */
	Menuitem getMenuitemByID(int id);
	/**
	 * 按照父ID查询菜单
	 * @return
	 */
	List<Menuitem> selectByParentId(int parentId);
	/**
	 * 查找全部
	 * @return
	 */
	List<Menuitem> findAll();
}
