package com.migu.online.service.quartz;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.pagehelper.util.StringUtil;
import com.migu.online.common.Constants;
import com.migu.online.model.SendMsgRecord;
import com.migu.online.service.MessageSendService;
import com.migu.online.service.RedisService;
import com.migu.online.service.SendMsgRecordService;

@Component
public class QuartzService {
	
	private Logger log = LoggerFactory.getLogger(QuartzService.class);
	@Autowired
	private SendMsgRecordService  sendMsgRecordService;
	@Autowired
	private MessageSendService  messageSendService;
	@Autowired
	private RedisService redisService;

	/**
	 * 短信发送定时任务，30s跑一次
	 */
	@Scheduled(cron = "0/30 * * * * ?")
	public void timerToNow() {
		log.info("---msg qutarz begin---");
		System.out.println("---msg qutarz begin---");
		// 排它锁
		String key = Constants.QUARTZ_UNQUE;
		String count = redisService.getString(key);
		if (StringUtil.isNotEmpty(count) && count.equals("1")) {
			return;
		}
		redisService.setString(key, "1", 60);
		// 一次查出100条未发短信
		List<SendMsgRecord> list = sendMsgRecordService.selectUnSendMsgByPage(1, 100);
	    System.out.println("list size : " + list.size());
		for (SendMsgRecord record : list) {
			messageSendService.sendSmsByTempAndUpdRecord(record);
		}
		redisService.del(key);
		log.info("---msg qutarz end---");
		System.out.println("---msg qutarz end---");
	}

}
