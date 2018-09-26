package com.migu.online.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.migu.online.mapper.ExamTkTkzpMapper;
import com.migu.online.model.ExamTkTkzp;
import com.migu.online.utils.FileUtils;

import tk.mybatis.mapper.entity.Example;

@Service
public class ExamTkTkzpService {

	@Autowired
	private ExamTkTkzpMapper examTkTkzpMapper;
	
	
	public List<ExamTkTkzp> selectImageByPage(Integer pageIndex,Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize,false);
		//单表自定义查询
        Example example = new Example(ExamTkTkzp.class);
        example.createCriteria().andCondition("zplx = 1");
		
        return examTkTkzpMapper.selectByExample(example);		
	} 
	
	/**
	 * 手动生成图片
	 * @throws Exception
	 */
	public void generateTkImage() throws Exception {
//		int totalCount = 2382;
		for (int i = 1; i <= 239; i++) {
			System.out.println("生成图片分页开始" + i);
			Thread.sleep(100);
			List<ExamTkTkzp> list = selectImageByPage(i,10);
			for (ExamTkTkzp zp: list) {
				if (null == zp) {
					continue;
				}
				FileUtils.decoderBase64File(zp.getKstzp(), "/var/log/migu/" + zp.getStxh() + ".jpeg" );
			}	
			System.out.println("生成图片分页结束" + i + "，完成图片：" + list.size());
		}
	}
				
}
