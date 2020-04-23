package com.exam.ddukp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.exam.action.Action;
import com.exam.action.ColumnCommentAction;
import com.exam.action.ColumnCommentDeleteAction;
import com.exam.action.ColumnCommentListAction;
import com.exam.action.ColumnDeleteAction;
import com.exam.action.ColumnListAction;
import com.exam.action.ColumnModifyAction;
import com.exam.action.ColumnModifyOkAction;
import com.exam.action.ColumnViewAction;
import com.exam.action.ColumnWriteOkAction;
import com.exam.action.CustomerCenterCommentAction;
import com.exam.action.CustomerCenterCommentDeleteAction;
import com.exam.action.CustomerCenterCommentListAction;
import com.exam.action.CustomerCenterDeleteAction;
import com.exam.action.CustomerCenterModifyAction;
import com.exam.action.CustomerCenterModifyOkAction;
import com.exam.action.CustomerCenterViewAction;
import com.exam.action.CustomerCenterWriteOkAction;
import com.exam.action.CustomerListAction;
import com.exam.action.FreeCommentAction;
import com.exam.action.FreeCommentDeleteAction;
import com.exam.action.FreeCommentListAction;
import com.exam.action.FreeDeleteAction;
import com.exam.action.FreeListAction;
import com.exam.action.FreeModifyAction;
import com.exam.action.FreeModifyOkAction;
import com.exam.action.FreeViewAction;
import com.exam.action.FreeWriteOkAction;
import com.exam.action.InfoCommentAction;
import com.exam.action.InfoCommentDeleteAction;
import com.exam.action.InfoCommentListAction;
import com.exam.action.InfoDeleteAction;
import com.exam.action.InfoListAction;
import com.exam.action.InfoModifyAction;
import com.exam.action.InfoModifyOkAction;
import com.exam.action.InfoViewAction;
import com.exam.action.InfoWriteOkAction;
import com.exam.action.LatterCommentAction;
import com.exam.action.LatterCommentDeleteAction;
import com.exam.action.LatterCommentListAction;
import com.exam.action.LatterDeleteAction;
import com.exam.action.MovieListAction;
import com.exam.action.UploadAction;
import com.exam.model.FreeCommentTO;
import com.exam.model.CommentDAO;
import com.google.gson.JsonObject;
import com.exam.action.LatterListAction;
import com.exam.action.LatterModifyAction;
import com.exam.action.LatterModifyOkAction;
import com.exam.action.LatterUploadAction;
import com.exam.action.LatterViewAction;
import com.exam.action.LatterWriteOkAction;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CommentController {

	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	Action movieAction = null;

	@RequestMapping(value = "/freeCommentOk.do",produces ="application/text; charset=utf8")
	public @ResponseBody String freeCommentOk(HttpServletRequest request, HttpServletResponse response) {
		movieAction=new FreeCommentAction();
		movieAction.execute(request, response);
		if(request.getAttribute("flag").equals(0)) {
			return "success";
		}
		else return "false";
		
	}
	@RequestMapping(value = "/freeCommentList.do",produces ="application/text; charset=utf8")
	@ResponseBody
	public ResponseEntity  write(HttpServletRequest request, HttpServletResponse response) {
		movieAction=new FreeCommentListAction();
		movieAction.execute(request, response);
		return new ResponseEntity(request.getAttribute("freecommentlist"), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/freeCommentDelete.do",produces ="application/text; charset=utf8")
	public @ResponseBody String freeCommentDelete(HttpServletRequest request, HttpServletResponse response) {
		movieAction=new FreeCommentDeleteAction();
		movieAction.execute(request, response);
		if(request.getAttribute("flag").equals(0))  return "success";
		else return "false";
	}
	
	@RequestMapping(value = "/columnCommentOk.do",produces ="application/text; charset=utf8")
	public @ResponseBody String columnCommentOk(HttpServletRequest request, HttpServletResponse response) {
		movieAction=new ColumnCommentAction();
		movieAction.execute(request, response);
		if(request.getAttribute("flag").equals(0)) {
			return "success";
		}
		else return "false";
		
	}
	@RequestMapping(value = "/columnCommentList.do",produces ="application/text; charset=utf8")
	@ResponseBody
	public ResponseEntity  columnwrite(HttpServletRequest request, HttpServletResponse response) {
		movieAction=new ColumnCommentListAction();
		movieAction.execute(request, response);
		return new ResponseEntity(request.getAttribute("columncommentlist"), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/columnCommentDelete.do",produces ="application/text; charset=utf8")
	public @ResponseBody String columnCommentDelete(HttpServletRequest request, HttpServletResponse response) {
		movieAction=new ColumnCommentDeleteAction();
		movieAction.execute(request, response);
		if(request.getAttribute("flag").equals(0))  return "success";
		else return "false";
	}
	
	@RequestMapping(value = "/latterCommentOk.do",produces ="application/text; charset=utf8")
	public @ResponseBody String latterCommentOk(HttpServletRequest request, HttpServletResponse response) {
		movieAction=new LatterCommentAction();
		movieAction.execute(request, response);
		if(request.getAttribute("flag").equals(0)) {
			return "success";
		}
		else return "false";
		
	}
	@RequestMapping(value = "/latterCommentList.do",produces ="application/text; charset=utf8")
	@ResponseBody
	public ResponseEntity  latterwrite(HttpServletRequest request, HttpServletResponse response) {
		movieAction=new LatterCommentListAction();
		movieAction.execute(request, response);
		return new ResponseEntity(request.getAttribute("lattercommentlist"), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/latterCommentDelete.do",produces ="application/text; charset=utf8")
	public @ResponseBody String latterCommentDelete(HttpServletRequest request, HttpServletResponse response) {
		movieAction=new LatterCommentDeleteAction();
		movieAction.execute(request, response);
		if(request.getAttribute("flag").equals(0))  return "success";
		else return "false";
	}
	
	@RequestMapping(value = "/infoCommentOk.do",produces ="application/text; charset=utf8")
	public @ResponseBody String infoCommentOk(HttpServletRequest request, HttpServletResponse response) {
		movieAction=new InfoCommentAction();
		movieAction.execute(request, response);
		if(request.getAttribute("flag").equals(0)) {
			return "success";
		}
		else return "false";
		
	}
	@RequestMapping(value = "/infoCommentList.do",produces ="application/text; charset=utf8")
	@ResponseBody
	public ResponseEntity  infowrite(HttpServletRequest request, HttpServletResponse response) {
		movieAction=new InfoCommentListAction();
		movieAction.execute(request, response);
		return new ResponseEntity(request.getAttribute("infocommentlist"), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/infoCommentDelete.do",produces ="application/text; charset=utf8")
	public @ResponseBody String infoCommentDelete(HttpServletRequest request, HttpServletResponse response) {
		movieAction=new InfoCommentDeleteAction();
		movieAction.execute(request, response);
		if(request.getAttribute("flag").equals(0))  return "success";
		else return "false";
	}
	
	@RequestMapping(value = "/customerCommentOk.do",produces ="application/text; charset=utf8")
	public @ResponseBody String customerCommentOk(HttpServletRequest request, HttpServletResponse response) {
		movieAction=new CustomerCenterCommentAction();
		movieAction.execute(request, response);
		if(request.getAttribute("flag").equals(0)) {
			return "success";
		}
		else return "false";
		
	}
	@RequestMapping(value = "/customerCommentList.do",produces ="application/text; charset=utf8")
	@ResponseBody
	public ResponseEntity  customerwrite(HttpServletRequest request, HttpServletResponse response) {
		movieAction=new CustomerCenterCommentListAction();
		movieAction.execute(request, response);
		return new ResponseEntity(request.getAttribute("customercommentlist"), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/customerCommentDelete.do",produces ="application/text; charset=utf8")
	public @ResponseBody String customerCommentDelete(HttpServletRequest request, HttpServletResponse response) {
		movieAction=new CustomerCenterCommentDeleteAction();
		movieAction.execute(request, response);
		if(request.getAttribute("flag").equals(0))  return "success";
		else return "false";
	}
}


