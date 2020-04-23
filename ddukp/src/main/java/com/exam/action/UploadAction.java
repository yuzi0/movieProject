package com.exam.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface UploadAction {
	public abstract String execute(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest multiFile) throws Exception;
}
