package vend.controller;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import base.util.Function;
import base.util.HttpClientUtil;
import base.util.Page;
import base.util.SysPara;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import vend.entity.CodeLibrary;
import vend.entity.VendAd;
import vend.entity.VendMachine;
import vend.entity.VendShopQrcode;
import vend.entity.VendUser;
import vend.service.CodeLibraryService;
import vend.service.VendAdService;
import vend.service.VendMachineService;
import vend.service.VendShopQrcodeService;

@Controller
@RequestMapping("/manage")
public class VendManageController{
	public static Logger logger = Logger.getLogger(VendManageController.class);
	
	@Autowired
	VendMachineService vendMachineService;
	@Autowired
	VendAdService vendAdService;
	@Autowired
	VendShopQrcodeService vendShopQrcodeService;
	@Autowired
	CodeLibraryService codeLibraryService;
	/**
	 * 根据输入信息条件查询机器列表，并分页显示
	 * @param model
	 * @param vendMachine
	 * @param page
	 * @param request
	 * @return
	 */
	@RequiresPermissions({"machine:machines"})
	@RequestMapping(value="/machines")
	public String listVendMachine(Model model,@ModelAttribute VendMachine vendMachine, @ModelAttribute Page page,HttpServletRequest request) {
		String currentPageStr = request.getParameter("currentPage");
		logger.info(currentPageStr + "===========");
		if(currentPageStr != null){
			int currentPage = Integer.parseInt(currentPageStr);
			page.setCurrentPage(currentPage);
		}
		logger.info(page.toString());
		logger.info(vendMachine.toString());
		HttpSession session=request.getSession();
		VendUser user=(VendUser)session.getAttribute("vendUser");
		if(user!=null){
			vendMachine.setUsercode(user.getUsercode());
		}
		List<CodeLibrary> usestatus=codeLibraryService.selectByCodeNo("USESTATUS");
		model.addAttribute("usestatus", usestatus);
		List<VendMachine> vendMachines = vendMachineService.listVendMachine(vendMachine, page);
		model.addAttribute("vendMachines",vendMachines);
		return "manage/machine/machine_list";
	}
	
