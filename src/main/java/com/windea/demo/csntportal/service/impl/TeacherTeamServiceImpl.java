package com.windea.demo.csntportal.service.impl;

import com.windea.demo.csntportal.domain.entity.TeacherInfo;
import com.windea.demo.csntportal.domain.entity.TeacherTeam;
import com.windea.demo.csntportal.domain.enums.ProfessionLevel;
import com.windea.demo.csntportal.domain.vo.TeacherTeamQueryVo;
import com.windea.demo.csntportal.exception.NotFoundException;
import com.windea.demo.csntportal.repository.TeacherInfoRepository;
import com.windea.demo.csntportal.repository.TeacherTeamRepository;
import com.windea.demo.csntportal.service.TeacherTeamService;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


@Service
@CacheConfig(cacheNames = "teacherTeamCache")
public class TeacherTeamServiceImpl implements TeacherTeamService {
	private final TeacherTeamRepository repository;
	private final TeacherInfoRepository teacherInfoRepository;

	public TeacherTeamServiceImpl(TeacherTeamRepository repository,
		TeacherInfoRepository teacherInfoRepository) {
		this.repository = repository;
		this.teacherInfoRepository = teacherInfoRepository;
	}


	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public TeacherTeam create(TeacherTeam teacherTeam) {
		return repository.save(teacherTeam);
	}

	@CacheEvict(allEntries = true)
	@Transactional
	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}

	@CachePut
	@Transactional
	@Override
	public TeacherTeam update(TeacherTeam teacherTeam) {
		var origin = get(teacherTeam.getId());
		origin.setName(teacherTeam.getName());
		origin.setProfessionLevel(teacherTeam.getProfessionLevel());
		origin.setIntroduce(teacherTeam.getIntroduce());
		origin.setTeacherInfoList(teacherTeam.getTeacherInfoList());
		return repository.save(origin);
	}

	@Cacheable
	@Override
	public TeacherTeam get(Integer id) {
		var result = repository.findById(id)
			.orElseThrow(() -> {throw new NotFoundException();});
		return result;
	}

	@Cacheable
	@Override
	public List<TeacherInfo> getTeacherInfoList(Integer id) {
		var resultList = teacherInfoRepository.findAllByTeacherTeam_Id(id);
		return resultList;
	}

	@Cacheable
	@Override
	public Page<TeacherTeam> list(Pageable pageable) {
		var resultPage = repository.findAll(pageable);
		return resultPage;
	}

	@Cacheable
	@Override
	public Page<TeacherTeam> searchByName(String name, Pageable pageable) {
		var resultPage = repository.findAllByNameContainsIgnoreCase(name, pageable);
		return resultPage;
	}

	@Cacheable
	@Override
	public Page<TeacherTeam> searchByProfessionLevel(Set<ProfessionLevel> levelSet, Pageable pageable) {
		var resultPage = repository.findAllByProfessionLevelIn(levelSet, pageable);
		return resultPage;
	}

	@Cacheable
	@Override
	public Page<TeacherTeam> searchByTeacherCount(Integer min, Integer max, Pageable pageable) {
		var resultPage = repository.findAllByTeacherCountBetween(min, max, pageable);
		return resultPage;
	}

	@Cacheable
	@Override
	public Page<TeacherTeam> advanceSearch(TeacherTeamQueryVo vo, Pageable pageable) {
		var resultPage = repository.findAllByNameContainsAndTeacherCountBetweenAndProfessionLevelIn(
			vo.getName(), vo.getMin(), vo.getMax(), vo.getLevelSet(), pageable
		);
		return resultPage;
	}
}
