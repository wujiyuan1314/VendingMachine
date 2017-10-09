	package vend.controller;

import java.util.Date;
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

import base.util.DateUtil;
import base.util.Function;
import vend.entity.CodeLibrary;
import vend.service.CodeLibraryService;

@Controller
@RequestMapping("/codeLibrary")
public class CodeLibraryController {
	public static Logger logger = Logger.getLogger(CodeLibraryController.class);
	
	@Autowired
	CodeLibraryService codeLibraryService;
	@RequiresPermissions({"codeLibrary:codeLibrarys"})
	@RequestMapping(value="/codeLibrarys")
	public String listCodeLibrary(Model model, @ModelAttribute CodeLibrary CodeLibrary,HttpServletRequest request) {
		List<CodeLibrary> codeLibrarys = codeLibraryService.listCodeLibrary(CodeLibrary);
		model.addAttribute("codeLibrarys",codeLibrarys);
		return "manage/codelibrary/codelibrary_list";
	}
	/**
	 * 广告屏样式
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/adscreen")
	public String listAdscreen(Model model) {
		List<CodeLibrary> codeLibrarys = codeLibraryService.selectByCodeNo("ADSCREEN");
		model.addAttribute("codeno", "ADSCREEN");
		model.addAttribute("codeLibrarys",codeLibrarys);
		return "manage/codelibrary/codelibrary_adscreen";
	}
	/**
	 * 优惠券地址设置
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/areaset")
	public String areaSet(Model model){
		List<CodeLibrary> couponareas=codeLibraryService.selectByCodeNo("COUPONAREA");
		model.addAttribute("codeno", "COUPONAREA");
		model.addAttribute("couponareas", couponareas);
		return "manage/codelibrary/codelibrary_areaset";
	}
	/**
	 * 微信商户信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/weixinset")
	public String weixinSet(Model model){
		List<CodeLibrary> wechatpubnos=codeLibraryService.selectByCodeNo("WECHATPUBNO");
		model.addAttribute("codeno", "WECHATPUBNO");
		model.addAttribute("wechatpubnos", wechatpubnos);
		return "manage/codelibrary/codelibrary_weixinset";
	}
	/**
	 * 跳转参数类别信息添加界面
	 * @param model
	 * @return
	 */
	@RequiresPermissions({"codeLibrary:add"})
	@RequestMapping(value="/{codeno}/add",method=RequestMethod.GET)
	public String addcodeLibrary(Model model,@PathVariable String codeno){
		model.addAttribute(new CodeLibrary());
		model.addAttribute("codeno", codeno);
		return "manage/codelibrary/codelibrary_add";
	}
   /**
    * 添加参数类别信息
    * @param request
    * @param model
    * @param codeLibrary
    * @param br
    * @return
    */
	@RequiresPermissions({"codeLibrary:add"})
    @RequestMapping(value="/add",method=RequestMethod.POST)
	public String addcodeLibrary(HttpServletRequest request,Model model,@Validated CodeLibrary codeLibrary,BindingResult br){
    	if(codeLibrary.getCodeno().equals("ADSCREEN")){
    		if(codeLibrary.getExtend2()==null||codeLibrary.getExtend2().equals("")){
    			br.rejectValue("extend2", "NOTEMPTY", "广告屏页web面地址不能为空");
    		}
    	}
		if(br.hasErrors()){
    		return "manage/codelibrary/codelibrary_add";
    	}
    	String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
    	String id=Function.getUUID();
    	codeLibrary.setId(id);
    	Date addtime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());//创建时间
    	codeLibrary.setAddtime(addtime);
    	codeLibraryService.insertCodeLibrary(codeLibrary);
    	
    	String returnStr="redirect:"+basePath+"/codeCatalog/"+codeLibrary.getCodeno()+"/codelibrarylist";
    	if(codeLibrary.getCodeno().equals("WECHATPUBNO")){
    		returnStr="redirect:weixinset";
    	}
    	if(codeLibrary.getCodeno().equals("COUPONAREA")){
    		returnStr="redirect:areaset";
    	}
    	if(codeLibrary.getCodeno().equals("ADSCREEN")){
    		returnStr="redirect:adscreen";
    	}
    	
    	return returnStr;
	}
    /**
     * 跳转到批量添加参数类别页面
     * @return
     */
    @RequestMapping(value="/addbatch",method=RequestMethod.GET)
    public String addCodeLibraryBatch(){
    	return "manage/codelibrary/codelibrary_addbatch";
    }
    /**
	 * 跳转参数类别修改界面
	 * @param model
	 * @return
	 */
    @RequiresPermissions({"codeLibrary:edit"})
	@RequestMapping(value="/{id}/edit",method=RequestMethod.GET)
	public String editCodeLibrary(Model model,@PathVariable String id){
		CodeLibrary codeLibrary=codeLibraryService.getCodeLibraryByID(id);
		model.addAttribute("codeno", codeLibrary.getCodeno());
		model.addAttribute(codeLibrary);
		return "manage/codelibrary/codelibrary_edit";
	}
	
   /**
    * 修改参数类别信息
    * @param codeLibrary
    * @param br
    * @return
    */
    @RequiresPermissions({"codeLibrary:edit"})
    @RequestMapping(value="/edit",method=RequestMethod.POST)
	public String editCodeLibrary(Model model,@Validated CodeLibrary codeLibrary,BindingResult br){
    	if(codeLibrary.getCodeno().equals("ADSCREEN")){
    		if(codeLibrary.getExtend2()==null||codeLibrary.getExtend2().equals("")){
    			br.rejectValue("extend2", "NOTEMPTY", "广告屏页web面地址不能为空");
    		}
    	}
    	if(br.hasErrors()){
    		return "manage/codelibrary/codelibrary_edit";
    	}
    	codeLibraryService.editCodeLibrary(codeLibrary);
    	
    	String returnStr="redirect:/codeCatalog/"+codeLibrary.getCodeno()+"/codelibrarylist";
    	if(codeLibrary.getCodeno().equals("WECHATPUBNO")){
    		returnStr="redirect:weixinset";
    	}
    	if(codeLibrary.getCodeno().equals("COUPONAREA")){
    		returnStr="redirect:areaset";
    	}
    	if(codeLibrary.getCodeno().equals("ADSCREEN")){
    		returnStr="redirect:adscreen";
    	}
    	
    	return returnStr; 
	}
    /**
     * 删除参数类别信息
     * @param user
     * @param br
     * @return
     */
    @RequiresPermissions({"codeLibrary:del"})
    @RequestMapping(value="/{codeno}/{id}/del")
 	public String delcodeLibrary(@PathVariable String id,@PathVariable String codeno){
    	
    	codeLibraryService.deleteCodeLibrary(id);
    	 
 		String returnStr="redirect:/codeCatalog/"+codeno+"/codelibrarylist";
    	if(codeno.equals("WECHATPUBNO")){
    		returnStr="redirect:weixinset";
    	}
    	if(codeno.equals("COUPONAREA")){
    		returnStr="redirect:areaset";
    	}
    	if(codeno.equals("ADSCREEN")){
    		returnStr="redirect:adscreen";
    	}
    	
    	return returnStr; 
 	}
     /**
      * 批量删除参数类别信息
      * @param ids
      * @return
      */
    @RequiresPermissions({"codeLibrary:dels"})
    @RequestMapping(value="/dels")
  	public String delcodeLibrarys(String ids[]){
    	  codeLibraryService.deleteCodeLibrarys(ids);
  		return "manage/codelibrary/codelibrary_list";
  	}
}
