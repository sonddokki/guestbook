package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping(value = "gbc")
public class GuestController {
	
	// 리스트 출력
		@RequestMapping("list")
		// @RequestMapping(value="/list", method={RequestMethod.GET,RequestMethod.POST})
		// 전부 생략 가능
		public String list(Model model) {
			System.out.println("리스트 출력");
			
		 	GuestDao guestDao = new GuestDao();
			List<GuestVo> guestList = guestDao.guestSelect("");

			// model 주소를 받고 메소드를 이용해서 담는다
			// --> DS request.setAttribute("암호",데이터)
			// request의 attritube에 넣는다
			model.addAttribute("gList",guestList);

			// list.jsp에 포워드 한다
			return "/WEB-INF/addList.jsp";
		}
		
		@RequestMapping(value="/add", method={RequestMethod.GET,RequestMethod.POST})		
		public String add(@ModelAttribute GuestVo guestVo) {
			System.out.println("등록 실행");
			
			GuestDao guestDao = new GuestDao();
			guestDao.guestInsert(guestVo);
			
			return "redirect:list";
		}
				
		@RequestMapping(value="dFrom", method={RequestMethod.GET,RequestMethod.POST})
		public String dFrom() {
			System.out.println("삭체폼 출력");
							
			return "/WEB-INF/dFrom.jsp";
		}
		
		@RequestMapping("/delete")
		public String delete(@RequestParam("no") int no,
							 @RequestParam("pw") String password) {
			System.out.println("삭제 실행");
			
			GuestDao guestDao = new GuestDao();
			guestDao.guestDelete(no, password);
			
			// list.jsp에 리다이렉트 한다
			return "redirect:list";
		}
		

}
