package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	ProjectRepository proRepo;

	@Autowired
	EmployeeRepository empRepo;

	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		Project project = new Project();

		List<Employee> employees = empRepo.findAll();
		model.addAttribute("allEmployees", employees);

		model.addAttribute("project", project);
		return "projects/new-project";
	}

	@PostMapping("/save")
	public String createProject(Project project, Model model) {
		proRepo.save(project);

		return "redirect:/projects";
	}

	@GetMapping()
	public String listProjects(Model model) {

		List<Project> projects = proRepo.findAll();
		model.addAttribute("projects", projects);
		return "/projects/list-projects";

	}

}
