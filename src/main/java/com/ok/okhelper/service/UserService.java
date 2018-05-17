package com.ok.okhelper.service;

import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.common.ServerResponse;
import com.ok.okhelper.pojo.bo.IdAndNameBo;
import com.ok.okhelper.pojo.dto.UserAndRoleDto;
import com.ok.okhelper.pojo.dto.UserAndStoreDto;
import com.ok.okhelper.pojo.dto.UserDto;
import com.ok.okhelper.pojo.dto.UserUpdateDto;
import com.ok.okhelper.pojo.po.User;
import com.ok.okhelper.pojo.vo.EmployeeVo;
import com.ok.okhelper.pojo.vo.UserVo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 *Author:zhangxin_an
 *Description:
 *Data:Created in 21:26 2018/4/10
 */
public interface UserService {

    /*
     *Author:zhangxin_an
     *Description:查找用户
     *Data:Created in  .21:30 2018/4/10
     */

    User findUserByUserNme(String username);

    ServerResponse loginUser(String userName, String password, String ip);

    /*
     * @Author zhangxin_an
     * @Date 2018/4/15 8:27
     * @Params [userDto]
     * @Return com.ok.okhelper.common.ServerResponse
     * @Description:用户注册
     */
    void userRegister(UserAndStoreDto userAndStoreDto);

    ServerResponse checkUserName(String userName);
    
    ServerResponse checkPassword(String userName);

    ServerResponse getUserListByStoreId(String token);

    /*
     * @Author zhangxin_an
     * @Date 2018/4/25 8:47
     * @Params [userDto]
     * @Return com.ok.okhelper.common.ServerResponse
     * @Description:
     */
    ServerResponse<IdAndNameBo> addEmployee(UserDto userDto);

    /*
     * @Author zhangxin_an
     * @Date 2018/4/19 17:38
     * @Params []
     * @Return java.util.List<com.ok.okhelper.pojo.vo.EmployeeVo>
     * @Description:获取员工
     */
    PageModel<EmployeeVo> getEmployeeList(PageModel pageModel,Integer deleteStatus);


    UserVo getUserInfo(Long id);
	
	void sendMs(String number);
    
    UserVo verifyPhoneCode(String phone, String code);
	
	void updateMyInfo(UserUpdateDto userDto);
    
    void deleteEmployee(Long id);
	
	void changeEmplyeeStatus(Long userId, Integer status);
    
    User getUserInfoByUserName(String userName);
}
