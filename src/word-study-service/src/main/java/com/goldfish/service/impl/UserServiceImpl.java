package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

import com.goldfish.common.log.LogTypeEnum;
import com.goldfish.constant.ActivateCodeState;
import com.goldfish.constant.FinishState;
import com.goldfish.constant.TaskType;
import com.goldfish.domain.ActivateCode;
import com.goldfish.domain.Task;
import com.goldfish.manager.ActivateCodeManager;
import com.goldfish.manager.TaskManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.goldfish.common.PageQuery;
import com.goldfish.common.CommonResult;
import com.goldfish.domain.User;
import com.goldfish.manager.UserManager;
import com.goldfish.service.UserService;


/**
 * @author hellosscat
 * @since 2018-5-8
 *<p>  用户service实现</p>
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Resource(name="userManager")
	private UserManager userManager;
	@Resource(name="activateCodeManager")
	private ActivateCodeManager activateCodeManager;
	@Resource
	private TaskManager taskManager;
    
    public CommonResult<User> addUser(User user) {
		CommonResult<User> result = new CommonResult<User>();
		try {
			// 1.check activateCode
			if (!activateCodeCheck(user, result)) {
				return result;
			}
			user.setCreated(new Date());
			user.setModified(new Date());
			User newUser = userManager.addUser(user);
			buildInitUserStudyDataTask(newUser);
			result.addDefaultModel(newUser);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 用户失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 用户
	 * @param newUser
	 */
	private void buildInitUserStudyDataTask(User newUser) {
		// 已关联课程，则尝试创建任务
		try {
			Integer userId = Integer.valueOf(String.valueOf(newUser.getId()));
			String lessonIds = newUser.getLessonIds();
			if (StringUtils.isEmpty(lessonIds)) {
				return;
			}
			String[] lessons = lessonIds.trim().split(",|，");
			Task task = new Task();
			Task query = new Task();
			query.setUserId(userId);
			Date current = new Date();
			for (String lessonId : lessons) {
				query.setBusinessId(Long.valueOf(lessonId));
				Task queridTask = taskManager.getUniqueValid(query);
				if (queridTask == null) {
					// 不存在，则插入
					task.setType(TaskType.INIT_STUDY_DATA.getType());
					task.setState(FinishState.NOT_COMPLETE.getState());
					task.setUserId(Integer.valueOf(String.valueOf(newUser.getId())));
					task.setBusinessId(Long.valueOf(lessonId));
					task.setCreated(current);
					task.setModified(current);
					taskManager.addTask(task);
				}
			}

		} catch (Exception e) {
			LogTypeEnum.DEFAULT.error(e, "创建初始化任务失败");
		}
	}

	private boolean activateCodeCheck(User user, CommonResult<User> result) {
		String activateCode = user.getActivateCode();
		if (StringUtils.isNotEmpty(activateCode)) {
            ActivateCode query = new ActivateCode();
            query.setActivateCode(activateCode.trim());
            ActivateCode uniqueCode = activateCodeManager.getUnique(query);
            if (uniqueCode == null) {
                result.setMessage("激活码不存在");
				return false;
            }
			if (uniqueCode.getState() == ActivateCodeState.INVALID.getState()) {
				result.setMessage("激活码无效");
				return false;
			}
			if (uniqueCode.getState() == ActivateCodeState.EXPIRED.getState()) {
				result.setMessage("激活码超期");
				return false;
			}
			if (uniqueCode.getState() == ActivateCodeState.USED.getState()) {
				User userQuery = new User();
				userQuery.setUserId(user.getUserId());
				userQuery.setActivateCode(activateCode);
				User unique = userManager.getUnique(userQuery);
				if (unique == null) {
					result.setMessage("激活码已使用");
					return false;
				}
			}
			if (uniqueCode.getState() == ActivateCodeState.NORMAL.getState()) {
				// 将激活码更改为已使用
				uniqueCode.setState(ActivateCodeState.USED.getState());
				activateCodeManager.updateActivateCode(uniqueCode);
				// 设置课程ID
			}
			user.setLessonIds(uniqueCode.getLessonIds());
		}
		return true;
	}

	public CommonResult<User> updateUser(User user) {
		CommonResult<User> result = new CommonResult<User>();
		try {
			// 1.check activateCode
			if (!activateCodeCheck(user, result)) {
				return result;
			}
			user.setModified(new Date());
			userManager.updateUser(user);
			buildInitUserStudyDataTask(user);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("更新 用户失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
   

	public CommonResult<User> deleteUser(Long id) {
		CommonResult<User> result = new CommonResult<User>();
		try {
			userManager.deleteUser(id);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除 用户失败", e);
			result.setSuccess(false);
		}
		return result;
    }


    	public CommonResult<User> getUserById(Long id) {
		CommonResult<User> result = new CommonResult<User>();
		try {
			result.addDefaultModel("user", userManager.getUserById(id));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据主键获取 用户失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	

        
	


	public CommonResult<User> getUnique(User user) {
		CommonResult<User> result = new CommonResult<User>();
		try {
			result.addDefaultModel(userManager.getUnique(user));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("根据example获取唯一 用户失败", e);
			result.setSuccess(false);
		}
		return result;
	}


	public CommonResult<List<User>> getListByExample(User user) {
		CommonResult<List<User>> result = new CommonResult<List<User>>();
		try {
			List<User> list = userManager.getListByExample(user);
			result.addDefaultModel("list", list);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("取得 用户失败", e);
			result.setSuccess(false);
		}
		return result;
	}

	
	public CommonResult<List<User>> getUserByPage(PageQuery pageQuery) {
		CommonResult<List<User>> result = new CommonResult<List<User>>();
		try {
			int totalCount = this.count(pageQuery);
			if (totalCount > 0) {
				pageQuery.setTotalCount(totalCount);
				List<User> list = userManager.getUserByPage(pageQuery);
				result.addDefaultModel("list", list);
				result.addModel("pageQuery", pageQuery);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("分页获取 用户失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public int count(PageQuery pageQuery) {
		return userManager.count(pageQuery);
	}


	/******* getter and setter ***/
	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

}