	/**
	 * 设备登陆
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/login")
	public String login(@PathVariable int id){
		VendMachine vendMachine=vendMachineService.getOne(id);
		JSONObject payload = new JSONObject();
		payload.accumulate("device_id", "1g8p5465d");
		payload.accumulate("device_type", vendMachine.getMachineType());
		payload.accumulate("operation", "login");
		payload.accumulate("hwAddr", "1234567890abcdef");
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("id", "1g8p5865c");
		dataMap.put("payload", payload);
		try {
			String retMsg = HttpClientUtil.httpPostRequest(SysPara.midPublishUrl,dataMap);
			if(StringUtils.isNotBlank(retMsg)){
				JSONObject retJson = JSONObject.fromObject(retMsg);	
				String retCode = retJson.getString("result");
				if(retCode.equals("0")){
					if(vendMachine!=null){
						vendMachine.setUseStatus("1");
						vendMachineService.editVendMachine(vendMachine);
					}
				}				
			}else{
				System.out.println("登陆失败");
			}
			
			return "redirect:../machines";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:../machines"; 
	}
	
	/**
	 * 设备自清洗
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/autoClean")
	public String autoClean(@PathVariable int id){
		System.out.println(id);
		VendMachine vendMachine=vendMachineService.getOne(id);
		JSONObject payload = new JSONObject();
		payload.accumulate("device_id", id);
		payload.accumulate("operation", "autoClean");
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("id", id);
		dataMap.put("payload", payload);
		try {
			String retMsg = HttpClientUtil.httpPostRequest(SysPara.midPublishUrl,dataMap);
			if(StringUtils.isNotBlank(retMsg)){
				JSONObject retJson = JSONObject.fromObject(retMsg);	
				String retCode = retJson.getString("error_code");
				if(retCode.equals("0")){
					/*if(vendMachine!=null){
						vendMachine.setUseStatus("1");
						vendMachineService.editVendMachine(vendMachine);
					}*/
				}				
			}else{
				System.out.println("自清洗失败");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:../machines"; 
	}
	
	/**
	 * 设备重启
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/reboot")
	public String reboot(@PathVariable int id){
		System.out.println(id);
		VendMachine vendMachine=vendMachineService.getOne(id);
		JSONObject payload = new JSONObject();
		payload.accumulate("device_id", id);
		payload.accumulate("operation", "reboot");
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("id", "1g8p5865c");
		dataMap.put("payload", payload);
		try {
			String retMsg = HttpClientUtil.httpPostRequest(SysPara.midPublishUrl,dataMap);
			if(StringUtils.isNotBlank(retMsg)){
				JSONObject retJson = JSONObject.fromObject(retMsg);	
				String retCode = retJson.getString("result");
				if(retCode.equals("0")){
					if(vendMachine!=null){
						vendMachine.setUseStatus("1");
						vendMachineService.editVendMachine(vendMachine);
					}
				}else{
					System.out.println("重启失败:" + retJson.getString("msg"));
				}				
			}else{
				System.out.println("重启失败");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:../machines"; 
	}
	
	/**
	 * 设备关机
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/shutdown")
	public String shutdown(@PathVariable int id){
		System.out.println(id);
		VendMachine vendMachine=vendMachineService.getOne(id);
		JSONObject payload = new JSONObject();
		payload.accumulate("device_id", "1g8p5865c");
		payload.accumulate("operation", "shutdown");
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("id", "1g8p5865c");
		dataMap.put("payload", payload);
		try {
			String retMsg = HttpClientUtil.httpPostRequest(SysPara.midPublishUrl,dataMap);
			if(StringUtils.isNotBlank(retMsg)){
				JSONObject retJson = JSONObject.fromObject(retMsg);	
				String retCode = retJson.getString("result");
				if(retCode.equals("0")){
					if(vendMachine!=null){
						vendMachine.setUseStatus("2");
						vendMachineService.editVendMachine(vendMachine);
					}
				}else{
					System.out.println("关机失败:" + retJson.getString("msg"));
				}				
			}else{
				System.out.println("关机失败");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success"; 
	}
	
	/**
	 * 设备自检
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/selfCheck")
	public String selfCheck(@PathVariable int id){
		System.out.println(id);
		VendMachine vendMachine=vendMachineService.getOne(id);
		JSONObject payload = new JSONObject();
		payload.accumulate("device_id", id);
		payload.accumulate("operation", "selfCheck");
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("id", id);
		dataMap.put("payload", payload);
		try {
			String retMsg = HttpClientUtil.httpPostRequest(SysPara.midPublishUrl,dataMap);
			if(StringUtils.isNotBlank(retMsg)){
				JSONObject retJson = JSONObject.fromObject(retMsg);	
				String retCode = retJson.getString("result");
				if(retCode.equals("0")){
					/*if(vendMachine!=null){
						vendMachine.setUseStatus("2");
						vendMachineService.editVendMachine(vendMachine);
					}*/
				}else{
					System.out.println("自检失败:" + retJson.getString("msg"));
				}				
			}else{
				System.out.println("自检失败");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:../machines"; 
	}
	
	/**
	 * 获取设备参数
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/getDevParam")
	public String getDevParam(@PathVariable int id){
		JSONObject payload = new JSONObject();
		payload.accumulate("device_id", id);
		payload.accumulate("operation", "getDevParam");
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("id", id);
		dataMap.put("payload", payload);
		try {
			String retMsg = HttpClientUtil.httpPostRequest(SysPara.midPublishUrl,dataMap);
			if(StringUtils.isNotBlank(retMsg)){
				JSONObject retJson = JSONObject.fromObject(retMsg);	
				String params = retJson.getString("params");
				if(StringUtils.isNotBlank(params)){
					JSONObject paramsJson = JSONObject.fromObject(params);					
				}else{
					System.out.println("自检失败:" + retJson.getString("msg"));
				}				
			}else{
				System.out.println("自检失败");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:../machines"; 
	}
	
	/**
	 * 设置设备参数
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/setDevParam")
	public String setDevParam(@PathVariable int id){
		JSONObject payload = new JSONObject();
		payload.accumulate("device_id", "1g8p5865c");
		payload.accumulate("operation", "setDevParam");
		JSONObject params = new JSONObject();
		params.accumulate("chNo", "0");
		params.accumulate("water", "35");
		params.accumulate("mater", "65");
		payload.accumulate("params", "params");
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("id", id);
		dataMap.put("payload", payload);
		try {
			String retMsg = HttpClientUtil.httpPostRequest(SysPara.midPublishUrl,dataMap);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:../machines"; 
	}
	
	/**
	 * 更新显示的二维码
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/setQR")
	public String setQR(@PathVariable int id){
		JSONObject payload = new JSONObject();
		payload.accumulate("device_id", id);
		payload.accumulate("operation", "setQR");
		//TODO 二维码判断是否重复
		payload.accumulate("qrPic", "http://device.xxx.com/adsdfa.jpg");
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("id", id);
		dataMap.put("payload", payload);
		try {
			String retMsg = HttpClientUtil.httpPostRequest(SysPara.midPublishUrl,dataMap);
			JSONObject retJson = JSONObject.fromObject(retMsg);	
			String retCode = retJson.getString("result");
			if(retCode.equals("0")){
				
			}else{
				System.out.println("更新显示的二维码:" + retJson.getString("msg"));
			}	
		} catch (UnsupportedEncodingException e) {
			// TODO 更新显示的二维码异常处理
			e.printStackTrace();
		}
		return "redirect:../machines"; 
	}
	
	/**
	 * 更新显示的机器识别码
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/setCsrCode")
	public String setCsrCode(@PathVariable int id){
		JSONObject payload = new JSONObject();
		payload.accumulate("device_id", id);
		payload.accumulate("operation", "setCsrCode");
		//TODO 机器码需要判断是否重复
		payload.accumulate("csrCode", "1234");
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("id", id);
		dataMap.put("payload", payload);
		try {
			String retMsg = HttpClientUtil.httpPostRequest(SysPara.midPublishUrl,dataMap);
			JSONObject retJson = JSONObject.fromObject(retMsg);	
			String retCode = retJson.getString("result");
			if(retCode.equals("0")){
				
			}else{
				System.out.println("重启失败:" + retJson.getString("msg"));
			}	
		} catch (UnsupportedEncodingException e) {
			// TODO 更新机器码异常处理
			e.printStackTrace();
		}
		return "redirect:../machines"; 
	}
	
	/**
	 * 请求机器节目信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/freshAdItems")
	public String freshAdItems(@PathVariable int id){
		JSONObject payload = new JSONObject();
		payload.accumulate("device_id", id);
		payload.accumulate("operation", "freshAdItems");
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("id", id);
		dataMap.put("payload", payload);
		try {
			String retMsg = HttpClientUtil.httpPostRequest(SysPara.midPublishUrl,dataMap);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:../machines"; 
	}
	
	/**
	 * 设置机器节目信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/setAdItemList")
	public String setAdItemList(@PathVariable int id){
		JSONObject payload = new JSONObject();
		payload.accumulate("device_id", id);
		payload.accumulate("operation", "setAdItemList");
		payload.accumulate("qrPic", "1234567890abcdef");
		payload.accumulate("csrCode", "1234567890abcdef");
		payload.accumulate("styleDoc", "1234567890abcdef");		
		JSONObject pic = new JSONObject();
		pic.accumulate("fileName", "xxx.jpg");
		pic.accumulate("fileMd5", "xxxx");
		pic.accumulate("fileSize", 14839);
		pic.accumulate("fileUrl", "http://device.xxx.com/xxxx.jpg");
		JSONArray picArray = new JSONArray();
		picArray.add(pic);
		payload.accumulate("picList", picArray);
		JSONObject mov = new JSONObject();
		JSONArray movArray = new JSONArray();
		pic.accumulate("fileName", "xxx.wmv");
		pic.accumulate("fileMd5", "xxxx");
		pic.accumulate("fileSize", 14839);
		pic.accumulate("fileUrl", "http://device.xxx.com/xxxx.wmv");
		movArray.add(mov);
		payload.accumulate("movList", movArray);
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("id", id);
		dataMap.put("payload", payload);
		try {
			String retMsg = HttpClientUtil.httpPostRequest(SysPara.midPublishUrl,dataMap);
			if(StringUtils.isNotBlank(retMsg)){
				JSONObject retJson = JSONObject.fromObject(retMsg);	
				String retCode = retJson.getString("result");
				if(retCode.equals("0")){
					/*if(vendMachine!=null){
						vendMachine.setUseStatus("2");
						vendMachineService.editVendMachine(vendMachine);
					}*/
				}else{
					System.out.println("自检失败:" + retJson.getString("msg"));
				}				
			}else{
				System.out.println("自检失败");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:../machines"; 
	}
	
	/**
	 * 上传设备告警信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/reportAlarm")
	public String reportAlarm(@PathVariable int id){
		JSONObject payload = new JSONObject();
		payload.accumulate("device_id", id);
		payload.accumulate("operation", "login");
		payload.accumulate("alarmLv", 1);	
		JSONObject alarms = new JSONObject();
		payload.accumulate("item1", "a1");
		payload.accumulate("item2", "a2");
		payload.accumulate("alarms", alarms);
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("id", id);
		dataMap.put("payload", payload);
		try {
			String retMsg = HttpClientUtil.httpPostRequest(SysPara.midPublishUrl,dataMap);
			if(StringUtils.isNotBlank(retMsg)){
				JSONObject retJson = JSONObject.fromObject(retMsg);	
				String retCode = retJson.getString("result");
				if(retCode.equals("0")){
					/*if(vendMachine!=null){
						vendMachine.setUseStatus("2");
						vendMachineService.editVendMachine(vendMachine);
					}*/
				}else{
					System.out.println("自检失败:" + retJson.getString("msg"));
				}				
			}else{
				System.out.println("自检失败");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:../machines"; 
	}
	
	/**
	 * 售卖指令
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/sell")
	public String sell(@PathVariable int id){
		JSONObject payload = new JSONObject();
		payload.accumulate("device_id", "1g8p5865c");
		payload.accumulate("operation", "sell");
		payload.accumulate("order", 123456);
		//商品详情
		JSONObject orderGoods = new JSONObject();
		orderGoods.accumulate("chNo", "a1");
		orderGoods.accumulate("count", 1);
		//商品参数详情
		JSONObject params = new JSONObject();
		params.accumulate("selfCup", 1);
		
		orderGoods.accumulate("params", params);
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("id", "1g8p5865c");
		dataMap.put("payload", payload);
		try {
			String retMsg = HttpClientUtil.httpPostRequest(SysPara.midPublishUrl,dataMap);
			if(StringUtils.isNotBlank(retMsg)){
				JSONObject retJson = JSONObject.fromObject(retMsg);	
				String retCode = retJson.getString("errCode");
				if(retCode.equals("0")){
					String device_id = retJson.getString("device_id");
					String operation = retJson.getString("operation");
					String order = retJson.getString("order");
					String msg = retJson.getString("msg");
					System.out.println("售卖失败:" + order + msg);
					/*if(vendMachine!=null){
						vendMachine.setUseStatus("2");
						vendMachineService.editVendMachine(vendMachine);
					}*/
				}else{
					System.out.println("售卖失败:" + retJson.getString("msg"));
				}				
			}else{
				System.out.println("售卖失败");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:../machines"; 
	}
	
	
	
	/**
	 * 开机
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/open")
	public String openMachine(@PathVariable int id){
		VendMachine vendMachine=vendMachineService.getOne(id);
		if(vendMachine!=null){
			vendMachine.setUseStatus("1");
			vendMachineService.editVendMachine(vendMachine);
		}
		return "redirect:../machines"; 
	}
	/**
	 * 机器回调地址
	 * @param map
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/callback")
	@ResponseBody
	public String callBack(@RequestParam String id,@RequestParam String payload) throws IOException{
		System.out.println(payload);
		logger.info("-------回调结果:" + payload);
		System.out.println("-------回调结果----------------:" + payload);
		return payload.toString();
	}
	/**
	 * 详情
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/detail")
	public String detail(Model model,@PathVariable int id){
		List<VendShopQrcode> clientQrcodes=vendShopQrcodeService.selectByType("2");
		model.addAttribute("clientQrcodes", clientQrcodes);
		VendMachine vendMachine=vendMachineService.getOne(id);
		model.addAttribute("vendMachine", vendMachine);
		return "manage/machine/machine_detail"; 
	}
	/**
	 * 跳转机器信息添加界面
	 * @param model
	 * @return
	 */
	@RequiresPermissions({"machine:add"})
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String machined(Model model){
		List<CodeLibrary> upvideotypes=codeLibraryService.selectByCodeNo("UPVIDEOTYPE");
		model.addAttribute("upvideotypes", upvideotypes);
		model.addAttribute(new VendMachine());
		return "manage/machine/machine_add";
	}
   /**
    * 添加机器信息
    * @param request
    * @param model
    * @param vendMachine
    * @param br
    * @return
    */
	@RequiresPermissions({"machine:add"})
    @RequestMapping(value="/add",method=RequestMethod.POST)
	public String machined(HttpServletRequest request,Model model,@Validated VendMachine vendMachine,BindingResult br){
		List<CodeLibrary> upvideotypes=codeLibraryService.selectByCodeNo("UPVIDEOTYPE");
		model.addAttribute("upvideotypes", upvideotypes);
    	if(br.hasErrors()){
    		return "manage/machine/machine_add";
    	}
    	vendMachineService.insertVendMachine(vendMachine);
    	return "redirect:machines";
	}
    /**
	 * 跳转机器修改界面
	 * @param model
	 * @return
	 */
	@RequiresPermissions({"machine:edit"})
	@RequestMapping(value="/{id}/edit",method=RequestMethod.GET)
	public String edit(Model model,@PathVariable int id){
		List<VendAd> ads=vendAdService.findAll();
		model.addAttribute("ads", ads);
		List<VendShopQrcode> vendShopQrcodes=vendShopQrcodeService.selectByType("1");
		model.addAttribute("vendShopQrcodes", vendShopQrcodes);
		VendMachine vendMachine=vendMachineService.getOne(id);
		model.addAttribute(vendMachine);
		return "manage/machine/machine_edit";
	}
	/**
	 * 修改机器信息
	 * @param request
	 * @param model
	 * @param vendMachine
	 * @param br
	 * @return
	 */
	@RequiresPermissions({"machine:edit"})
    @RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(HttpServletRequest request,Model model,@Validated VendMachine vendMachine,BindingResult br){
    	if(br.hasErrors()){
    		return "manage/machine/machine_edit";
    	}
    	int isOk=vendMachineService.editVendMachine(vendMachine);
		return "redirect:machines";
	}
    /**
     * 删除机器信息
     * @param user
     * @param br
     * @return
     */
	@RequiresPermissions({"machine:del"})
    @RequestMapping(value="/{id}/del")
 	public String del(@PathVariable Integer id){
    	vendMachineService.delVendMachine(id);;
 		return "redirect:/machine/machines";
 	}
    /**
     * 批量删除机器信息
     * @param ids
     * @return
     */
	@RequiresPermissions({"machine:dels"})
    @RequestMapping(value="/dels")
  	public String dels(HttpServletRequest request){
    	String ids=request.getParameter("ids");
    	String idArray[]=ids.split(",");
    	int[] idArray1=new int[idArray.length];
    	for(int i=0;i<idArray.length;i++){
    		idArray1[i]=Function.getInt(idArray[i], 0);
    	}
    	int isOk=vendMachineService.delVendMachines(idArray1);
  		return "redirect:/machine/machines";
  	}
}
