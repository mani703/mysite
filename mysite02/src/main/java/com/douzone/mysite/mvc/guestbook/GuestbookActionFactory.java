package com.douzone.mysite.mvc.guestbook;

import com.douzone.mvc.Action;
import com.douzone.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("insert".equals(actionName)) {
			action = new AddAction();
		} else if("deleteform".equals(actionName)) {
			action = new DeleteFormActionAction();
		} else if("delete".equals(actionName)){
			action = new DeleteAction();
		} else{
			action = new IndexAction();
		}
		
		return action;
	}

}
