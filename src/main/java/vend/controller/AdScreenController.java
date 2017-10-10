package vend.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import base.util.DateUtil;
import vend.entity.VendAd;
import vend.entity.VendMachine;
import vend.entity.VendShopQrcode;
import vend.service.VendAdService;
import vend.service.VendMachineService;
import vend.service.VendParaService;
import vend.service.VendShopQrcodeService;

@Controller
@RequestMapping("/adscreen")
public class AdScreenController{
	public static Logger logger = Logger.getLogger(AdScreenController.class);
	@Autowired
	VendParaService vendParaService;
	@Autowired
	VendMachineService vendMachineService;
	@Autowired
	VendAdService vendAdService;
	@Autowired
	VendShopQrcodeService vendShopQrcodeService;
	/**
	 * 广告屏样式1
	 * @param machineId
	 * @return
	 */
	@RequestMapping(value="/{machineId}/screen1")
	public String adScreen1(@PathVariable String machineId,Model model){
		VendMachine vendMachine=vendMachineService.selectByMachineId(machineId);
		VendAd vendAd=vendAdService.selectByMachineId(machineId);
		VendShopQrcode vendShopQrcode=vendShopQrcodeService.getOne(vendMachine.getShopQrcode());
		Date currentDate=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		if(DateUtil.daysBetween(currentDate, vendAd.getStartTime())>0||DateUtil.daysBetween(currentDate, vendAd.getEndTime())<0){
			vendAd=new VendAd();
			vendShopQrcode=new VendShopQrcode();
		}
		if(vendAd.getExtend3().equals("0")){
			vendAd=new VendAd();
			vendShopQrcode=new VendShopQrcode();
		}
		model.addAttribute("vendMachine",vendMachine);
		model.addAttribute("vendAd",vendAd);
		model.addAttribute("vendShopQrcode",vendShopQrcode);
		return "front/adscreen/ad_screen1";
	}
	/**
	 * 广告屏样式2
	 * @return
	 */
	@RequestMapping(value="/{machineId}/screen2")
	public String adScreen2(@PathVariable String machineId,Model model){
		VendMachine vendMachine=vendMachineService.selectByMachineId(machineId);
		VendAd vendAd=vendAdService.selectByMachineId(machineId);
		VendShopQrcode vendShopQrcode=vendShopQrcodeService.getOne(vendMachine.getShopQrcode());
		Date currentDate=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		if(DateUtil.daysBetween(currentDate, vendAd.getStartTime())>0||DateUtil.daysBetween(currentDate, vendAd.getEndTime())<0){
			vendAd=new VendAd();
			vendShopQrcode=new VendShopQrcode();
		}
		if(vendAd.getExtend3().equals("0")){
			vendAd=new VendAd();
			vendShopQrcode=new VendShopQrcode();
		}
		model.addAttribute("vendMachine",vendMachine);
		model.addAttribute("vendAd",vendAd);
		model.addAttribute("vendShopQrcode",vendShopQrcode);
		return "front/adscreen/ad_screen2";
	}
	/**
	 * 广告屏样式3
	 * @return
	 */
	@RequestMapping(value="/{machineId}/screen3")
	public String adScreen3(@PathVariable String machineId,Model model){
		VendMachine vendMachine=vendMachineService.selectByMachineId(machineId);
		VendAd vendAd=vendAdService.selectByMachineId(machineId);
		VendShopQrcode vendShopQrcode=vendShopQrcodeService.getOne(vendMachine.getShopQrcode());
		Date currentDate=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		if(DateUtil.daysBetween(currentDate, vendAd.getStartTime())>0||DateUtil.daysBetween(currentDate, vendAd.getEndTime())<0){
			vendAd=new VendAd();
			vendShopQrcode=new VendShopQrcode();
		}
		if(vendAd.getExtend3().equals("0")){
			vendAd=new VendAd();
			vendShopQrcode=new VendShopQrcode();
		}
		model.addAttribute("vendMachine",vendMachine);
		model.addAttribute("vendAd",vendAd);
		model.addAttribute("vendShopQrcode",vendShopQrcode);
		return "front/adscreen/ad_screen3";
	}
	/**
	 * 广告屏样式4
	 * @return
	 */
	@RequestMapping(value="/{machineId}/screen4")
	public String adScreen4(@PathVariable String machineId,Model model){
		VendMachine vendMachine=vendMachineService.selectByMachineId(machineId);
		VendAd vendAd=vendAdService.selectByMachineId(machineId);
		VendShopQrcode vendShopQrcode=vendShopQrcodeService.getOne(vendMachine.getShopQrcode());
		Date currentDate=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		if(DateUtil.daysBetween(currentDate, vendAd.getStartTime())>0||DateUtil.daysBetween(currentDate, vendAd.getEndTime())<0){
			vendAd=new VendAd();
			vendShopQrcode=new VendShopQrcode();
		}
		if(vendAd.getExtend3().equals("0")){
			vendAd=new VendAd();
			vendShopQrcode=new VendShopQrcode();
		}
		model.addAttribute("vendMachine",vendMachine);
		model.addAttribute("vendAd",vendAd);
		model.addAttribute("vendShopQrcode",vendShopQrcode);
		return "front/adscreen/ad_screen4";
	}
	/**
	 * 广告屏样式5
	 * @return
	 */
	@RequestMapping(value="/{machineId}/screen5")
	public String adScreen5(@PathVariable String machineId,Model model){
		VendMachine vendMachine=vendMachineService.selectByMachineId(machineId);
		VendAd vendAd=vendAdService.selectByMachineId(machineId);
		VendShopQrcode vendShopQrcode=vendShopQrcodeService.getOne(vendMachine.getShopQrcode());
		Date currentDate=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		if(DateUtil.daysBetween(currentDate, vendAd.getStartTime())>0||DateUtil.daysBetween(currentDate, vendAd.getEndTime())<0){
			vendAd=new VendAd();
			vendShopQrcode=new VendShopQrcode();
		}
		if(vendAd.getExtend3().equals("0")){
			vendAd=new VendAd();
			vendShopQrcode=new VendShopQrcode();
		}
		model.addAttribute("vendMachine",vendMachine);
		model.addAttribute("vendAd",vendAd);
		model.addAttribute("vendShopQrcode",vendShopQrcode);
		return "front/adscreen/ad_screen5";
	}
	/**
	 * 广告屏样式6
	 * @return
	 */
	@RequestMapping(value="/{machineId}/screen6")
	public String adScreen6(@PathVariable String machineId,Model model){
		VendMachine vendMachine=vendMachineService.selectByMachineId(machineId);
		VendAd vendAd=vendAdService.selectByMachineId(machineId);
		VendShopQrcode vendShopQrcode=vendShopQrcodeService.getOne(vendMachine.getShopQrcode());
		Date currentDate=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		if(DateUtil.daysBetween(currentDate, vendAd.getStartTime())>0||DateUtil.daysBetween(currentDate, vendAd.getEndTime())<0){
			vendAd=new VendAd();
			vendShopQrcode=new VendShopQrcode();
		}
		if(vendAd.getExtend3().equals("0")){
			vendAd=new VendAd();
			vendShopQrcode=new VendShopQrcode();
		}
		model.addAttribute("vendMachine",vendMachine);
		model.addAttribute("vendAd",vendAd);
		model.addAttribute("vendShopQrcode",vendShopQrcode);
		return "front/adscreen/ad_screen6";
	}
	/**
	 * 广告屏样式7
	 * @return
	 */
	@RequestMapping(value="/{machineId}/screen7")
	public String adScreen7(@PathVariable String machineId,Model model){
		VendMachine vendMachine=vendMachineService.selectByMachineId(machineId);
		VendAd vendAd=vendAdService.selectByMachineId(machineId);
		VendShopQrcode vendShopQrcode=vendShopQrcodeService.getOne(vendMachine.getShopQrcode());
		Date currentDate=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		if(DateUtil.daysBetween(currentDate, vendAd.getStartTime())>0||DateUtil.daysBetween(currentDate, vendAd.getEndTime())<0){
			vendAd=new VendAd();
			vendShopQrcode=new VendShopQrcode();
		}
		if(vendAd.getExtend3().equals("0")){
			vendAd=new VendAd();
			vendShopQrcode=new VendShopQrcode();
		}
		model.addAttribute("vendMachine",vendMachine);
		model.addAttribute("vendAd",vendAd);
		model.addAttribute("vendShopQrcode",vendShopQrcode);
		return "front/adscreen/ad_screen7";
	}
	/**
	 * 广告屏样式8
	 * @return
	 */
	@RequestMapping(value="/{machineId}/screen8")
	public String adScreen8(@PathVariable String machineId,Model model){
		VendMachine vendMachine=vendMachineService.selectByMachineId(machineId);
		VendAd vendAd=vendAdService.selectByMachineId(machineId);
		VendShopQrcode vendShopQrcode=vendShopQrcodeService.getOne(vendMachine.getShopQrcode());
		Date currentDate=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		if(DateUtil.daysBetween(currentDate, vendAd.getStartTime())>0||DateUtil.daysBetween(currentDate, vendAd.getEndTime())<0){
			vendAd=new VendAd();
			vendShopQrcode=new VendShopQrcode();
		}
		if(vendAd.getExtend3().equals("0")){
			vendAd=new VendAd();
			vendShopQrcode=new VendShopQrcode();
		}
		model.addAttribute("vendMachine",vendMachine);
		model.addAttribute("vendAd",vendAd);
		model.addAttribute("vendShopQrcode",vendShopQrcode);
		return "front/adscreen/ad_screen8";
	}
	/**
	 * 广告屏样式9
	 * @return
	 */
	@RequestMapping(value="/{machineId}/screen9")
	public String adScreen9(@PathVariable String machineId,Model model){
		VendMachine vendMachine=vendMachineService.selectByMachineId(machineId);
		VendAd vendAd=vendAdService.selectByMachineId(machineId);
		VendShopQrcode vendShopQrcode=vendShopQrcodeService.getOne(vendMachine.getShopQrcode());
		Date currentDate=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		if(DateUtil.daysBetween(currentDate, vendAd.getStartTime())>0||DateUtil.daysBetween(currentDate, vendAd.getEndTime())<0){
			vendAd=new VendAd();
			vendShopQrcode=new VendShopQrcode();
		}
		if(vendAd.getExtend3().equals("0")){
			vendAd=new VendAd();
			vendShopQrcode=new VendShopQrcode();
		}
		model.addAttribute("vendMachine",vendMachine);
		model.addAttribute("vendAd",vendAd);
		model.addAttribute("vendShopQrcode",vendShopQrcode);
		return "front/adscreen/ad_screen9";
	}
	/**
	 * 广告屏样式10
	 * @return
	 */
	@RequestMapping(value="/{machineId}/screen10")
	public String adScreen10(@PathVariable String machineId,Model model){
		VendMachine vendMachine=vendMachineService.selectByMachineId(machineId);
		VendAd vendAd=vendAdService.selectByMachineId(machineId);
		VendShopQrcode vendShopQrcode=vendShopQrcodeService.getOne(vendMachine.getShopQrcode());
		Date currentDate=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		if(DateUtil.daysBetween(currentDate, vendAd.getStartTime())>0||DateUtil.daysBetween(currentDate, vendAd.getEndTime())<0){
			vendAd=new VendAd();
			vendShopQrcode=new VendShopQrcode();
		}
		if(vendAd.getExtend3().equals("0")){
			vendAd=new VendAd();
			vendShopQrcode=new VendShopQrcode();
		}
		model.addAttribute("vendMachine",vendMachine);
		model.addAttribute("vendAd",vendAd);
		model.addAttribute("vendShopQrcode",vendShopQrcode);
		return "front/adscreen/ad_screen10";
	}
}
