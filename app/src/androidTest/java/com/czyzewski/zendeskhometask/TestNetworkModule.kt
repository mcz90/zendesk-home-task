package com.czyzewski.zendeskhometask

import com.czyzewski.zendeskhometask.network.di.NetworkModule
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]

)
class TestNetworkModule : NetworkModule() {
    override fun baseUrl() = "http://localhost:8080/"
}