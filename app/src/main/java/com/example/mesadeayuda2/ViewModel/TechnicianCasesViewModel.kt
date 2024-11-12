package com.example.mesadeayuda2.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.mesadeayuda2.dao.CaseDao
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class TechnicianCasesViewModel @Inject constructor(private val caseDao: CaseDao) : ViewModel() {
    val cases = liveData(Dispatchers.IO) {
        emit(caseDao.getAllCases())
    }
}
