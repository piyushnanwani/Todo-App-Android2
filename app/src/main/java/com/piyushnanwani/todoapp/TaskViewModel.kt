package com.piyushnanwani.todoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel: ViewModel() {
    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>>
        get() = _tasks

    fun addTask(taskTitle: String) {
        val currentTasks = _tasks.value ?: emptyList()
        val newTask = Task(taskTitle)
        _tasks.value = currentTasks + newTask
    }
}