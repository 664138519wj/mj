package com.migu.online.controller.web;

import static java.nio.file.StandardOpenOption.READ;

import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.migu.online.controller.BaseController;
import com.migu.online.service.CourseOnlineService;
import com.migu.online.service.UserCourseVipService;
import com.migu.online.vo.UserCourseOnlineVo;

@Controller
@RequestMapping("/api/video")
public class VideoController extends BaseController{

	private static final long serialVersionUID = 3454595288080797913L;
		
	private static final int BUFFER_LENGTH = 1024 * 16;
	private static final long EXPIRE_TIME = 1000 * 60 * 60 * 24;
	private static final Pattern RANGE_PATTERN = Pattern.compile("bytes=(?<start>\\d*)-(?<end>\\d*)");
	@Value("${video.base.path}")
	private String VIDEO_BASE_PATH;
	
	@Autowired
	private CourseOnlineService courseOnlineService;
	@Autowired
	private UserCourseVipService userCourseVipService;

	/**
	 * 视频播放流控制器
	 * @param request
	 * @param response
	 * @param videoPath
	 * @throws Exception 
	 */
	@RequestMapping(value = "/stream", method = RequestMethod.GET)
	public void processRequest(final HttpServletRequest request, final HttpServletResponse response, 
			@RequestParam("id") Long id) throws Exception {
        if (id <= 0) {
        	return;
        }
        Long userId = super.getCurrentUserId();
        // 获取在线课程	
        UserCourseOnlineVo vo = courseOnlineService.selectUserOne(id, userId);	
        String videoPath = vo.getVideoPath();
        // vip用户，可直接观看
 		if (!userCourseVipService.isVipUser(super.getCurrentUserId())) {
 			if (null == vo || vo.getIsPay() == 0) {
 	        	// 付费视频，该用户未付费 只能提供试看视频
 	        	videoPath = vo.getSmallVideoPath();
 	        	if (StringUtils.isEmpty(videoPath)) {
 	        		videoPath = vo.getVideoPath();
 	        	}
 	        }
 		}

		String videoFilename = videoPath.substring(videoPath.lastIndexOf("/") + 1, videoPath.length());

		Path video = Paths.get(VIDEO_BASE_PATH + videoPath.substring(0, videoPath.lastIndexOf("/") + 1), videoFilename);
		if (null == video) {
			return;
		}
		int length = (int) Files.size(video);
		int start = 0;
		int end = length - 1;

		String range = request.getHeader("Range");
		range = range == null ? "" : range;
		Matcher matcher = RANGE_PATTERN.matcher(range);

		if (matcher.matches()) {
			String startGroup = matcher.group("start");
			start = startGroup.isEmpty() ? start : Integer.valueOf(startGroup);
			start = start < 0 ? 0 : start;

			String endGroup = matcher.group("end");
			end = endGroup.isEmpty() ? end : Integer.valueOf(endGroup);
			end = end > length - 1 ? length - 1 : end;
		}

		int contentLength = end - start + 1;

		response.reset();
		response.setBufferSize(BUFFER_LENGTH);
		response.setHeader("Content-Disposition", String.format("inline;filename=\"%s\"", videoFilename));
		response.setHeader("Accept-Ranges", "bytes");
		response.setDateHeader("Last-Modified", Files.getLastModifiedTime(video).toMillis());
		response.setDateHeader("Expires", System.currentTimeMillis() + EXPIRE_TIME);
		response.setContentType(Files.probeContentType(video));
		response.setHeader("Content-Range", String.format("bytes %s-%s/%s", start, end, length));
		response.setHeader("Content-Length", String.format("%s", contentLength));
		response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);

		int bytesRead;
		int bytesLeft = contentLength;
		ByteBuffer buffer = ByteBuffer.allocate(BUFFER_LENGTH);

		try (SeekableByteChannel input = Files.newByteChannel(video, READ);
				OutputStream output = response.getOutputStream()) {
			input.position(start);
			while ((bytesRead = input.read(buffer)) != -1 && bytesLeft > 0) {
				buffer.clear();
				output.write(buffer.array(), 0, bytesLeft < bytesRead ? bytesLeft : bytesRead);
				bytesLeft -= bytesRead;
			}

		}
	}
	

}
