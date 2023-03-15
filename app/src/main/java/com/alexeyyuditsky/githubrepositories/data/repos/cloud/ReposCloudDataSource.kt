package com.alexeyyuditsky.githubrepositories.data.repos.cloud

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData

interface ReposCloudDataSource {

    suspend fun fetchRepos(query: String): LiveData<PagingData<RepoCloud>>
    /* suspend fun fetchRepos(query: String): ReposResponse */

    class Base(
        private val service: ReposService,
    ) : ReposCloudDataSource {

        override suspend fun fetchRepos(query: String): LiveData<PagingData<RepoCloud>> {
            return Pager(
                config = PagingConfig(
                    pageSize = NETWORK_PAGE_SIZE,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = { PagingReposSource(service, query) }
            ).liveData
        }

        /*override suspend fun fetchRepos(query: String): ReposResponse {
            return service.fetchRepos(query)
        }*/

    }

}
