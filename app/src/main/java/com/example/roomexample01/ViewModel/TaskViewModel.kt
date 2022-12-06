package com.example.roomexample01.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomexample01.Model.TaskDataBase
import com.example.roomexample01.Model.TaskEntity
import com.example.roomexample01.TaskRepository
import kotlinx.coroutines.launch


class TaskViewModel( application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository

    // Live Data que expone la info del modelo
    val allTask: LiveData<List<TaskEntity>>

    init {

        // Necesito La instancia del repositorio
        // ESTAN LAS 3 CLASE CONECTADAS
        val taskDao = TaskDataBase.getDatabase(application).getTaskDao()
        repository = TaskRepository(taskDao)
        allTask = repository.listAllTask

    }

    // El viewmodel Scope trabaja con las coroutines  hace que se ejecute el proceso en el hilo secundario
    fun insertTask(task: TaskEntity) = viewModelScope.launch{
        repository.insertTask(task)
    }
}