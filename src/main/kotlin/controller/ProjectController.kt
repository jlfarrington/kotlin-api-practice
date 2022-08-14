package controller

import model.Project
import org.bson.types.ObjectId
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import service.ProjectService

@RestController
@RequestMapping("/projects")
class ProjectController (
    private val projectService: ProjectService
        ) {
    @GetMapping
    fun getAllProjects(): ResponseEntity<List<Project>> {
        val projects = projectService.findAll()
        return ResponseEntity.ok(projects)
    }

    @GetMapping("/{id}")
    fun getProjectById(@PathVariable("id") id: String): ResponseEntity<Project> {
        val project = projectService.get(ObjectId(id))
        return ResponseEntity.ok(project)
    }

    @GetMapping("/in-progress")
    fun getProjectsInProgress(): ResponseEntity<List<Project>> {
        val projects = projectService.findAllInProgress()
        return ResponseEntity.ok(projects)
    }

    @PostMapping
    fun create(project: Project): ResponseEntity<Project> {
        val project = projectService.create(project)
        return ResponseEntity.ok(project)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: String, project: Project): ResponseEntity<Project> {
        val updatedProject = projectService.update(ObjectId(id), project)
        return ResponseEntity.ok(updatedProject)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: String) {
        projectService.delete(ObjectId(id))
    }

}
