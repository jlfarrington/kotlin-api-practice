package repository

import model.Project
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface ProjectRepository: MongoRepository<Project, String> {
    override fun deleteAll()
}