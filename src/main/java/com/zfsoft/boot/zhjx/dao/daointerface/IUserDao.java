package com.zfsoft.boot.zhjx.dao.daointerface;

import com.zfsoft.boot.authz.dao.entities.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.zfsoft.boot.zhjx.dao.entities.MobileUserModel;

@Mapper
public interface IUserDao {

	public UserModel getUserByUuid(String uuid);

	public MobileUserModel checkWxLogin(@Param("userName")String userName,@Param("pass") String pass);

	public MobileUserModel selectUserByYhm(@Param("yhm") String yhm);

	void insertUser(MobileUserModel user);

	public MobileUserModel selectUserByOpenid(@Param("openId") String openId);

	void updateOpenid(@Param("openid")String openid,@Param("yhm") String yhm);

	void clearOpenid(@Param("openid")String openid,@Param("yhm") String yhm);

}
