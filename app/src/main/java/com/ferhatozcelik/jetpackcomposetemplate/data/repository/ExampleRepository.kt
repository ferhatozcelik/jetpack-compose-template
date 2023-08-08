package com.ferhatozcelik.jetpackcomposetemplate.data.repository

import com.ferhatozcelik.jetpackcomposetemplate.data.dao.ExampleDao
import com.ferhatozcelik.jetpackcomposetemplate.data.remote.AppApi
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ExampleRepository @Inject constructor(private val appApi: AppApi, private val exampleDao: ExampleDao)