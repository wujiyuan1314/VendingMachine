package vend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import base.util.Page;
import vend.entity.VendSyslog;
import vend.service.VendSyslogService;

@Controller
@RequestMapping("/syslog")
public class VendSyslogController {
	public static Logger logger = Logger.getLogger(VendSyslogController.class);
	
	@Autowired
	VendSyslogService vendSyslogService;

	@RequiresPermissions({"login:log"})
	@RequestMapping(value="/syslogs")
	public String listVendSyslog(Model model, @ModelAttribute VendSyslog vendSyslog, @ModelAttribute Page page, HttpServletRequest request) {
		
		String currentPageStr = request.getParameter("currentPage");
		logger.info(currentPageStr + "===========");
		if(currentPageStr != null){
			int currentPage = Integer.parseInt(currentPageStr);
			page.setCurrentPage(currentPage);
		}
		logger.info(page.toString());
		logger.info(vendSyslog.toString());
		List<VendSyslog> vendSyslogs = vendSyslogService.listVendSyslog(vendSyslog, page);
		model.addAttribute("vendSyslogs",vendSyslogs);
		return "manage/syslog/syslog_list";
	}
}
