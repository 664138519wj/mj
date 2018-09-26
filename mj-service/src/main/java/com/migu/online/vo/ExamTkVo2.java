package com.migu.online.vo;

import java.util.List;

/**
 * 考试题库
 * @author fanyunlong
 *
 */
public class ExamTkVo2 extends ExamTkVo{
	
	private static final long serialVersionUID = 7013877876373050465L;

	private List<ExamTkAnsVo> answerList;

	public List<ExamTkAnsVo> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<ExamTkAnsVo> answerList) {
		this.answerList = answerList;
	}
	
}
