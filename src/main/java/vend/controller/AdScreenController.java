package vend.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adscreen")
public class AdScreenController{
	public static Logger logger = Logger.getLogger(AdScreenController.class);
	/**
	 * 广告屏样式1
	 * @return
	 */
	@RequestMapping(value="/screen1")
	public String adScreen1(){
		return "front/adscreen/ad_screen1";
	}
	/**
	 * 广告屏样式2
	 * @return
	 */
	@RequestMapping(value="/screen2")
	public String adScreen2(){
		return "front/adscreen/ad_screen2";
	}
	/**
	 * 广告屏样式3
	 * @return
	 */
	@RequestMapping(value="/screen3")
	public String adScreen3(){
		return "front/adscreen/ad_screen3";
	}
	/**
	 * 广告屏样式4
	 * @return
	 */
	@RequestMapping(value="/screen4")
	public String adScreen4(){
		return "front/adscreen/ad_screen4";
	}
	/**
	 * 广告屏样式5
	 * @return
	 */
	@RequestMapping(value="/screen5")
	public String adScreen5(){
		return "front/adscreen/ad_screen5";
	}
	/**
	 * 广告屏样式6
	 * @return
	 */
	@RequestMapping(value="/screen6")
	public String adScreen6(){
		return "front/adscreen/ad_screen6";
	}
	/**
	 * 广告屏样式7
	 * @return
	 */
	@RequestMapping(value="/screen7")
	public String adScreen7(){
		return "front/adscreen/ad_screen7";
	}
	/**
	 * 广告屏样式8
	 * @return
	 */
	@RequestMapping(value="/screen8")
	public String adScreen8(){
		return "front/adscreen/ad_screen8";
	}
	/**
	 * 广告屏样式9
	 * @return
	 */
	@RequestMapping(value="/screen9")
	public String adScreen9(){
		return "front/adscreen/ad_screen9";
	}
	/**
	 * 广告屏样式10
	 * @return
	 */
	@RequestMapping(value="/screen10")
	public String adScreen10(){
		return "front/adscreen/ad_screen10";
	}
}
