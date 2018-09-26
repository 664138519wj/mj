package com.migu.online.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.migu.online.mapper.UserCourseVipMapper;
import com.migu.online.model.UserCourseVip;
import com.migu.online.model.VipCourse;
import com.migu.online.ops.vo.UserCourseVipOpsVo;
import com.migu.online.ops.vo.UserCourseVipVo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class UserCourseVipService {

    @Autowired
    private UserCourseVipMapper userCourseVipMapper;
    @Autowired
    private VipCourseService vipCourseService;
 
 
    /**
     * 分页查询
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    public Page<UserCourseVip> selectByPage(String mobile, Integer assignStatus, Integer payStatus, Integer kemu, 
    		Integer pageIndex,Integer pageSize) throws Exception{
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize,false);
        //单表自定义查询
        Example example = new Example(UserCourseVip.class);
        if (StringUtils.isNotEmpty(mobile)) {
        	Criteria criteria = example.createCriteria();
        	criteria.andCondition("mobile=", mobile);
        }
        Page<UserCourseVip> pageList = (Page<UserCourseVip>)userCourseVipMapper.selectByExample(example);
        return pageList;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int deleteById(Long id) {
    	UserCourseVip record = userCourseVipMapper.selectByPrimaryKey(id);
    	if (null != record) {
        	return userCourseVipMapper.updateByPrimaryKey(record);
    	}
    	return 0;
    }
    
    /**
     * 查
     * @param id
     * @return
     */
    public UserCourseVip selectById(Long id) {
    	return userCourseVipMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 改&插
     * @param model
     * @return
     */
    public int createOrUpdate(UserCourseVip model) {
    	if (null != model.getId() && model.getId() > 0) {
    		// update
    		return userCourseVipMapper.updateByPrimaryKey(model);
    	} else {   		
    		return userCourseVipMapper.insert(model);
    	}
    }
    
    /**
     * 后台查询
     *
     * @param pageIndex
     * @param pageSize
     * @param platId
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws Exception
     */

    public Page<UserCourseVipOpsVo> selectConditions(Map<String, String> filters) {
        boolean byPage = true;
        int pageIndex = 0;
        int pageSize = 0;
        //单表自定义查询
        Example example = new Example(UserCourseVip.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("is_delete = 0");
        for (String key : filters.keySet()) {
            switch (key) {
                case "mobile":
                    criteria.andLike("mobile", "%" + filters.get(key) + "%");
                    break;
                case "assignStatus":
                	int assignStatus = Integer.parseInt(filters.get(key));
                    criteria.andEqualTo("assignStatus", assignStatus);
                    break;
                case "payStatus":
                    int payStatus = Integer.parseInt(filters.get(key));
                    criteria.andEqualTo("payStatus", payStatus);
                    break;
                case "kemu":
                    criteria.andLike("kemus", "%" + filters.get(key) + "%");
                    break;
                case "page_index":
                    pageIndex = Integer.parseInt(filters.get(key));
                    break;
                case "page_size":
                    pageSize = Integer.parseInt(filters.get(key));
                    break;
                case "teacherId":
                	Integer teacherId = Integer.parseInt(filters.get(key));
                	criteria.andCondition("(kemu_teacher_id1 = " + teacherId + " or kemu_teacher_id2 =" 
                	+ teacherId + " or kemu_teacher_id3 =" + teacherId + " or kemu_teacher_id4 =" + teacherId + ")");
                    break;
            }
        }
        example.setOrderByClause("update_time desc");

        Page<UserCourseVipOpsVo> pageVo = new Page<UserCourseVipOpsVo>();
        if (byPage) {
            PageHelper.startPage(pageIndex, pageSize, true);
        }
        List<UserCourseVip> pageList = (List<UserCourseVip>) userCourseVipMapper.selectByExample(example);
        for (UserCourseVip uc : pageList) {
        	UserCourseVipOpsVo vo = new UserCourseVipOpsVo();
            try {
                BeanUtils.copyProperties(vo, uc);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String kemus = vo.getKemus();
            String[] args = kemus.split(",");
            String kemuStr = "";
            for (int i = 0 ;i < args.length; i ++) {
            	String kemu = "";
            	if (args[i].equals("1")) {
            		kemu = "科目一";
            	} else if (args[i].equals("2")) {
            		kemu = "科目二";
            	} else if (args[i].equals("3")) {
            		kemu = "科目三";
            	} else if (args[i].equals("4")) {
            		kemu = "科目四";
            	} else if (args[i].equals("5")) {
            		kemu = "组合套餐";
            	}
            	kemuStr += kemu + " ";
            }
            vo.setKemuStr(kemuStr);
            // 分配的老师
            String assignTeacher = "";
            if (StringUtils.isNotEmpty(vo.getKemuTeacherName1())) {
            	assignTeacher += "科目一：" + vo.getKemuTeacherName1() + " ";
            } 
            if (StringUtils.isNotEmpty(vo.getKemuTeacherName2())) {
            	assignTeacher += "科目二：" + vo.getKemuTeacherName2()  + " ";
            }  
            if (StringUtils.isNotEmpty(vo.getKemuTeacherName3())) {
            	assignTeacher += "科目三：" + vo.getKemuTeacherName3()  + " ";
            }  
            if (StringUtils.isNotEmpty(vo.getKemuTeacherName4())) {
            	assignTeacher += "科目四：" + vo.getKemuTeacherName4()  + " ";
            }
            vo.setAssignTeacher(assignTeacher);
            pageVo.add(vo);
        }
        if (byPage) {
            pageVo.setTotal(((Page) pageList).getTotal());
        }
        return pageVo;
    }
    
    /**
     * 查已付款
     * @param id
     * @return
     */
    public List<UserCourseVip> selectPayCourseByUserId(Long userId) {
    	if (null == userId || userId <= 0) {
    		return null;
    	}
        //单表自定义查询
        Example example = new Example(UserCourseVip.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("user_id=", userId);
        criteria.andCondition("pay_status=2");        
        return userCourseVipMapper.selectByExample(example);
    }
    
    /**
     * 查我已购买vip
     * @param id
     * @return
     */
    public List<UserCourseVipVo> selectCourseDetailByUserId(Long userId) {
    	if (null == userId || userId <= 0) {
    		return null;
    	}
    	List<UserCourseVipVo> result = new ArrayList<>();
        //单表自定义查询
        Example example = new Example(UserCourseVip.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("user_id=", userId);
        criteria.andCondition("pay_status=2");   
        example.setOrderByClause("create_time desc");
        List<UserCourseVip> list = userCourseVipMapper.selectByExample(example);
        if (null != list && list.size() > 0) {
        	UserCourseVip data = list.get(0);
        	if (null != data) {        		
        		try {
					String kemus = data.getKemus();
					List<VipCourse> vipList = vipCourseService.selectAll();
					for (VipCourse vipCourse: vipList) {
						if (kemus.contains(vipCourse.getId().toString())) {
							UserCourseVipVo vo = new UserCourseVipVo();
							BeanUtils.copyProperties(vo, data);
							// 包含该课程
							vo.setKemuTitle(vipCourse.getTitle());
							vo.setKemuPrice(vipCourse.getPrice());
							vo.setKemuContent(vipCourse.getContent());
							result.add(vo);
						}
					}
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        	}
        }
        return result;
    }
    
	/**
	 * 查已付款
	 * TODO 增加缓存
	 * @param id
	 * @return
	 */
	public boolean isVipUser(Long userId) {
		if (null == userId || userId <= 0) {
			return false;
		}
		// 单表自定义查询
		Example example = new Example(UserCourseVip.class);
		Criteria criteria = example.createCriteria();
		criteria.andCondition("user_id=", userId);
		criteria.andCondition("pay_status=2");
		List<UserCourseVip> uCourse = userCourseVipMapper.selectByExample(example);
		;
		if (null != uCourse && uCourse.size() > 0) {
			// 已购买，返回
			return true;
		}
		return false;
	}
	
	/**
	 * 查已付款
	 * TODO 增加缓存
	 * @param id
	 * @return
	 */
	public UserCourseVip isVipUser2(Long userId) {
		if (null == userId || userId <= 0) {
			return null;
		}
		// 单表自定义查询
		Example example = new Example(UserCourseVip.class);
		Criteria criteria = example.createCriteria();
		criteria.andCondition("user_id=", userId);
		criteria.andCondition("pay_status=2");
		List<UserCourseVip> uCourse = userCourseVipMapper.selectByExample(example);
		;
		if (null != uCourse && uCourse.size() > 0) {
			// 已购买，返回
			return uCourse.get(0);
		}
		return null;
	}

}
