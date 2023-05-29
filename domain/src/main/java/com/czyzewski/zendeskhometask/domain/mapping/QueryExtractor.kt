package com.czyzewski.zendeskhometask.domain.mapping

import java.net.MalformedURLException
import java.net.URL
import javax.inject.Inject

class QueryExtractor<Type> @Inject constructor() {
    fun extract(url: String?, parameterName: String): Type? {
        return try {
            URL(url).findParameterValue(parameterName)
        } catch (error: MalformedURLException) {
            null
        }
    }

    private fun URL.findParameterValue(parameterName: String): Type? {
        return query?.split('&')
            ?.map {
                val parts = it.split('=')
                val name = parts.firstOrNull() ?: ""
                val value = parts.drop(1).firstOrNull() ?: ""
                Pair(name, value)
            }
            ?.firstOrNull { it.first == parameterName }?.second as? Type?
    }
}