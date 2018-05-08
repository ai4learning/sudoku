package com.goldfish.service.impl;



import javax.annotation.Resource;
import java.util.List;
import java.util.Date;
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
    
    public CommonResult<User> addUser(User user) {
		CommonResult<User> result = new CommonResult<User>();
		try{
			
			user.setCreated(new Date());
			
			
			user.setModified(new Date());
			
			result.addDefaultModel(userManager.addUser(user));
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("添加 用户失败", e);
			result.setSuccess(false);
		}
		return result;
	}
	
	public CommonResult<User> updateUser(User user) {
		CommonResult<User> result = new CommonResult<User>();
		try {
			
					user.setModified(new Date());
					
			userManager.updateUser(user);
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
