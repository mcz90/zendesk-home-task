package com.czyzewski.zendeskhometask.domain.di

import com.czyzewski.zendeskhometask.domain.mapping.DataMapper
import com.czyzewski.zendeskhometask.domain.mapping.QueryExtractor
import com.czyzewski.zendeskhometask.domain.mapping.TicketsMapper
import com.czyzewski.zendeskhometask.domain.model.TicketResponseModel
import com.czyzewski.zendeskhometask.domain.repository.TicketsRepository
import com.czyzewski.zendeskhometask.domain.usecase.GetTicketsListUseCase
import com.czyzewski.zendeskhometask.network.datasource.ITicketsDataSource
import com.czyzewski.zendeskhometask.network.datasource.TicketsDataSource
import com.czyzewski.zendeskhometask.network.dto.TicketsResponseDto
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [Domain::class])
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindTicketsMapper(
        mapper: TicketsMapper
    ): DataMapper<TicketsResponseDto, TicketResponseModel>

    @Binds
    abstract fun bindTicketsDataSource(
        dataSource: TicketsDataSource
    ): ITicketsDataSource
}

@Module
@InstallIn(SingletonComponent::class)
internal object Domain {

    @Provides
    @Singleton
    fun provideQueryExtractor(
    ): QueryExtractor<Int> {
        return QueryExtractor()
    }
    @Provides
    @Singleton
    fun provideGetTicketsListUseCase(
        repository: TicketsRepository
    ): GetTicketsListUseCase {
        return GetTicketsListUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideTicketsRepository(
        mapper: TicketsMapper,
        dataSource: TicketsDataSource
    ): TicketsRepository {
        return TicketsRepository(mapper, dataSource)
    }
}