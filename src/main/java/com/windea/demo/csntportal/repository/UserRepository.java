package com.windea.demo.csntportal.repository;

import com.windea.demo.csntportal.domain.entity.User;
import com.windea.demo.csntportal.enums.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/*
用法参考：http://www.ityouknow.com/springboot/2016/08/20/spring-boot-jpa.html

默认存在的方法：findOne(example),findById(id),findAll(example?,pageable?),
	delete(entity),deleteById(id),deleteAll(iter?),
	save(entity),saveAll(iter)
	count(example?)
	exists(example),existsById(id)

常用前缀：find,save,delete,update,count,exists
	findOne,findAll,findFirst10
指定查询字段：ByName,ByNameAndRole,ByNameOrRole,ByNameNot
    ByNameLike,ByNameNotLike,ByNameIgnoreCase,ByNameStartingWith,ByNameContaining
	ByAgeIn,ByAgeBetween,ByAgeLessThan,ByAgeLessThanEqual,ByDateBefore
	ByAgeIsNull,ByAgeNotNull,ByStateTrue,ByStateFalse
指定排序：OrderByNameAsc,OrderByNameAscAgeDesc

结果存在多个的情况，可以将返回值设为Page<T>, 将最后一个参数设为pageable

自定义SQL查询：为相关方法注上@Query(sql)，按情况注上@Transactional，@Modifying
里面的sql语句是jpa的写法，使用实体类名和字段名，引用实体类字段的字段需要join该实体类字段
占位符写成?1,?2的形式，也可以为参数注上@Param，然后写成:paramName的形式

多表查询：利用 Hibernate 的级联查询来实现，或者创建一个结果集接口来接收连表查询后的结果
这个结果集接口中的虚方法为对应的get方法，默认方法为特定的已实现的get方法
然后为查询方法注上@Query，返回类型为对应的结果集接口
*/

/**
 * 用户的持久接口。
 */
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);


	Page<User> findAllByNicknameContainingIgnoreCase(String nickname, Pageable pageable);

	Page<User> findAllByGender(Gender gender, Pageable pageable);

	Page<User> findAllByRole(Role role, Pageable pageable);

	Page<User> findAllByProfession(Profession profession, Pageable pageable);


	boolean existsByUsernameOrEmailOrPhoneNum(String username, String email, String phoneNum);
}
