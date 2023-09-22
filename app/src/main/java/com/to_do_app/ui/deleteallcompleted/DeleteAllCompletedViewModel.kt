package com.to_do_app.ui.deleteallcompleted

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.to_do_app.data.PreferencesManager
import com.to_do_app.data.TaskDao
import com.to_do_app.di.ApplicationScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteAllCompletedViewModel @Inject constructor(
    private val taskDao: TaskDao,
    @ApplicationScope private val applicationScope: CoroutineScope
) : ViewModel() {

    fun onConfirmClick() = applicationScope.launch {
        taskDao.deleteCompletedTasks()
    }
}