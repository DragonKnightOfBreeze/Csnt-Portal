package com.windea.demo.csntportal.service.impl;

import com.windea.commons.springboot.utils.PageUtils;
import com.windea.demo.csntportal.domain.entity.TeacherTeam;
import com.windea.demo.csntportal.domain.request.TeacherTeamSearchVo;
import com.windea.demo.csntportal.enums.ProfessionLevel;
import com.windea.demo.csntportal.exception.ResultEmptyException;
import com.windea.demo.csntportal.exception.ResultNotFoundException;
import com.windea.demo.csntportal.repository.TeacherTeamRepository;
import com.windea.demo.csntportal.service.TeacherTeamService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Set;

@Service
public class TeacherTeamServiceImpl implements TeacherTeamService {
	private final TeacherTeamRepository repository;

	public TeacherTeamServiceImpl(TeacherTeamRepository repository) {this.repository = repository;}

	@Transactional
	@Override
	public TeacherTeam save(TeacherTeam teacherTeam) {
		return repository.save(teacherTeam);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	@Transactional
	@Override
	public TeacherTeam update(TeacherTeam teacherTeam) {
		var origin = findById(teacherTeam.getId());
		origin.setName(teacherTeam.getName());
		origin.setProfessionLevel(teacherTeam.getProfessionLevel());
		origin.setIntroduce(teacherTeam.getIntroduce());
		origin.setTeacherInfoList(teacherTeam.getTeacherInfoList());
		return repository.save(origin);
	}


	@Override
	public TeacherTeam findById(Integer id) {
		var result = repository.findById(id).orElseThrow(() -> {throw new ResultNotFoundException();});
		return result;
	}

	@Override
	public Page<TeacherTeam> findAll(Pageable pageable) {
		var resultPage = repository.findAll(pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}

	@Override
	public Page<TeacherTeam> findAllByNameContaining(String name, Pageable pageable) {
		var resultPage = repository.findAllByNameContainingIgnoreCase(name, pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}

	@Override
	public Page<TeacherTeam> findAllByProfessionLevelIn(Set<ProfessionLevel> levelSet, Pageable pageable) {
		var resultPage = repository.findAllByProfessionLevelIn(levelSet, pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}

	@Override
	public Page<TeacherTeam> findAllByTeacherCountBetween(Integer begin, Integer end, Pageable pageable) {
		var resultPage = repository.findAllByTeacherCountBetween(begin, end, pageable);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}

	@Override
	public Page<TeacherTeam> findAllByConditions(TeacherTeamSearchVo vo, Pageable pageable) {
		var page1 = repository.findAllByNameContainingIgnoreCase(vo.getName(), pageable);
		var page2 = repository.findAllByProfessionLevelIn(vo.getLevelSet(), pageable);
		var page3 = repository.findAllByTeacherCountBetween(vo.getBegin(), vo.getEnd(), pageable);
		var resultPage = PageUtils.concat(pageable, page1, page2, page3);
		Assert.notEmpty(resultPage.getContent(), () -> {throw new ResultEmptyException();});
		return resultPage;
	}
}
