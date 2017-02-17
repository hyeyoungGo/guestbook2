package com.bit2017.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bit2017.guestbook.repository.GuestbookDao;
import com.bit2017.guestbook.vo.GuestbookVo;

@Controller
public class GuestbookController {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	@RequestMapping( "/list" )
	public String list( Model model ) {
		List<GuestbookVo> list = 
				guestbookDao.getList();
		
		model.addAttribute( "list", list );
		return "/WEB-INF/views/index.jsp";
	}
	
	@RequestMapping( "/deleteform/{no}" )
	public String deleteform( @PathVariable("no") Long no, Model model ){
		model.addAttribute( "no", no );
		return "/WEB-INF/views/deleteform.jsp";
	}
	
	@RequestMapping( "/delete" )
	public String delete(@ModelAttribute GuestbookVo guestbookVo){
		guestbookDao.delete(guestbookVo);
		return "redirect:/list";
	}
	
	@RequestMapping( "/add" )
	public String add(@ModelAttribute GuestbookVo guestbookVo){
		guestbookDao.insert(guestbookVo);
		return "redirect:/list";
	}	
}