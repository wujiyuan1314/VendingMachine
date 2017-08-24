package vend.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
	
	@RequestMapping(value="/codeLibrarys")
	public String listCodeLibrary(Model model, @ModelAttribute CodeLibrary CodeLibrary,HttpServletRequest request) {
		List<CodeLibrary> codeLibrarys = codeLibraryService.listCodeLibrary(CodeLibrary);
		model.addAttribute("codeLibrarys",codeLibrarys);
		return "manage/codelibrary/codelibrary_list";
	}
	/**
	 * 跳转参数类别信息添加界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/{codeno}/add",method=RequestMethod.GET)
	public String addcodeLibrary(Model model,@PathVariable String codeno){
		model.addAttribute(new CodeLibrary());
		model.addAttribute("codeno", codeno);
		return "manage/codelibrary/codelibrary_add";
	}
   /**
    * 添加参数类别信息
    * @param CodeLibrary
    * @param br
    * @return
    */
    @RequestMapping(value="/add",method=RequestMethod.POST)
	public String addcodeLibrary(Model model,@Validated CodeLibrary codeLibrary,BindingResult br){
    	if(br.hasErrors()){
    		return "manage/codelibrary/codelibrary_add";
    	}
    	String id=Function.getUUID();
    	codeLibrary.setId(id);
    	Date addtime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());//创建时间
    	codeLibrary.setAddtime(addtime);
    	codeLibraryService.insertCodeLibrary(codeLibrary);
    	return "redirect:/codeCatalog/"+codeLibrary.getCodeno()+"/codelibrarylist";
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
	@RequestMapping(value="/{id}/edit",method=RequestMethod.GET)
	public String editCodeLibrary(Model model,@PathVariable String id){
		CodeLibrary codeLibrary=codeLibraryService.getCodeLibraryByID(id);
		model.addAttribute(codeLibrary);
		return "manage/codelibrary/codelibrary_edit";
	}
	
   /**
    * 修改参数类别信息
    * @param codeLibrary
    * @param br
    * @return
    */
    @RequestMapping(value="/edit",method=RequestMethod.POST)
	public String editCodeLibrary(Model model,@Validated CodeLibrary codeLibrary,BindingResult br){
    	if(br.hasErrors()){
    		return "manage/codelibrary/codelibrary_edit";
    	}
    	int isOk=codeLibraryService.editCodeLibrary(codeLibrary);
		return "redirect:/codeCatalog/"+codeLibrary.getCodeno()+"/codelibrarylist";
	}
    /**
     * 删除参数类别信息
     * @param user
     * @param br
     * @return
     */
    @RequestMapping(value="/{codeno}/{id}/del")
 	public String delcodeLibrary(@PathVariable String id,@PathVariable String codeno){
    	 codeLibraryService.deleteCodeLibrary(id);
 		return "redirect:/codeCatalog/"+codeno+"/codelibrarylist";
 	}
     /**
      * 批量删除参数类别信息
      * @param ids
      * @return
      */
    @RequestMapping(value="/dels")
  	public String delcodeLibrarys(String ids[]){
    	  codeLibraryService.deleteCodeLibrarys(ids);
  		return "manage/codelibrary/codelibrary_list";
  	}
}
