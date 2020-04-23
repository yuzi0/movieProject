package com.exam.ddukp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.exam.action.Action;
import com.exam.action.MainAction;
import com.exam.action.MovieSortListAction;
import com.exam.action.MovieSortViewAction;
import com.exam.action.MovieSortWriteAction;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MovieController {

	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	Action movieAction = null;

	@RequestMapping(value = "/main.do")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		movieAction = new MainAction();
		movieAction.execute(request, response);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("main");
		return modelAndView;
	}

	@RequestMapping(value = "/introduceMoviep.do")
	public ModelAndView introduceMoviep(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("introduceMoviep");
		return modelAndView;
	}

	@RequestMapping(value = "/movieSortWrite.do")
	public @ResponseBody String movieSortWrite(HttpServletRequest request, HttpServletResponse response) {
		movieAction = new MovieSortWriteAction();
		movieAction.execute(request, response);
		if (request.getAttribute("flag").equals(0)) {
			return "success";
		} else
			return "false";
	}

	@RequestMapping(value = "/movieSortList.do")
	public ModelAndView movieSortList(HttpServletRequest request, HttpServletResponse response) {
		movieAction = new MovieSortListAction();
		movieAction.execute(request, response);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("./movieSortBoard/movieSortList");
		return modelAndView;
	}

	@RequestMapping(value = "/movieSortView.do")
	public ModelAndView movieSortView(HttpServletRequest request, HttpServletResponse response) {
		movieAction = new MovieSortViewAction();
		movieAction.execute(request, response);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("./movieSortBoard/movieSortView");
		return modelAndView;
	}

	@RequestMapping(value = "/movieEnglishList.do")
	public ModelAndView movieEnglishList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("./movieEnglishBoard/movieEnglishList");
		return modelAndView;
	}

	@RequestMapping(value = "/movieEnglishView.do")
	public ModelAndView movieEnglishView(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("./movieEnglishBoard/movieEnglishView");
		return modelAndView;
	}

}
