package com.projetojpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetojpa.entities.Curso;
import com.projetojpa.repository.CursoRepository;

@Service
public class CursoService {
	
private final CursoRepository cursoRepository;
	
	@Autowired
	public CursoService(CursoRepository cursoRepository) {
		this.cursoRepository = cursoRepository;
	}
	
	public List<Curso> buscaTodosCurso(){
		return cursoRepository.findAll();
	}
	
	public Curso BuscaCursoId(Long id) {
		Optional <Curso> Curso = cursoRepository.findById(id);
		return Curso.orElse(null);
	}
	
	public Curso salvaCurso(Curso curso) {
		return cursoRepository.save(curso);	
	}
	
	public Curso alterarCurso (Long id, Curso alterarC) {
		Optional <Curso> existeCurso = cursoRepository.findById(id);
		if (existeCurso.isPresent()) {
			alterarC.setId(id);
			return cursoRepository.save(alterarC);
		}
		return null;
	}
	
	public boolean apagarCurso(Long id) {
		Optional <Curso> existeCurso = cursoRepository.findById(id);
		if (existeCurso.isPresent()) {
			cursoRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
