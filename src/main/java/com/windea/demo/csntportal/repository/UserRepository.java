package com.windea.demo.csntportal.repository;

import com.windea.demo.csntportal.domain.entity.User;
import com.windea.demo.csntportal.domain.enums.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/*
用法参考：http://www.ityouknow.com/springboot/2016/08/20/spring-boot-jpa.html

默认存在的方法：findOne(example),get(id),list(example?,pageable?),
	delete(entity),delete(id),deleteAll(iter?),
	create(entity),saveAll(iter)
	count(example?)
	exists(example),existsById(id)

常用前缀：find,register,delete,update,count,exists
	findOne,list,findFirst10,findTop10,findDistinct
指定查询字段：ByName,ByNameAndRole,ByNameOrRole,ByNameNot
    ByNameLike,ByNameNotLike,ByNameIgnoreCase,ByNameStartingWith,ByNameStartsWith,ByNameContaining,ByNameContains
	ByTagIn,ByAgeBetween,ByAgeLessThan,ByAgeLessThanEqual,ByDateBefore
	ByAgeIsNull,ByAgeNotNull,ByStateTrue,ByStateFalse
	By.....AllIgnoreCase
指定排序：OrderByNameAsc,OrderByNameAscAgeDesc
根据实体类的实体类属性查询：BySponsorUser_Username
模糊查询：ByNameLike直接利用原有字符串进行模糊查询，ByNameContaining在左右添加%占位符
界限查询：ByTagIn参数为集合，ByAgeBetween参数为上下限

结果存在多个的情况，可以将返回值设为Page<T>, 将最后一个参数设为pageable

自定义SQL查询：为相关方法注上@Query(sql)，按情况注上@Transactional，@Modifying
里面的sql语句是jpa的写法，使用实体类名和字段名，引用实体类字段的字段需要join该实体类字段
占位符写成?1,?2的形式，也可以为参数注上@Param，然后写成:paramName的形式，参数名相同视注解属性可省略

多表查询：利用 Hibernate 的级联查询来实现，或者创建一个结果集接口来接收连表查询后的结果
这个结果集接口中的虚方法为对应的get方法，默认方法为特定的已实现的get方法
然后为查询方法注上@Query，返回类型为对应的结果集接口
*/

/**
 * 用户的持久接口。
 */
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);

	Page<User> findAllByNicknameContainsIgnoreCase(String nickname, Pageable pageable);

	Page<User> findAllByGender(Gender gender, Pageable pageable);

	Page<User> findAllByRole(Role role, Pageable pageable);

	Page<User> findAllByProfession(Profession profession, Pageable pageable);

	boolean existsByUsernameOrEmailOrPhoneNum(String username, String email, String phoneNum);
}
