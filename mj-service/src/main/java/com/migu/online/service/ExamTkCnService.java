package com.migu.online.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.migu.online.common.Constants;
import com.migu.online.mapper.ExamTkCnMapper;
import com.migu.online.mapper.ExamTkHaMapper;
import com.migu.online.mapper.ExamTkMonMapper;
import com.migu.online.mapper.ExamTkUyMapper;
import com.migu.online.mapper.UserExamRecordMapper;
import com.migu.online.model.ExamTk;
import com.migu.online.model.ExamTkCn;
import com.migu.online.model.ExamTkHa;
import com.migu.online.model.ExamTkMon;
import com.migu.online.model.ExamTkUy;
import com.migu.online.model.UserExamRecord;
import com.migu.online.utils.Base64Tools;
import com.migu.online.vo.ExamStatus;
import com.migu.online.vo.ExamTkAnsVo;
import com.migu.online.vo.ExamTkVo;
import com.migu.online.vo.ExamTkVo2;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class ExamTkCnService {

	@Autowired
	private ExamTkCnMapper examTkCnMapper;
	@Autowired
	private ExamTkMonMapper examTkMonMapper;
	@Autowired
	private ExamTkHaMapper examTkHaMapper;
	@Autowired
	private ExamTkUyMapper examTkUyMapper;
	@Autowired
	private UserExamRecordMapper userExamRecordMapper;
	@Autowired
	private UserExamRecordService userExamRecordService;
	@Autowired
	private RedisService redisService;

	    
	/**
	 * 根据条件查询自由题库
	 * @param quType 题目类型 1是非 2 选择题
	 * @param examType 考题类型
	 * @param kemu 科目1 4
	 * @return
	 */
	public List<String> queryFreeExamIds(String quType, String examType, String kemu, Integer licType, Integer language) {
		// 多语言选择
		if (language == null || language == 0) {
			// 默认中文
			return examTkCnMapper.queryFreeExamIds(quType, examType, kemu, licType);
		} else if (language == 1) {
			// 蒙语
			return examTkMonMapper.queryFreeExamIds(quType, examType, kemu, licType);
		} else if (language == 2) {
			// 哈语
			return examTkHaMapper.queryFreeExamIds(quType, examType, kemu, licType);
		} else if (language == 3) {
			// 维语
			return examTkUyMapper.queryFreeExamIds(quType, examType, kemu, licType);
		}	
		return examTkCnMapper.queryFreeExamIds(quType, examType, kemu, licType);
	}
	
	/**
	 * 根据条件查询自由题库
	 * @param quType 题目类型 1是非 2 选择题
	 * @param examType 考题类型
	 * @param kemu 科目1 4
	 * @return
	 */
	public List<ExamTkVo> queryExamVoList(String quType, String examType, String kemu, String licenceType, Integer language) {
		List<ExamTkVo> result = new ArrayList<>();
 		Example example = new Example(ExamTkCn.class);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(quType)) {
			if (quType.equals("1")) {
				criteria.andCondition("sttx = ", quType);
			} else {
				criteria.andNotEqualTo("sttx", "1");
			}
		}		
		if (StringUtils.isNotEmpty(licenceType)) {
			criteria.andLike("sycx", "%" + licenceType +"%");
		}
		criteria.andCondition("stfl = ", examType);
		criteria.andCondition("tklx = ", kemu);
		if (language == null || language == 0) {
			// 默认中文
			List<ExamTkCn> pageList = examTkCnMapper.selectByExample(example);	
			for (ExamTk record: pageList) {
				if (null != record) {
					ExamTkVo vo = new ExamTkVo();
					vo.setExamCode(record.getStxh());
					vo.setQuestion(record.getStnr());
					vo.setAnswer1(record.getXzdaa());
					vo.setAnswer2(record.getXzdab());
					vo.setAnswer3(record.getXzdac());
					vo.setAnswer4(record.getXzdad());
					if (StringUtils.isNotEmpty(record.getTxlj())) {
						vo.setImageUrl("/upload/image/tk/" + record.getStxh() + ".jpeg");
					}
					vo.setCorrAnswer(record.getStda());
					vo.setKemu(Integer.parseInt(record.getTklx()));
					vo.setExamType(record.getStfl());
					vo.setQuesType(Integer.parseInt(record.getSttx()));
					vo.setLanguage(language);
					result.add(vo);
				}
			}
		} else if (language == 1) {
			// 蒙语
			List<ExamTkMon> pageList = examTkMonMapper.selectByExample(example);
			for (ExamTk record: pageList) {
				if (null != record) {
					ExamTkVo vo = new ExamTkVo();
					vo.setExamCode(record.getStxh());
					Base64Tools tools = new Base64Tools();
					vo.setQuestion(tools.decodeStr(record.getStnr()));
					vo.setAnswer1(tools.decodeStr(record.getXzdaa()));
					vo.setAnswer2(tools.decodeStr(record.getXzdab()));
					vo.setAnswer3(tools.decodeStr(record.getXzdac()));
					vo.setAnswer4(tools.decodeStr(record.getXzdad()));
					if (StringUtils.isNotEmpty(record.getTxlj())) {
						vo.setImageUrl("/upload/image/tk/" + record.getStxh() + ".jpeg");
					}
					vo.setCorrAnswer(record.getStda());
					vo.setKemu(Integer.parseInt(record.getTklx()));
					vo.setExamType(record.getStfl());
					vo.setQuesType(Integer.parseInt(record.getSttx()));
					vo.setLanguage(language);
					result.add(vo);
				}
			}
		} else if (language == 2) {
			// 哈语
			List<ExamTkHa> pageList = examTkHaMapper.selectByExample(example);
			for (ExamTk record: pageList) {
				if (null != record) {
					ExamTkVo vo = new ExamTkVo();
					vo.setExamCode(record.getStxh());
					Base64Tools tools = new Base64Tools();
					vo.setQuestion(tools.decodeStr(record.getStnr()));
					vo.setAnswer1(tools.decodeStr(record.getXzdaa()));
					vo.setAnswer2(tools.decodeStr(record.getXzdab()));
					vo.setAnswer3(tools.decodeStr(record.getXzdac()));
					vo.setAnswer4(tools.decodeStr(record.getXzdad()));
					if (StringUtils.isNotEmpty(record.getTxlj())) {
						vo.setImageUrl("/upload/image/tk/" + record.getStxh() + ".jpeg");
					}
					vo.setCorrAnswer(record.getStda());
					vo.setKemu(Integer.parseInt(record.getTklx()));
					vo.setExamType(record.getStfl());
					vo.setQuesType(Integer.parseInt(record.getSttx()));
					vo.setLanguage(language);
					result.add(vo);
				}
			}
		} else if (language == 3) {
			// 维语
			List<ExamTkUy> pageList = examTkUyMapper.selectByExample(example);
			for (ExamTk record: pageList) {
				if (null != record) {
					ExamTkVo vo = new ExamTkVo();
					vo.setExamCode(record.getStxh());
					Base64Tools tools = new Base64Tools();
					vo.setQuestion(tools.decodeStr(record.getStnr()));
					vo.setAnswer1(tools.decodeStr(record.getXzdaa()));
					vo.setAnswer2(tools.decodeStr(record.getXzdab()));
					vo.setAnswer3(tools.decodeStr(record.getXzdac()));
					vo.setAnswer4(tools.decodeStr(record.getXzdad()));
					if (StringUtils.isNotEmpty(record.getTxlj())) {
						vo.setImageUrl("/upload/image/tk/" + record.getStxh() + ".jpeg");
					}
					vo.setCorrAnswer(record.getStda());
					vo.setKemu(Integer.parseInt(record.getTklx()));
					vo.setExamType(record.getStfl());
					vo.setQuesType(Integer.parseInt(record.getSttx()));
					vo.setLanguage(language);
					result.add(vo);
				}
			}
		}	
		
		return result;
	}
	
	/**
	 * 根据条件查询自由题库
	 * 
	 * @param examCode
	 * @return
	 * @throws @throws
	 *             Exception
	 */
	public ExamTkVo queryFreeExamByCode(String examCode, Integer language) {
		ExamTkVo vo = new ExamTkVo();
		ExamTk data = new ExamTk();
		try {
			if (language == null || language == 0) {
				// 默认中文
				ExamTkCn record = new ExamTkCn();
				record.setStxh(examCode);
				record = examTkCnMapper.selectOne(record);
				BeanUtils.copyProperties(data, record);
			} else if (language == 1) {
				// 蒙语
				ExamTkMon record = new ExamTkMon();
				record.setStxh(examCode);
				record = examTkMonMapper.selectOne(record);
				BeanUtils.copyProperties(data, record);
			} else if (language == 2) {
				// 哈语
				ExamTkHa record = new ExamTkHa();
				record.setStxh(examCode);
				record = examTkHaMapper.selectOne(record);
				BeanUtils.copyProperties(data, record);
			} else if (language == 3) {
				// 维语
				ExamTkUy record = new ExamTkUy();
				record.setStxh(examCode);
				record = examTkUyMapper.selectOne(record);
				BeanUtils.copyProperties(data, record);
			}
			if (null != data) {
				vo.setExamCode(examCode);
				vo.setLanguage(language);
				if (language > 0) {
					Base64Tools tools = new Base64Tools();
					vo.setQuestion(tools.decodeStr(data.getStnr()));
					vo.setAnswer1(tools.decodeStr(data.getXzdaa()));
					vo.setAnswer2(tools.decodeStr(data.getXzdab()));
					vo.setAnswer3(tools.decodeStr(data.getXzdac()));
					vo.setAnswer4(tools.decodeStr(data.getXzdad()));
				} else {
					vo.setQuestion(data.getStnr());
					vo.setAnswer1(data.getXzdaa());
					vo.setAnswer2(data.getXzdab());
					vo.setAnswer3(data.getXzdac());
					vo.setAnswer4(data.getXzdad());
				}				
				if (StringUtils.isNotEmpty(data.getTxlj())) {
					vo.setImageUrl("/upload/image/tk/" + examCode + ".jpeg");
				}
				vo.setCorrAnswer(data.getStda());
				vo.setKemu(Integer.parseInt(data.getTklx()));
				vo.setExamType(data.getStfl());
				vo.setQuesType(Integer.parseInt(data.getSttx()));
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}
    
	/**
	 * 获取个人用户 自由答题
	 * @param quType
	 * @param examType
	 * @param kemu
	 * @return
	 */
	public ExamTkVo freeDomQuestions(String quType, String examType, String kemu, Integer licType, Integer language) {
		// 获取个人用户答题
		ExamTkVo vo = null;
		List<String> questList = queryFreeExamIds(quType, examType, kemu, licType, language);
		if (null != questList && questList.size() > 0) {
			Random rand = new Random();
			int ranNum = rand.nextInt(questList.size());
			vo = queryFreeExamByCode(questList.get(ranNum), language);
		}		
		return vo;
	}
	
	/**
	 * 自由答题 记录
	 * @param quType
	 * @param examType
	 * @param kemu
	 * @return
	 */
	public UserExamRecord recordFreeExam(String answerMap, Long userId, String userName, Integer kemu,
			Integer licType, Integer language) {
		// 获取个人用户答题
		ExamTkAnsVo vo = JSON.parseObject(answerMap, ExamTkAnsVo.class);
		return userExamRecordService.createFreeRecord(userId, kemu, licType, userName, vo, language);
	}
	
	/**
	 * 生成模拟考试
	 * 按照规则随机生成一套题
	 * @param quType
	 * @param examType
	 * @param kemu
	 * @return
	 */
	public ExamTkVo examTestGenerate(Long userId, String userName, Integer licenceType, int kemu, Integer language) {
		// 题目矩阵
		Map<Integer, ExamTkVo> examMap = new HashMap<>(); 
		Long examId = 0L;
		if (kemu == 1) {
			// 科目1
			if (licenceType == 1) {
				examId = generateExamAB1(userId, userName, examMap, licenceType, kemu, language);
			} else if (licenceType == 2) {
				examId = generateExamC1(userId, userName, examMap, licenceType, kemu, language);
			} else if (licenceType == 3) {
				examId = generateExamDEF1(userId, userName, examMap, licenceType, kemu, language);
			}
		} else if (kemu == 4) {
			// 科目4
			if (licenceType == 1) {
				examId = generateExamAB4(userId, userName, examMap, licenceType, kemu, language);
			} else if (licenceType == 2) {
				examId = generateExamC4(userId, userName, examMap, licenceType, kemu, language);
			} else if (licenceType == 3) {
				examId = generateExamDEF4(userId, userName, examMap, licenceType, kemu, language);
			}
		}

		ExamTkVo vo = null;
		if (null != examMap && null != examMap.get(1)) {
			vo = examMap.get(1);
			vo.setExamId(examId);
		}
		return vo;
	}
	
	/**
	 * 生成模拟考试
	 * 按照规则随机生成一套题
	 * @param quType
	 * @param examType
	 * @param kemu
	 * @return
	 */
	public List<ExamTkVo> examTestGenerate2(Long userId, String userName, Integer licenceType, int kemu, Integer language) {
		// 题目矩阵
		Map<Integer, ExamTkVo> examMap = new HashMap<>(); 
		Long examId = 0L;
		if (kemu == 1) {
			// 科目1
			if (licenceType == 1) {
				examId = generateExamAB1(userId, userName, examMap, licenceType, kemu, language);
			} else if (licenceType == 2) {
				examId = generateExamC1(userId, userName, examMap, licenceType, kemu, language);
			} else if (licenceType == 3) {
				examId = generateExamDEF1(userId, userName, examMap, licenceType, kemu, language);
			}
		} else if (kemu == 4) {
			// 科目4
			if (licenceType == 1) {
				examId = generateExamAB4(userId, userName, examMap, licenceType, kemu, language);
			} else if (licenceType == 2) {
				examId = generateExamC4(userId, userName, examMap, licenceType, kemu, language);
			} else if (licenceType == 3) {
				examId = generateExamDEF4(userId, userName, examMap, licenceType, kemu, language);
			}
		}
		List<ExamTkVo> result = new ArrayList<>();
		for (Map.Entry<Integer, ExamTkVo> entry : examMap.entrySet()) {
			ExamTkVo vo = entry.getValue();
			vo.setIndex(entry.getKey());
			vo.setExamId(examId);
			result.add(vo);
		}		
		return result;
	}
		
	/**
	 * 模拟考试答题&获取题目 
	 * @param quType
	 * @param examType
	 * @param kemu
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ExamTkVo2 ansAndGetTest(int index, int next, UserExamRecord userExam, Long userId, String answer, Integer isCorrect, Integer language) {
		// 记录答题
		List<ExamTkAnsVo> list = JSONArray.parseArray(userExam.getExamMap(), ExamTkAnsVo.class);
		if (!StringUtils.isEmpty(answer) && isCorrect != null) {
			ExamTkAnsVo vo = list.get(index - 1);
			vo.setIsCorrect(isCorrect);
			vo.setAnswer(answer);
			vo.setLanguage(language);
			userExam.setExamMap(JSON.toJSONString(list));
			userExam.setUpdateTime(new Date());
			// 得分计算
			int score = 0;
			for (ExamTkAnsVo data: list) {
				if (data.getIsCorrect() == 1) {
					score++;
				}
			}
			userExam.setScore(score);		
			userExamRecordMapper.updateByPrimaryKeySelective(userExam);
		}	
		// 题目矩阵
		int curIndex = index + next;
		Map<Integer, ExamTkVo> examMap = (Map<Integer, ExamTkVo>)redisService.get(Constants.USER_EXAM_CONTENT + userId); 
		ExamTkVo tkvo = new ExamTkVo();
		if (null == examMap) {
			// 缓存不存在
			 tkvo = queryFreeExamByCode(list.get(curIndex - 1).getExamCode(), language);			
		} else {
			tkvo = examMap.get(curIndex);
		}	
		tkvo.setExamId(userExam.getId());
		tkvo.setIndex(index + next);
		ExamTkVo2 vo2 = new ExamTkVo2();
		try {
			BeanUtils.copyProperties(vo2, tkvo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo2.setAnswerList(list);
		return vo2;
	}
	
	/**
	 * 模拟提交
	 * @param quType
	 * @param examType
	 * @param kemu
	 * @return
	 */
	public boolean submitTest(int index, UserExamRecord userExam, Long userId, String answer, Integer isCorrect) {
		// 记录答题
		if (StringUtils.isNotEmpty(answer)) {
			List<ExamTkAnsVo> list = JSONArray.parseArray(userExam.getExamMap(), ExamTkAnsVo.class);
			ExamTkAnsVo vo = list.get(index - 1);
			vo.setIsCorrect(isCorrect);
			vo.setAnswer(answer);
			userExam.setExamMap(JSON.toJSONString(list));
			if (null != isCorrect && isCorrect == 1) {
				userExam.setScore(userExam.getScore() + 1);
			}
		}		
		userExam.setUpdateTime(new Date());
		userExam.setStatus(1);		
		userExamRecordMapper.updateByPrimaryKeySelective(userExam);	
		redisService.delObjct(Constants.USER_EXAM_TIME + userId);
		return true;
	}
	
	/**
	 * app模拟提交
	 * @param quType
	 * @param examType
	 * @param kemu
	 * @return
	 */
	public boolean appSubmitTest(UserExamRecord userExam, String answer, int score) {
		// 记录答题
		userExam.setExamMap(answer);
		userExam.setUpdateTime(new Date());
		userExam.setStatus(1);
		userExam.setScore(score);
		
		userExamRecordMapper.updateByPrimaryKeySelective(userExam);	
		return true;
	}
	
	/**
	 * 校验上次考试是否完成
	 * @param userId
	 * @return
	 */
	public boolean checkTestFinish(Long userId) {
		String key = Constants.USER_EXAM_TIME;
		Long examId = (Long)redisService.get(key + userId);	
		if (null != examId) {
			return false;
		}
		return true;
	}
	
	/**
	 * 获取未完成考试信息
	 * @param userId
	 * @return
	 */
	public ExamStatus getUnFinishExam(Long userId) {
		String key = Constants.USER_EXAM_TIME;
		ExamStatus status = new ExamStatus();
		Long examId = (Long)redisService.get(key + userId);	
		if (null != examId) {
			status.setUnFinish(true);
			// 计算
			UserExamRecord record = userExamRecordService.selectById(examId);
			List<ExamTkAnsVo> list = JSONArray.parseArray(record.getExamMap(), ExamTkAnsVo.class);
			int lastIndex = 1;
			for (ExamTkAnsVo vo : list) {
				if (vo.getAnswer() == null) {
					lastIndex = vo.getIndex();
					break;
				}
			}
			status.setLastIndex(lastIndex);
			status.setSeconds(redisService.getExpire(key + userId));
			status.setKemu(record.getKemu());
			status.setLicType(record.getLicType());
			status.setExamId(examId);
		}
		return status;
	}
	
	/**
	 * 获取当前考试id
	 * @param userId
	 * @return
	 */
	public Long getExamIdByUserId(Long userId) {
		String key = Constants.USER_EXAM_TIME;
		return (Long)redisService.get(key + userId);
	}
	
	/**
	 * 放弃本地考试
	 * @param userId
	 * @return
	 */
	public boolean abandonTest(Long userId) {
		String key = Constants.USER_EXAM_TIME;
		Long value = (Long)redisService.get(key + userId);	
		if (null != value) {
			UserExamRecord record = new UserExamRecord();
			record.setId(value);
			record.setStatus(2);
			record.setUpdateTime(new Date());
			userExamRecordMapper.updateByPrimaryKeySelective(record);
			redisService.delObjct(key + userId);
		}
		return true;
	}
	
	/**
	 * AB类驾照生成题目 
	 * 科目1
	 * @return
	 */
	private Long generateExamAB1(Long userId, String userName, Map<Integer, ExamTkVo> examMap, Integer licenceType, int kemu, Integer language) {
		// 答题情况矩阵
		List<ExamTkAnsVo> examAnsList = new ArrayList<>();
		// A01 随机15题
		randomExam(queryExamVoList("", "A01", "1", "A", language), 17, examMap, 1, examAnsList, language);
		// A02 随机10题
		randomExam(queryExamVoList("", "A02", "1", "A", language), 12, examMap, 18, examAnsList, language);
		// A03 随机30题
		randomExam(queryExamVoList("", "A03", "1", "A", language), 32, examMap, 30, examAnsList, language);
		// A04 随机10题
		randomExam(queryExamVoList("", "A04", "1", "A", language), 12, examMap, 62, examAnsList, language);
		// A05 随机10题
		randomExam(queryExamVoList("", "A05", "1", "A", language), 11, examMap, 74, examAnsList, language);
		// A07 随机15题
		randomExam(queryExamVoList("", "A07", "1", "A", language), 16, examMap, 85, examAnsList, language);
	
		return saveUserRecord(examAnsList, userId, userName, examMap, 100, licenceType, kemu, language);
	}
	
	/**
	 * C类驾照生成题目 
	 * 科目1
	 * @return
	 */
	private Long generateExamC1(Long userId, String userName, Map<Integer, ExamTkVo> examMap, Integer licenceType, int kemu, Integer language) {
		// 答题情况矩阵
		List<ExamTkAnsVo> examAnsList = new ArrayList<>();
		// A01 随机15题
		randomExam(queryExamVoList("", "A01", "1", "C", language), 23, examMap, 1, examAnsList, language);
		// A02 随机10题
		randomExam(queryExamVoList("", "A02", "1", "C", language), 27, examMap, 24, examAnsList, language);
		// A03 随机30题
		randomExam(queryExamVoList("", "A03", "1", "C", language), 27, examMap, 51, examAnsList, language);
		// A04 随机10题
		randomExam(queryExamVoList("", "A04", "1", "C", language), 12, examMap, 78, examAnsList, language);
		// A05 随机10题
		randomExam(queryExamVoList("", "A05", "1", "C", language), 11, examMap, 90, examAnsList, language);

		return saveUserRecord(examAnsList, userId, userName, examMap, 100, licenceType, kemu, language);
	}
	
	/**
	 * DEF类驾照生成题目 
	 * 科目1
	 * @return
	 */
	private Long generateExamDEF1(Long userId, String userName, Map<Integer, ExamTkVo> examMap, Integer licenceType, int kemu, Integer language) {
		// 答题情况矩阵
		List<ExamTkAnsVo> examAnsList = new ArrayList<>();
		// A01 随机15题
		randomExam(queryExamVoList("", "A01", "1", "DEF", language), 11, examMap, 1, examAnsList, language);
		// A02 随机10题
		randomExam(queryExamVoList("", "A02", "1", "DEF", language), 19, examMap, 12, examAnsList, language);
		// A03 随机30题
		randomExam(queryExamVoList("", "A03", "1", "DEF", language), 16, examMap, 31, examAnsList, language);
		// A04 随机10题
		randomExam(queryExamVoList("", "A04", "1", "DEF", language), 4, examMap, 47, examAnsList, language);
		// 记录用户题库	
		return saveUserRecord(examAnsList, userId, userName, examMap, 50, licenceType, kemu, language);
	}
	
	/**
	 * AB类驾照生成题目 
	 * 科目4
	 * @return
	 */
	private Long generateExamAB4(Long userId, String userName, Map<Integer, ExamTkVo> examMap, Integer licenceType, int kemu, Integer language) {
		// 答题情况矩阵
		List<ExamTkAnsVo> examAnsList = new ArrayList<>();
		// B01 随机15题
		randomExam(queryExamVoList("", "B01", "4", "A", language), 11, examMap, 1, examAnsList, language);
		// B02 随机10题
		randomExam(queryExamVoList("", "B02", "4", "A", language), 10, examMap, 12, examAnsList, language);
		// B03 随机30题
		randomExam(queryExamVoList("", "B03", "4", "A", language), 6, examMap, 22, examAnsList, language);
		// B04 随机10题
		randomExam(queryExamVoList("", "B04", "4", "A", language), 9, examMap, 28, examAnsList, language);
		// B05 随机10题
		randomExam(queryExamVoList("", "B05", "4", "A", language), 8, examMap, 37, examAnsList, language);
		// B06 随机10题
		randomExam(queryExamVoList("", "B06", "4", "A", language), 2, examMap, 45, examAnsList, language);
		// B07 随机15题
		randomExam(queryExamVoList("", "B07", "4", "A", language), 4, examMap, 47, examAnsList, language);
		
		return saveUserRecord(examAnsList, userId, userName, examMap, 50, licenceType, kemu, language);
	}
	
	/**
	 * C类驾照生成题目 
	 * 科目4
	 * @return
	 */
	private Long generateExamC4(Long userId, String userName, Map<Integer, ExamTkVo> examMap, Integer licenceType, int kemu, Integer language) {
		// 答题情况矩阵
		List<ExamTkAnsVo> examAnsList = new ArrayList<>();
		// B01 随机15题
		randomExam(queryExamVoList("", "B01", "4", "C", language), 11, examMap, 1, examAnsList, language);
		// B02 随机10题
		randomExam(queryExamVoList("", "B02", "4", "C", language), 10, examMap, 12, examAnsList, language);
		// B03 随机30题
		randomExam(queryExamVoList("", "B03", "4", "C", language), 6, examMap, 22, examAnsList, language);
		// B04 随机10题
		randomExam(queryExamVoList("", "B04", "4", "C", language), 9, examMap, 28, examAnsList, language);
		// B05 随机10题
		randomExam(queryExamVoList("", "B05", "4", "C", language), 8, examMap, 37, examAnsList, language);
		// B06 随机10题
		randomExam(queryExamVoList("", "B06", "4", "C", language), 2, examMap, 45, examAnsList, language);
		// B07 随机15题
		randomExam(queryExamVoList("", "B07", "4", "C", language), 4, examMap, 47, examAnsList, language);
		
		return saveUserRecord(examAnsList, userId, userName, examMap, 50, licenceType, kemu, language);
	}
	
	
	/**
	 * DEF类驾照生成题目 
	 * 科目4
	 * @return
	 */
	private Long generateExamDEF4(Long userId, String userName, Map<Integer, ExamTkVo> examMap, Integer licenceType, int kemu, Integer language) {
		// 答题情况矩阵
		List<ExamTkAnsVo> examAnsList = new ArrayList<>();
		// B01 随机15题
		randomExam(queryExamVoList("", "B01", "4", "DEF", language), 14, examMap, 1, examAnsList, language);
		// B02 随机10题
		randomExam(queryExamVoList("", "B02", "4", "DEF", language), 9, examMap, 15, examAnsList, language);
		// B03 随机30题
		randomExam(queryExamVoList("", "B03", "4", "DEF", language), 3, examMap, 24, examAnsList, language);
		// B04 随机10题
		randomExam(queryExamVoList("", "B04", "4", "DEF", language), 10, examMap, 27, examAnsList, language);
		// B05 随机10题
		randomExam(queryExamVoList("", "B05", "4", "DEF", language), 8, examMap, 37, examAnsList, language);
		// B06 随机10题
		randomExam(queryExamVoList("", "B06", "4", "DEF", language), 2, examMap, 45, examAnsList, language);
		// B07 随机15题
		randomExam(queryExamVoList("", "B07", "4", "DEF", language), 4, examMap, 47, examAnsList, language);

		return saveUserRecord(examAnsList, userId, userName, examMap, 50, licenceType, kemu, language);
	}
	
	/**
	 * 记录用户考试记录
	 * @param examAnsList
	 * @param userId
	 * @param userName
	 * @param examMap
	 * @param count
	 * @return
	 */
	private Long saveUserRecord(List<ExamTkAnsVo> examAnsList, Long userId, String userName, 
			Map<Integer, ExamTkVo> examMap, int count, Integer licenceType, int kemu, Integer language) {
		if (null == examAnsList || examAnsList.size() <= 0) {
			return 0l;
		}
		// 记录用户题库
		UserExamRecord record = new UserExamRecord();
		record.setCount(count);
		record.setExamMap(JSON.toJSONString(examAnsList));
		record.setUserId(userId);
		record.setUserName(userName);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		record.setStatus(0);
		record.setScore(0);
		record.setKemu(kemu);
		record.setType(0);
		record.setLicType(licenceType);
		if (null == language) {
			language = 0;
		}
		record.setLanguage(language);
		userExamRecordMapper.insert(record);
		// 答题时间控制 半小时
		String key = Constants.USER_EXAM_TIME;
		redisService.set(key + userId, record.getId(), 60 * 30);	
		// 保存试题内容到redis
		redisService.set(Constants.USER_EXAM_CONTENT + userId, examMap, 60 * 30);
		return record.getId();
	}
	
	
	/**
	 * 返回随机
	 * @param list
	 * @param randomNum
	 * @return
	 */
	private void randomExam(List<ExamTkVo> list, int randomNum, Map<Integer, ExamTkVo> examMap, int index, List<ExamTkAnsVo> examAnsList, Integer language) {
		if (list == null || list.size() <=0 || list.size() < randomNum) {
			return;
		}
		int[] arr = new int[randomNum];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * list.size());
			for (int j = 0; j < i; j++) {
				if (arr[i] == arr[j]) {
					i--;
					break;
				}
			}
		}
		for (int i = 0; i < arr.length; i ++) {
			ExamTkVo data = list.get(arr[i]);
			if (null != data) {
				examMap.put(index++, data);
				ExamTkAnsVo vo = new ExamTkAnsVo();
				vo.setIndex(index - 1);
				vo.setExamCode(data.getExamCode());
				vo.setIsCorrect(0);
				vo.setLanguage(language);
				examAnsList.add(vo);
			}			
		}
	}
		
}
