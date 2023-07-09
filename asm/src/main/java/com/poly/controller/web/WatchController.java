package com.poly.controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.constatnt.Constant;
import com.poly.entity.User;
import com.poly.entity.Video;
import com.poly.service.UserService;
import com.poly.service.VideoService;
import com.poly.utils.XForm;
import com.poly.utils.XScope;

@WebServlet("/watch")
public class WatchController extends HttpServlet {
	private static final long serialVersionUID = 9130122760621113791L;

	private VideoService videoService = new VideoService();
	private UserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String videoId = XForm.getString("id",null);
		if(videoId !=null) {
			Video video = videoService.findById(videoId);
			video.setViews(video.getViews() + 1);
			videoService.update(video);
			List<Video> videos = videoService.randoms(10);
			int favoriteCount = videoService.countFavorites(videoId);
			User us = XScope.getSession(Constant.USER);
			if(us != null) {
				boolean isLikedVideo = userService.isLikedVideo(us.getId(),videoId);
				req.setAttribute("isLikedVideo", isLikedVideo);
			}
			req.setAttribute("video", video);
			req.setAttribute("videos", videos);
			req.setAttribute("videoId", videoId);
			req.setAttribute("favoriteCount", favoriteCount);
			req.getRequestDispatcher("/views/web/watch.jsp").forward(req, resp);
		}
	}
	
	
}
