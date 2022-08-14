package service

import model.Project
import org.bson.types.ObjectId

interface ProjectService {
    fun create(createProjectRequest: Project): Project
    fun get(id: ObjectId): Project
    fun update(id: ObjectId, project: Project): Project
    fun delete(id: ObjectId)
    fun findAllInProgress(): List<Project>
    fun findAll(): List<Project>
}