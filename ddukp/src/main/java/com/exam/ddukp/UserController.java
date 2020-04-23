package com.exam.ddukp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.exam.action.Action;
import com.exam.action.CheckIdAction;
import com.exam.action.FindIdAction;
import com.exam.action.FindPwdAction;
import com.exam.action.LoginAction;
import com.exam.action.LoginOkAction;
import com.exam.action.MemberDeleteOkAction;
import com.exam.action.MemberWriteAction;
import com.exam.action.MemberWriteDeleteOkAction;
import com.exam.action.MemberWriteViewAction;
import com.exam.action.SendMailAction;
import com.exam.action.UserChangePwdAction;
import com.exam.action.UserDeleteAction;
import com.exam.action.UserDeleteOkAction;
import com.exam.action.UserEditAction;
import com.exam.action.UserRegisterAction;
import com.exam.action.UserViewAction;
import com.exam.action.UsersDeleteOkAction;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	Action userAction = null;
	@RequestMapping(value = "/login.do")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		userAction = new LoginAction();
		System.out.println("login");
		modelAndView.setViewName("./login/login");
		return modelAndView;
	}

	@RequestMapping(value = "/login_ok.do")
	public ModelAndView login_ok(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		
		userAction = new LoginOkAction();
		userAction.execute(request, response);
		int flag = (Integer) request.getAttribute("flag");

		HttpSession session = request.getSession();

		if (request.getAttribute("flag").equals(0)) {
			session.setAttribute("sid", request.getParameter("id"));
			modelAndView.setViewName("main");

		} else {
			
			modelAndView.setViewName("./login/login_ok");
		}

		modelAndView.setViewName("./login/login_ok");
		return modelAndView;
	}

	@RequestMapping(value = "/logout_ok.do")
	public ModelAndView logout_ok(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		System.out.println("로그아웃");
		session.invalidate();
		modelAndView.setViewName("main");
		return modelAndView;
	}
	
	@RequestMapping(value = "/register.do")
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("./login/register");
	
		return modelAndView;
	}

	@RequestMapping(value = "/register_ok.do")
	public ModelAndView register_ok(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("register_ok");
		
		userAction = new UserRegisterAction();
		userAction.execute(request, response);	
		
		modelAndView.setViewName("./login/register_ok");
		return modelAndView;
	}
	
	@RequestMapping(value = "/checkId.do")
	public @ResponseBody int checkID(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("checkId");

		userAction = new CheckIdAction();
		userAction.execute(request, response);
		int flag = (Integer)request.getAttribute("flag");
		
		return flag;
	}
	
	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping(value = "/sendMail.do")
	public @ResponseBody String sendMail(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("sendMail");

		request.setAttribute("mailSender", mailSender);
		userAction = new SendMailAction();
		userAction.execute(request, response);
		String code = request.getAttribute("code").toString();
		
		return code;
	}

	@RequestMapping(value = "/findPwd.do")
	public ModelAndView findPwd(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("./login/findPwd");
		return modelAndView;
	}

	@RequestMapping(value = "/findPwd_ok.do")
	public ModelAndView findPwd_ok(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("./login/findPwd");

		request.setAttribute("mailSender", mailSender);
		userAction = new FindPwdAction();
		userAction.execute(request, response);
		
		modelAndView.setViewName("./login/findPwd_ok");
		return modelAndView;
	}
	
	@RequestMapping(value = "/findId.do")
	public ModelAndView findId(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("./login/findId");
		return modelAndView;
	}
	
	@RequestMapping(value = "/findId_ok.do")
	public ModelAndView findId_ok(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("./login/findId");

		request.setAttribute("mailSender", mailSender);
		userAction = new FindIdAction();
		userAction.execute(request, response);
		
		modelAndView.setViewName("./login/findId_ok");
		return modelAndView;
	}

	@RequestMapping(value = "/memberInfo.do")
	public ModelAndView memberInfo(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		userAction = new UserViewAction();
		userAction.execute(request, response);
		modelAndView.setViewName("./mypage/memberInfo");
		return modelAndView;
	}	
	
	@RequestMapping(value = "/memberEdit.do")
	public ModelAndView memberEdit(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		userAction = new UserViewAction();
		userAction.execute(request, response);
		modelAndView.setViewName("./mypage/memberEdit");
		return modelAndView;
	}

	@RequestMapping(value = "/memberEdit_ok.do")
	public ModelAndView memberEdit_ok(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		userAction = new UserEditAction();
		userAction.execute(request, response);
		userAction = new UserViewAction();
		userAction.execute(request, response);
		modelAndView.setViewName("./mypage/memberEdit_ok");
		return modelAndView;
	}

	@RequestMapping(value = "/memberDelete.do")
	public ModelAndView memberDelete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		userAction = new UserViewAction();
		userAction.execute(request, response);
		modelAndView.setViewName("./mypage/memberDelete");
		return modelAndView;
	}

	@RequestMapping(value = "/memberDelete_ok.do")
	public ModelAndView memberDelete_ok(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();

		userAction = new MemberDeleteOkAction();
		userAction.execute(request, response);
		session.invalidate();

		modelAndView.setViewName("./mypage/memberDeleteOk");
		return modelAndView;
	}

	@RequestMapping(value = "/memberWrite.do")
	public ModelAndView memberWrite(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		userAction = new MemberWriteAction();
		userAction.execute(request, response);
		modelAndView.setViewName("./mypage/memberWrite");
		return modelAndView;
	}
	@RequestMapping(value = "/memberWriteView.do")
	public ModelAndView memberWriteView(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		userAction = new MemberWriteViewAction();
		userAction.execute(request, response);
		if(request.getParameter("board").equals("review") == true) { modelAndView.setViewName("./latterBoard/latterView");
		} else if(request.getParameter("board").equals("free") == true) { modelAndView.setViewName("./freeBoard/freeView");
		} else if(request.getParameter("board").equals("column") == true) { modelAndView.setViewName("./columnBoard/columnView");
		} else if(request.getParameter("board").equals("qna") == true) { modelAndView.setViewName("./qna/customerCenterView");
		} 
		return modelAndView;
	}
	@RequestMapping(value = "/memberWriteDelete_ok.do")
	public ModelAndView memberWriteDelete_ok(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		userAction = new MemberWriteDeleteOkAction();
		userAction.execute(request, response); 
		modelAndView.setViewName("./mypage/memberWriteDelete_ok");
		return modelAndView;
	}	

	@RequestMapping(value = "/memberScrap.do")
	public ModelAndView memberScrap(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("./mypage/memberScrap");
		return modelAndView;
	}
	@RequestMapping(value = "/changePwd.do")
	public ModelAndView changePwd(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("./mypage/changePwd");
		return modelAndView;
	}
	@RequestMapping(value = "/changePwd_ok.do")
	public ModelAndView changePwd_ok(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		
		userAction = new UserChangePwdAction();
		userAction.execute(request, response);

		modelAndView.setViewName("./mypage/changePwd_ok");
		return modelAndView;
	}
	@RequestMapping(value = "/userDelete.do")
	public ModelAndView userDelete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		userAction = new UserDeleteAction();
		userAction.execute(request, response);
		modelAndView.setViewName("./admin/userDelete");
		return modelAndView;
	}
	@RequestMapping(value = "/userDelete_ok.do")
	public ModelAndView userDelete_ok(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		userAction = new UserDeleteOkAction();
		userAction.execute(request, response);
		modelAndView.setViewName("./admin/userDelete_ok");
		return modelAndView;
	}
	@RequestMapping(value = "/usersDelete_ok.do")
	public ModelAndView usersDelete_ok(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		userAction = new UsersDeleteOkAction();
		userAction.execute(request, response); 
		modelAndView.setViewName("./admin/usersDelete_ok");
		return modelAndView;
	}
	@RequestMapping(value = "/movieUpdate.do")
	public ModelAndView movieUpdate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("./admin/movieUpdate");
		return modelAndView;
	}
}


