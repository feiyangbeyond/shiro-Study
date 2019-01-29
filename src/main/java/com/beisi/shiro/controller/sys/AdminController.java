package com.beisi.shiro.controller.sys;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beisi.shiro.model.sys.Role;
import com.beisi.shiro.model.sys.User;
import com.beisi.shiro.service.sys.RoleService;
import com.beisi.shiro.service.sys.UserService;
import com.github.pagehelper.PageInfo;




@Controller
public class AdminController {
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/admin/main.html",method=RequestMethod.GET)
	public String admin(Model model) {
//		Session session = SecurityUtils.getSubject().getSession();
//		System.out.println(session);
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		model.addAttribute("username",user.getUsername());
		model.addAttribute("nowDate",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return "admin/admin";
	}
	
	//用户管理
	@RequestMapping(value="/admin/userManager.html",method=RequestMethod.GET)
	public String userManager(Model model,Integer pageNum,Integer pageSize) {
		//获取到所有的角色的记录信息，注入到user.jsp视图页面
		List<Role> roles = this.roleService.selectAll();
		model.addAttribute("allRoles", roles);		
//		//获取所有用户信息
//		List<User> users = this.userService.selectRelevanceUser();//关联查询（用户和角色信息）
//		model.addAttribute("allUsers",users);
		//分页查询
		if(pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if(pageSize == null || pageSize == 0) {
			pageSize = 10;
		}
		PageInfo<User> users = this.userService.selectUsersByPage(pageNum, pageSize);
		model.addAttribute("userDatasByPager",users);
		return "admin/user";
	}
	
	//角色管理
	@RequestMapping(value="/admin/roleManager.html",method=RequestMethod.GET)
	public String roleManager() {
		return "admin/role";
	}
	
	//资源管理
	@RequestMapping(value="/admin/resManager.html",method=RequestMethod.GET)
	public String resManager() {
		return "admin/resources";
	}
}
