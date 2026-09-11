package com.ferhatozcelik.jetpackcomposetemplate.data.repository

import com.ferhatozcelik.jetpackcomposetemplate.data.dao.ExampleDao
import com.ferhatozcelik.jetpackcomposetemplate.data.model.ExampleModel
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Resource
import com.ferhatozcelik.jetpackcomposetemplate.data.remote.AppApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Single source of truth for example data. Bridges the remote [AppApi] and the
 * local [ExampleDao], and exposes results wrapped in [Resource] so callers
 * (use cases / view models) don't need to know about the underlying data source.
 */
@Singleton
class ExampleRepository @Inject constructor(
    private val appApi: AppApi,
    private val exampleDao: ExampleDao,
) {

    fun getExamples(): Flow<Resource<List<ExampleModel>>> = flow {
        emit(Resource.Loading)
        try {
            val response = appApi.getExampleResult()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                emit(Resource.Success(body))
            } else {
                emit(Resource.Error("Request failed with code ${response.code()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown network error"))
        }
    }
}
