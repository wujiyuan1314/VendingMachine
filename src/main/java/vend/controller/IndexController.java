package vend.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import base.util.CacheUtils;
import base.util.DateUtil;
import base.util.Function;
import vend.entity.Result;
import vend.entity.VendMachine;
import vend.entity.VendOrder;
import vend.entity.VendUser;
import vend.service.VendAccountService;
import vend.service.VendMachineService;
import vend.service.VendOrderService;
import vend.service.VendUserService;

@Controller
public class IndexController {
	@Autowired
	VendUserService vendUserService;
	@Autowired
	VendMachineService vendMachineService;
	@Autowired
	VendAccountService vendAccountService;
	@Autowired
	VendOrderService vendOrderService;
	/**
	 * 清除缓存
	 * @return
	 */
    @RequestMapping("/clearcache")
    @ResponseBody
    public Object clearcache(){
    	CacheUtils.clear();
        Result result = new Result();
        result.setSuccess(true);
        result.setMsg("清理缓存成功!");
        return result;
    }
    /**
     * 每天销售统计
     * @param request
     * @param response
     */
    @RequestMapping(value="/daysales",method=RequestMethod.POST)
    public void daySales(HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession();
	    VendUser vendUser=(VendUser)session.getAttribute("vendUser");
	    String usercodelist="";
	    if(vendUser!=null){
	    	usercodelist=vendUserService.getNextUsers(vendUser.getUsercode());
	    	if(usercodelist==null){
	    		usercodelist="";
	    	}
	    }
	    
	    String usercodeArray[]=Function.stringSpilit(usercodelist, ",");
	    List<VendMachine> vendMachines=vendMachineService.selectByUsercode(usercodeArray);
	    String mochinecodelist="";
	    for(VendMachine vendMachine:vendMachines){
	    	if(vendMachine!=null&&vendMachine.getMachineCode()!=null){
	    		mochinecodelist+=vendMachine.getMachineCode()+",";
	    	}
	    }
	    String mochinecodeArray[]=Function.stringSpilit(mochinecodelist, ",");
	    VendOrder vendOrder=new VendOrder();
	    String beginTime=DateUtil.getCurrentDateStr();
	    String endTime=DateUtil.format(DateUtil.addDays(DateUtil.parseDate(beginTime),1));
	    List<VendOrder> vendOrders=new ArrayList();
	    if(mochinecodeArray.length==0){
	    	vendOrders =vendOrderService.selectByParams1(vendOrder,beginTime, endTime);
	    }else{
	    	vendOrders =vendOrderService.selectByParams(vendOrder,mochinecodeArray,beginTime, endTime);
	    }
	    
	    int user_num=0;//消费用户数
	    int sell_num=0;//销售量
	    double sell_amount=0.0;//销售金额
	    int free_num=0;//免费数量
	    String buyusers="";
	    for(VendOrder vendOrder1:vendOrders){
	    	if(vendOrder1!=null&&vendOrder1.getUsercode()!=null){
	    		if(buyusers.indexOf(vendOrder1.getUsercode()+",")==-1){
	    			buyusers+=vendOrder1.getUsercode()+",";
	    			user_num++;
	    		}
	    	}
	    	if(vendOrder1!=null&&vendOrder1.getNum()!=null){
	    		    sell_num+=vendOrder1.getNum();
	    	}
	    	if(vendOrder1!=null&&vendOrder1.getAmount()!=null){
	    		    sell_amount+=vendOrder1.getAmount().doubleValue();
	    	}
	    	if(vendOrder1!=null&&vendOrder1.getFreeStatus()!=null&&vendOrder1.getFreeStatus().equals("1")){
	    		    free_num++;
    	    }
	    }
	    JSONObject json = new JSONObject();
	    json.put("user_num", user_num);
	    json.put("sell_num", sell_num);
	    json.put("sell_amount", sell_amount);
	    json.put("free_num", free_num);
	    try {
			response.getWriter().append(json.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * 每周销售统计
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/weeksales",method=RequestMethod.POST)
    public void weekSales(HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession();
	    VendUser vendUser=(VendUser)session.getAttribute("vendUser");
	    String usercodelist="";
	    if(vendUser!=null){
	    	usercodelist=vendUserService.getNextUsers(vendUser.getUsercode());
	    	if(usercodelist==null){
	    		usercodelist="";
	    	}
	    }
	    
	    String usercodeArray[]=Function.stringSpilit(usercodelist, ",");
	    List<VendMachine> vendMachines=vendMachineService.selectByUsercode(usercodeArray);
	    String mochinecodelist="";
	    for(VendMachine vendMachine:vendMachines){
	    	if(vendMachine!=null&&vendMachine.getMachineCode()!=null){
	    		mochinecodelist+=vendMachine.getMachineCode()+",";
	    	}
	    }
	    String mochinecodeArray[]=Function.stringSpilit(mochinecodelist, ",");
	    
	    VendOrder vendOrder=new VendOrder();
	    String beginTime=DateUtil.formatTime(DateUtil.getTimesWeekmorning());
	    String endTime=DateUtil.formatTime(DateUtil.getTimesWeeknight());
	    List<VendOrder> vendOrders=new ArrayList();
	    if(mochinecodeArray.length==0){
	    	vendOrders =vendOrderService.selectByParams1(vendOrder,beginTime, endTime);
	    }else{
	    	vendOrders =vendOrderService.selectByParams(vendOrder,mochinecodeArray,beginTime, endTime);
	    }
	    
	    int user_num=0;//消费用户数
	    int sell_num=0;//销售量
	    double sell_amount=0.0;//销售金额
	    int free_num=0;//免费数量
	    String buyusers="";
	    for(VendOrder vendOrder1:vendOrders){
	    	if(vendOrder1!=null&&vendOrder1.getUsercode()!=null){
	    		if(buyusers.indexOf(vendOrder1.getUsercode()+",")==-1){
	    			buyusers+=vendOrder1.getUsercode()+",";
	    			user_num++;
	    		}
	    	}
	    	if(vendOrder1!=null&&vendOrder1.getNum()!=null){
	    		    sell_num+=vendOrder1.getNum();
	    	}
	    	if(vendOrder1!=null&&vendOrder1.getAmount()!=null){
	    		    sell_amount+=vendOrder1.getAmount().doubleValue();
	    	}
	    	if(vendOrder1!=null&&vendOrder1.getFreeStatus()!=null&&vendOrder1.getFreeStatus().equals("1")){
	    		    free_num++;
    	    }
	    }
	    JSONObject json = new JSONObject();
	    json.put("user_num", user_num);
	    json.put("sell_num", sell_num);
	    json.put("sell_amount", sell_amount);
	    json.put("free_num", free_num);
	    try {
			response.getWriter().append(json.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * 按时间查询销售统计
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/bydatesales",method=RequestMethod.POST)
    public void byDateSales(HttpServletRequest request,HttpServletResponse response){
		HttpSession session=request.getSession();
	    VendUser vendUser=(VendUser)session.getAttribute("vendUser");
	    String usercodelist="";
	    if(vendUser!=null){
	    	usercodelist=vendUserService.getNextUsers(vendUser.getUsercode());
	    	if(usercodelist==null){
	    		usercodelist="";
	    	}
	    }
	    
	    String usercodeArray[]=Function.stringSpilit(usercodelist, ",");
	    List<VendMachine> vendMachines=vendMachineService.selectByUsercode(usercodeArray);
	    String mochinecodelist="";
	    for(VendMachine vendMachine:vendMachines){
	    	if(vendMachine!=null&&vendMachine.getMachineCode()!=null){
	    		mochinecodelist+=vendMachine.getMachineCode()+",";
	    	}
	    }
	    String mochinecodeArray[]=Function.stringSpilit(mochinecodelist, ",");
	    
	    VendOrder vendOrder=new VendOrder();
	    String beginTime=request.getParameter("beginTime");
	    String endTime=request.getParameter("endTime");
	    List<VendOrder> vendOrders=new ArrayList();
	    if(mochinecodeArray.length==0){
	    	vendOrders =vendOrderService.selectByParams1(vendOrder,beginTime, endTime);
	    }else{
	    	vendOrders =vendOrderService.selectByParams(vendOrder,mochinecodeArray,beginTime, endTime);
	    }
	    
	    int user_num=0;//消费用户数
	    int sell_num=0;//销售量
	    double sell_amount=0.0;//销售金额
	    int free_num=0;//免费数量
	    String buyusers="";
	    for(VendOrder vendOrder1:vendOrders){
	    	if(vendOrder1!=null&&vendOrder1.getUsercode()!=null){
	    		if(buyusers.indexOf(vendOrder1.getUsercode()+",")==-1){
	    			buyusers+=vendOrder1.getUsercode()+",";
	    			user_num++;
	    		}
	    	}
	    	if(vendOrder1!=null&&vendOrder1.getNum()!=null){
	    		    sell_num+=vendOrder1.getNum();
	    	}
	    	if(vendOrder1!=null&&vendOrder1.getAmount()!=null){
	    		    sell_amount+=vendOrder1.getAmount().doubleValue();
	    	}
	    	if(vendOrder1!=null&&vendOrder1.getFreeStatus()!=null&&vendOrder1.getFreeStatus().equals("1")){
	    		    free_num++;
    	    }
	    }
	    JSONObject json = new JSONObject();
	    json.put("user_num", user_num);
	    json.put("sell_num", sell_num);
	    json.put("sell_amount", sell_amount);
	    json.put("free_num", free_num);
	    try {
			response.getWriter().append(json.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
