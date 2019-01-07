package com.pg.controller;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pg.VO.MembersVO;
import com.pg.dao.MembersDao;

@Controller
public class PGController {
	
	@Autowired
	MembersDao dao;
	
	@RequestMapping({ "/index.do" })
	public String home(){
		return ".main";
	}
	
	@RequestMapping(value="/join.do",method=RequestMethod.GET)
	public String joinForm(){
		return ".join";
	}

	@RequestMapping(value="/join.do",method=RequestMethod.POST)
	public String join(HttpServletRequest request,HttpServletResponse response, Model model){
		String id = request.getParameter("id");
		String mName = request.getParameter("mName");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String birth = request.getParameter("birth");
		String pCode = request.getParameter("sample4_postcode");
		String pNAddress = request.getParameter("sample4_roadAddress");
		String pOAddress = request.getParameter("sample4_jibunAddress");
		String pDAddress = request.getParameter("sample4_detailAddress");
		
		System.out.println(id);
		System.out.println(mName);
				
		MembersVO vo = new MembersVO(id,mName,email,pwd,phone,birth,pCode,pNAddress,pOAddress,pDAddress);
		int n = dao.join(vo);
		if( n>=0){
			model.addAttribute("msg","succ");
		}else{
			model.addAttribute("msg","fail");
		}
		return ".login";
	}
	
	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public String loginForm(){
		return ".login";
	}
	
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		System.out.println(id);
		
		MembersVO vo = dao.getMember(id);
		
		System.out.println("꺼낸 아이디 : " + vo.getId());
		
		if(id.equals(vo.getId())){
			session.setAttribute("id", id);
			return ".main";
		}else{
			session.setAttribute("error", "로그인에 실패하였습니다");
			response.sendRedirect(request.getContextPath()+"/login.do");
			return "null";
		}
		
	}
	
	@RequestMapping(value="/logout.do",method=RequestMethod.GET)
	public void logout(String url,HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		if(session != null){
			session.invalidate();
		}
		
		response.sendRedirect(request.getContextPath()+"/index.do");
		
	}
}
