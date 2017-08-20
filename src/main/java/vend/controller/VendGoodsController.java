package vend.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import net.sf.json.JSONArray;
import vend.entity.VendGoods;
import vend.service.VendGoodsService;

@Controller
@RequestMapping("/goods")
public class VendGoodsController{
	public static Logger logger = Logger.getLogger(VendGoodsController.class);
	
	@Autowired
	VendGoodsService vendGoodsService;
	/**
	 * 根据输入信息条件查询商品列表，并分页显示
	 * @param vendGoods
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/goodss")
	public String listVendGoods(Model model,@ModelAttribute VendGoods vendGoods, @ModelAttribute Page page,HttpServletRequest request) {
		String currentPageStr = request.getParameter("currentPage");
		logger.info(currentPageStr + "===========");
		if(currentPageStr != null){
			int currentPage = Integer.parseInt(currentPageStr);
			page.setCurrentPage(currentPage);
		}
		logger.info(page.toString());
		logger.info(vendGoods.toString());
		List<VendGoods> vendGoodss = vendGoodsService.listVendGoods(vendGoods, page);
		model.addAttribute("vendGoodss",vendGoodss);
		return "manage/goods/goods_list";
	}
	@RequestMapping(value="/jgoodss",method=RequestMethod.GET)
	public void getJson(HttpServletResponse response) throws IOException {
		List<VendGoods> vendGoodss = vendGoodsService.findAll();
		JSONArray json = JSONArray.fromObject(vendGoodss);
		PrintWriter out=response.getWriter();
		out.print(json);
	}
}
