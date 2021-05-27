package com.douzone.mysite.mvc.guestbook;

import com.douzone.mvc.Action;
import com.douzone.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("insert".equals(actionName)) {
			System.out.println("insert");
			action = new AddAction();
		} else if("deleteform".equals(actionName)) {
			System.out.println("delteform");
			action = new DeleteFormActionAction();
		} else if("delete".equals(actionName)){
			System.out.println("delete");
			action = new DeleteAction();
		} else{
			action = new IndexAction();
		}
		
		return action;
	}

}
