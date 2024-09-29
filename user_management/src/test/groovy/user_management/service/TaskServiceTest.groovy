package user_management.service

import com.example.user_management.task_management.service.TaskService
import com.example.user_management.task_management.dto.TaskDto
import com.example.user_management.signin.mapper.TaskMapper
import com.example.user_management.task_management.dao.repository.TaskRepository
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class TaskServiceTest extends Specification {

    private TaskService taskService
    private TaskRepository taskRepository

    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def "setup"() {
        taskRepository = Mock()
        taskService = new TaskService(taskRepository)
    }

    def "addTask - SUCCESS case"() {
        given:
        def dto = random.nextObject(TaskDto)
        def entity = TaskMapper.INSTANCE.toEntity(dto)

        when:
        taskService.addTask(dto)

        then:
        taskRepository.save(entity) >> entity
    }

    def "editTask - SUCCESS case"() {
        given:
        def taskId = 1L // Set an appropriate task ID for testing
        def updatedTaskDto = random.nextObject(TaskDto)
        def existingTask = TaskMapper.INSTANCE.toEntity(updatedTaskDto)

        when:
        taskService.editTask(taskId, updatedTaskDto)

        then:
        1 * taskRepository.findById(taskId) >> Optional.of(existingTask)
        1 * taskRepository.save(existingTask) >> existingTask
    }

    def "deleteTask - SUCCESS case"() {
        given:
        def taskId = 1L

        when:
        taskService.deleteTask(taskId)

        then:
        1 * taskRepository.deleteById(taskId)
    }
}
