package service.impl

import model.Project
import org.bson.types.ObjectId
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import repository.ProjectRepository
import service.ProjectService

@Service
class ProjectServiceImpl (
    val projectRepository: ProjectRepository
): ProjectService {
    override fun create(project: Project): Project {
        val project = Project(
            id = project.id,
            name = project.name,
            description = project.description,
            startedDate = project.startedDate,
            completedDate = project.completedDate,
            moneySpent = null
        )

        projectRepository.save(project)
        return project
    }

    override fun get(id: ObjectId): Project {
        val idString = id.toString()
        return findProjectByIdOrNotFound(idString)
    }

    override fun update(id: ObjectId, project: Project): Project {
        val projectToUpdate = findProjectByIdOrNotFound(id.toString())

        projectToUpdate.apply {
            description = project.description!!
            startedDate = project.startedDate!!
            completedDate = project.completedDate!!
            moneySpent = project.moneySpent!!
        }

        projectRepository.save(projectToUpdate)

        return projectToUpdate
    }

    override fun delete(id: ObjectId) {
        val projectToDelete = findProjectByIdOrNotFound(id.toString())
        projectRepository.delete(projectToDelete)
    }

    override fun findAll(): List<Project> {
        return projectRepository.findAll()
    }

    override fun findAllInProgress(): List<Project> {
        val projectList = projectRepository.findAll()
        return projectList.filter { it.startedDate !== null && it.completedDate == null}
    }

    private fun findProjectByIdOrNotFound(id: String): Project {
        val project = projectRepository.findByIdOrNull(id)
        if (project == null) {
            throw NotFoundException()
        } else return project
    }

}