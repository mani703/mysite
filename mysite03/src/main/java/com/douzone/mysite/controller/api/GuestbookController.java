package com.douzone.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@RestController("guestbookControllerApi")
@RequestMapping("/guestbook/api")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@GetMapping("/{no}")
	public JsonResult read(@PathVariable Long no) {
		List<GuestbookVo> list = guestbookService.getMessageList(no);
		return JsonResult.success(list);
	}
	
	@PostMapping("")
	public JsonResult create(@RequestBody GuestbookVo vo) {
		guestbookService.addMessage(vo);
		return JsonResult.success(vo);
	}
	
	@DeleteMapping("/{no}")
	public JsonResult delete(
			@PathVariable Long no,
			@RequestParam(value="password", required=true, defaultValue="") String password) {
		
		Long data = guestbookService.deleteMessage(no, password) ? no : -1L;
		return JsonResult.success(data);
	}
}
