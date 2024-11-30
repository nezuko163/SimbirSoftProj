package com.nezuko.businessdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewModelScope
import com.nezuko.domain.model.Business
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusinessDetailsViewModel @Inject constructor() : ViewModel() {
    fun getBusinessById(id: Int): Flow<Business?> {
        return flow {
            emit(null)
        }.flowOn(Dispatchers.IO)
    }

    fun saveBusiness(business: Business) {

    }
}