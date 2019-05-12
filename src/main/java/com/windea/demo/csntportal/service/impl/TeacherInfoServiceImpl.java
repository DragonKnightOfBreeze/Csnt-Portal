package com.windea.demo.csntportal.service.impl;

import com.windea.demo.csntportal.domain.entity.TeacherInfo;
import com.windea.demo.csntportal.domain.entity.TeacherTeam;
import com.windea.demo.csntportal.exception.NotFoundException;
import com.windea.demo.csntportal.repository.TeacherInfoRepository;
import com.windea.demo.csntportal.repository.TeacherTeamRepository;
import com.windea.demo.csntportal.service.TeacherInfoService;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@CacheConfig(cacheNames = "teacherInfoCache")
public class TeacherInfoServiceImpl implements TeacherInfoService {
	private final TeacherInfoRepository repository;
	private final TeacherTeamRepository teacherTeamRepository;

	public TeacherInfoServiceImpl(TeacherInfoRepository repository, TeacherTeamRepository teacherTeamRepository) {
		this.repository = repository;
		this.teacherTeamRepository = teacherTeamRepository;
	}

	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public TeacherInfo saveByTeacherTeamId(TeacherInfo teacherInfo, Integer teacherTeamId) {
		var teacherTeam = teacherTeamRepository.findById(teacherTeamId)
			.orElseThrow(() -> {throw new NotFoundException();});
		teacherInfo.setTeacherTeam(teacherTeam);
		return repository.save(teacherInfo);
	}

	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	@CachePut
	@Transactional
	@Override
	public TeacherInfo update(TeacherInfo teacherInfo) {
		var origin = findById(teacherInfo.getId());
		origin.setGender(teacherInfo.getGender());
		origin.setIntroduce(teacherInfo.getIntroduce());
		origin.setName(teacherInfo.getName());
		origin.setProfession(teacherInfo.getProfession());
		origin.setTeacherTeam(teacherInfo.getTeacherTeam());
		return repository.save(origin);
	}

	@Override
	public TeacherInfo findById(Integer id) {
		var result = repository.findById(id)
			.orElseThrow(() -> {throw new NotFoundException();});
		return result;
	}

	@Override
	public TeacherTeam findTeacherTeamById(Integer id) {
		var result = repository.findTeacherTeamById(id).getTeacherTeam();
		Assert.notNull(result, () -> {throw new NotFoundException();});
		return result;
	}
}
