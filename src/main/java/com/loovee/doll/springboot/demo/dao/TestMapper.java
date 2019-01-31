package com.loovee.doll.springboot.demo.dao;

import com.loovee.doll.springboot.demo.bean.po.User;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Function:
 *
 * @author HeXin
 * @date 10:02 2019/1/30
 * @since JDK 1.8
 */
@Mapper
@Repository
public interface TestMapper {

	/**
	 * 根据用户Id查询用户基本信息
	 * @param id
	 * @return
	 */
	User findUserById(Long id);
}
