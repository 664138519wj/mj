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
import com.github.pagehelper.util.StringUtil;
import com.migu.online.mapper.AreaMapper;
import com.migu.online.mapper.DrivingSchoolMapper;
import com.migu.online.mapper.PlatformNetworkMapper;
import com.migu.online.mapper.PlatformServiceNetworkImageMapper;
import com.migu.online.model.Area;
import com.migu.online.model.DrivingSchool;
import com.migu.online.model.PlatformNetwork;
import com.migu.online.model.PlatformServiceNetworkImage;
import com.migu.online.ops.vo.PlatformNetworkOpsVo;
import com.migu.online.vo.PlatformNetworkVo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class PlatformNetworkService {

	@Autowired
	private PlatformNetworkMapper platformNetworkMapper;
	@Autowired
	private PlatformServiceNetworkImageMapper platformServiceNetworkImageMapper;
	@Autowired
	private DrivingSchoolMapper drivingSchoolMapper;
	@Autowired
	private CourseOfflineService courseOfflineService;
	@Autowired
	private AreaMapper areaMapper;
	
	/**
     * 查询所有网点
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<PlatformNetworkVo> selectAll() throws Exception{
    	List<PlatformNetworkVo> result = new ArrayList<PlatformNetworkVo>();
        //单表自定义查询
        Example example = new Example(PlatformNetwork.class);
        example.createCriteria().andCondition("is_delete = 0");
        List<PlatformNetwork> pageList = platformNetworkMapper.selectByExample(example);
        for (PlatformNetwork data : pageList) {
        	PlatformNetworkVo vo = new PlatformNetworkVo();
			BeanUtils.copyProperties(vo, data);
			if (null != data.getSchoolId() && data.getSchoolId() > 0) {
				// 驾校获取
				DrivingSchool record = drivingSchoolMapper.selectByPrimaryKey(data.getSchoolId());
				if (null != record) {
					vo.setSchoolName(record.getNameS());
				}
			}
			result.add(vo);
		}
        return result;
    }
	
	 /**
     * 分页查询
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<PlatformNetworkVo> selectByPage(Integer pageIndex, Integer pageSize) throws Exception{
    	List<PlatformNetworkVo> result = new ArrayList<PlatformNetworkVo>();
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize,false);

        //单表自定义查询
        Example example = new Example(PlatformNetwork.class);
        example.createCriteria().andCondition("is_delete = 0");
        List<PlatformNetwork> pageList = platformNetworkMapper.selectByExample(example);
        for (PlatformNetwork data : pageList) {
        	PlatformNetworkVo vo = new PlatformNetworkVo();
			BeanUtils.copyProperties(vo, data);
			if (null != data.getSchoolId() && data.getSchoolId() > 0) {
				// 驾校获取
				DrivingSchool record = drivingSchoolMapper.selectByPrimaryKey(data.getSchoolId());
				if (null != record) {
					vo.setSchoolName(record.getNameS());
				}
			}
			result.add(vo);
		}
        return result;
    }
    
    /**
     * 分页查询
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    public Page<PlatformNetworkVo> selectWebByPage(Integer pageIndex, Integer pageSize) throws Exception{
    	Page<PlatformNetworkVo> result = new Page<PlatformNetworkVo>();
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize,true);

        //单表自定义查询
        Example example = new Example(PlatformNetwork.class);
        example.createCriteria().andCondition("is_delete = 0");
        Page<PlatformNetwork> pageList = (Page<PlatformNetwork>)platformNetworkMapper.selectByExample(example);
        for (PlatformNetwork data : pageList) {
        	PlatformNetworkVo vo = new PlatformNetworkVo();
			BeanUtils.copyProperties(vo, data);
			if (null != data.getSchoolId() && data.getSchoolId() > 0) {
				// 驾校获取
				DrivingSchool record = drivingSchoolMapper.selectByPrimaryKey(data.getSchoolId());
				if (null != record) {
					vo.setSchoolName(record.getNameS());
				}
			}
			result.add(vo);
		}
        result.setTotal(pageList.getTotal());
        return result;
    }
    
    /**
     * 分页查询
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    public Page<PlatformNetworkVo> selectWebConditionByPage(Map<String, Object> filters, Integer pageIndex, Integer pageSize) throws Exception{
    	Page<PlatformNetworkVo> result = new Page<PlatformNetworkVo>();
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize,true);        
        //单表自定义查询
        Example example = new Example(PlatformNetwork.class);
        Criteria criteria = example.createCriteria();
        Long areaId = (Long)filters.get("areaId");
        if (null != areaId) {
        	criteria.andCondition("area_id = ", areaId);
        }
        String platName = (String)filters.get("platName");
        if (StringUtil.isNotEmpty(platName)) {
        	criteria.andLike("name", "%" + platName + "%");
        }
        String address = (String)filters.get("address");
        if (StringUtil.isNotEmpty(address)) {
        	criteria.andLike("address", "%" + address + "%");
        }
        String startTime = (String)filters.get("startTime");
        Long teacherId = (Long)filters.get("teacherId");
        Integer kemu = (Integer)filters.get("kemu");
        if (StringUtil.isNotEmpty(startTime) || teacherId != null || null != kemu) {
        	List<Long> ids = courseOfflineService.selectForPlatformIds(kemu, teacherId, startTime);
        	if (null == ids || ids.size() <= 0) {
        		return null;
        	}
        	criteria.andIn("id", ids);
        }
        criteria.andCondition("is_delete = 0");
        int count = platformNetworkMapper.selectCountByExample(example);
        List<PlatformNetwork> pageList = platformNetworkMapper.selectByExample(example);
        for (PlatformNetwork data : pageList) {
        	PlatformNetworkVo vo = new PlatformNetworkVo();
			BeanUtils.copyProperties(vo, data);
			if (null != data.getSchoolId() && data.getSchoolId() > 0) {
				// 驾校获取
				DrivingSchool record = drivingSchoolMapper.selectByPrimaryKey(data.getSchoolId());
				if (null != record) {
					vo.setSchoolName(record.getNameS());
				}
			}
			result.add(vo);
		}
        result.setTotal(count);
        return result;
    }
    
    
    
    /**
     * 详情
     * @param id
     * @return
     */
    public PlatformNetworkVo selectOne(Long id) throws Exception {
    	PlatformNetworkVo vo = new PlatformNetworkVo();
    	PlatformNetwork model = new PlatformNetwork();
    	model.setId(id);
    	model.setIsDelete(0);
    	PlatformNetwork data = platformNetworkMapper.selectOne(model);
        if (null != data) {
        	List<String> imageList = new ArrayList<>();
    		BeanUtils.copyProperties(vo, data);
    		String image = data.getShufflingImage();
    		if (image.length() > 0) {
    			String args[] = image.split(";");
    			for (int i = 0; i < args.length; i++) {
    				imageList.add(args[i]);
    			}
    		}
    		vo.setImagePathList(imageList);
        }
        return vo;
    }
    	
	/*获取平台图片列表*/
	public List<PlatformServiceNetworkImage> selectImages(Integer fromId) {
		 
		// 单表自定义查询
		// Example example = new Example(Demo.class);
		PlatformServiceNetworkImage model=new PlatformServiceNetworkImage();
		model.setFromId(fromId);
		return platformServiceNetworkImageMapper.select(model);
	}
	
	/**
     * 删除
     * @param id
     * @return
     */
    public int deleteById(Long id) {
    	PlatformNetwork record = platformNetworkMapper.selectByPrimaryKey(id);
    	if (null != record) {
        	record.setIsDelete(1);
        	return platformNetworkMapper.updateByPrimaryKey(record);
    	}
    	return 0;
    }
    
    /**
     * 查
     * @param id
     * @return
     */
    public PlatformNetwork selectById(Long id) {
    	return platformNetworkMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 改&插
     * @param model
     * @return
     */
    public int createOrUpdate(PlatformNetwork model) {
    	if (null != model.getId() && model.getId() > 0) {
    		// update
    		return platformNetworkMapper.updateByPrimaryKey(model);
    	} else {
    		// add
    		return platformNetworkMapper.insert(model);
    	}
    }
    
    /**
     * 分页条件查找
     * @param filter
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<PlatformNetwork> selectConditonByPage(String filter, Integer pageIndex, Integer pageSize){
        // 单表分页
        PageHelper.startPage(pageIndex,pageSize);
        Example example = new Example(PlatformNetwork.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("is_delete = 0");
        if(StringUtils.isNotEmpty(filter)) {
            filter = "%" + filter + "%";
            criteria.andLike("name", filter);
        }
		example.setOrderByClause("update_time desc");
        return platformNetworkMapper.selectByExample(example);      
    }
    
    /**
     * 分页条件查找
     * @param filter
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public Page<PlatformNetworkOpsVo> selectConditonByPage2(String filter, Integer pageIndex, Integer pageSize) throws IllegalAccessException, InvocationTargetException{
        // 单表分页
        Page<PlatformNetworkOpsVo> result = new Page<PlatformNetworkOpsVo>();
        PageHelper.startPage(pageIndex, pageSize, true);
        Example example = new Example(PlatformNetwork.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("is_delete = 0");
        if(StringUtils.isNotEmpty(filter)) {
            filter = "%" + filter + "%";
            criteria.andLike("name", filter);
        }
		example.setOrderByClause("update_time desc");         
        Page<PlatformNetwork> pageList = (Page<PlatformNetwork>)platformNetworkMapper.selectByExample(example);  ;
		for (PlatformNetwork data : pageList) {
			PlatformNetworkOpsVo vo = new PlatformNetworkOpsVo();
			BeanUtils.copyProperties(vo, data);
			Area area = areaMapper.selectByPrimaryKey(data.getAreaId());
			if (null != area) {
				vo.setAreaName(area.getCity());
			}
			result.add(vo);
		}
		result.setTotal(pageList.getTotal());
        return result;
    }
    
    /**
     * 后台显示详情
     * @param id
     * @return
     */
    public PlatformNetworkOpsVo selectOpsOne(Long id) throws Exception {
    	PlatformNetworkOpsVo vo = new PlatformNetworkOpsVo();
    	PlatformNetwork model = new PlatformNetwork();
    	model.setId(id);
    	model.setIsDelete(0);
    	PlatformNetwork data = platformNetworkMapper.selectOne(model);
		if (null != data) {
			BeanUtils.copyProperties(vo, data);
            String images = data.getShufflingImage();
            List<String> shufflingImageList = new ArrayList<>();
            if (!StringUtils.isEmpty(images)) {
            	String args[] = images.split(";");
            	for (int i = 0; i < args.length; i++) {
            		shufflingImageList.add(args[i]);
            	}            	
            }
            vo.setShufflingImageList(shufflingImageList);        
            DrivingSchool school = drivingSchoolMapper.selectByPrimaryKey(data.getSchoolId());
            if (null != school && StringUtils.isNotEmpty(school.getNameS())) {
            	vo.setSchoolName(school.getNameS());
            } else {
            	vo.setSchoolName("无");
            }
            Area area = areaMapper.selectByPrimaryKey(data.getAreaId());
			if (null != area) {
				vo.setAreaName(area.getCity());
			}
		}

        return vo;
    }
    
	
}
