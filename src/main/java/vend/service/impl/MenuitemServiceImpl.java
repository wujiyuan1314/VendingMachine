package vend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import vend.dao.MenuitemMapper;
import vend.entity.Menuitem;
import vend.service.MenuitemService;

@Service
public class MenuitemServiceImpl implements MenuitemService {

	@Autowired
	MenuitemMapper menuitemMapper;
	
	@Override
	@Cacheable(value="menuitemCache")
	public int editMenuitem(Menuitem menuitem) {
		// TODO Auto-generated method stub
		return menuitemMapper.updateByPrimaryKeySelective(menuitem);
	}

	@Override
	public int insertMenuitem(Menuitem menuitem) {
		// TODO Auto-generated method stub
		return menuitemMapper.insertSelective(menuitem);
	}

	@Override
	public int deleteMenuitem(int id) {
		// TODO Auto-generated method stub
		return menuitemMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteMenuitems(int[] ids) {
		// TODO Auto-generated method stub
       for(int id:ids){
    	   menuitemMapper.deleteByPrimaryKey(id);
       }
	}

	@Override
	@Cacheable(value="menuitemCache")
	public Menuitem getMenuitemByID(int id) {
		// TODO Auto-generated method stub
		return menuitemMapper.selectByPrimaryKey(id);
	}

	@Override
	@Cacheable(value="menuitemCache")
	public List<Menuitem> selectByParentId(int parentId) {
		// TODO Auto-generated method stub
		return menuitemMapper.selectByParentId(parentId);
	}
	/**
	 * 查找全部
	 * @return
	 */
	@Cacheable(value="menuitemCache")
	public List<Menuitem> findAll(){
		return menuitemMapper.findAll();
	}

}
