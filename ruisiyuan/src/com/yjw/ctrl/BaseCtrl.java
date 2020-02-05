package com.yjw.ctrl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("loginAccount")
public class BaseCtrl<T> {
	// Ctrl 的 LOG 日志
	protected Log log = LogFactory.getLog(BaseCtrl.class);
}
