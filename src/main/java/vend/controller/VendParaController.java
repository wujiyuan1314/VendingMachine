package vend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import base.util.Page;
import vend.entity.VendPara;
import vend.entity.CodeLibrary;
import vend.service.VendParaService;
import vend.service.CodeLibraryService;

@Controller
@RequestMapping("/para")
public class VendParaController {
	public static Logger logger = Logger.getLogger(VendParaController.class);
	
	@Autowired
	VendParaService vendParaService;
	@RequiresPermissions({"para:paras"})
	@RequestMapping(value="/paras")
	public String listVendPara(Model model, @ModelAttribute VendPara vendPara, @ModelAttribute Page page, HttpServletRequest request) {
		
		String currentPageStr = request.getParameter("currentPage");
		logger.info(currentPageStr + "===========");
		if(currentPageStr != null){
			int currentPage = Integer.parseInt(currentPageStr);
			page.setCurrentPage(currentPage);
		}
		logger.info(page.toString());
		logger.info(vendPara.toString());
		List<VendPara> paras = vendParaService.listVendPara(vendPara, page);
		model.addAttribute("paras",paras);
		return "manage/para/para_list";
	}
	/**
	 * 跳转参数信息添加界面
	 * @param model
	 * @return
	 */
	@RequiresPermissions({"para:add"})
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute(new VendPara());
		return "manage/para/para_add";
	}
   /**
    * 添加参数信息
    * @param VendPara
    * @param br
    * @return
    */
	@RequiresPermissions({"para:add"})
    @RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Model model,@Validated VendPara VendPara,BindingResult br){
    	if(br.hasErrors()){
    		return "manage/para/para_add";
    	}
    	vendParaService.insertVendPara(VendPara);
    	return "redirect:paras";
	}
    /**
	 * 跳转参数修改界面
	 * @param model
	 * @return
	 */
    @RequiresPermissions({"para:edit"})
	@RequestMapping(value="/{id}/edit",method=RequestMethod.GET)
	public String edit(Model model,@PathVariable int id){
		VendPara para=vendParaService.getOne(id);
		model.addAttribute(para);
		return "manage/para/para_edit";
	}
	
   /**
    * 修改参数信息
    * @param para
    * @param br
    * @return
    */
    @RequiresPermissions({"para:edit"})
    @RequestMapping(value="/edit",method=RequestMethod.POST)
	public String editVendPara(Model model,@Validated VendPara para,BindingResult br){
    	if(br.hasErrors()){
    		return "manage/para/para_edit";
    	}
    	vendParaService.editVendPara(para);
		return "redirect:paras";
	}
    /**
     * 删除参数信息
     * @param user
     * @param br
     * @return
     */
    @RequiresPermissions({"para:del"})
    @RequestMapping(value="/{id}/del")
 	public String del(@PathVariable int id){
    	vendParaService.delVendPara(id);;
 		return "redirect:/para/paras";
 	}
}
