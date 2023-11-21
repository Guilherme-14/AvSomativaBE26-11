package com.projetojpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetojpa.entities.Curso;
import com.projetojpa.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Curso" , description = "API REST DE GERENCIAMENTO DE CURSOS")
@RestController
@RequestMapping("/curso")
public class CursoController {

	private final CursoService cursoService;

	@Autowired
	public CursoController (CursoService cursoService) {
		this.cursoService = cursoService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza o curso pelo ID")
	public ResponseEntity<Curso> buscaCursoControlId(@PathVariable Long id){
		Curso curso = cursoService.BuscaCursoId(id);
		if (curso != null) {
			return ResponseEntity.ok(curso);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	@Operation(summary = "Apresenta todos os cursos")
	public ResponseEntity<List<Curso>> buscaTodosCursoCOntrol(){
		List<Curso> Curso = cursoService.buscaTodosCurso();
		return ResponseEntity.ok(Curso);
	}

	@PostMapping
	@Operation(summary = "Cadastra um Curso")
	public ResponseEntity<Curso> salvaCursoControl (@RequestBody @Valid Curso curso){
		Curso salvaCurso = cursoService.salvaCurso(curso);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaCurso);
	}
	@PutMapping("/{id}")
	@Operation(summary = "Altera os Cursos")
	public ResponseEntity<Curso> alterarCursoControl(@PathVariable Long id, @RequestBody @Valid Curso curso){
		Curso alterarCurso = cursoService.alterarCurso(id, curso);
		if(alterarCurso != null) {
			return ResponseEntity.ok(curso);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui os Cursos")
	public ResponseEntity<Curso> apagaCursoControl (@PathVariable Long id){
		boolean apagar = cursoService.apagarCurso(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
