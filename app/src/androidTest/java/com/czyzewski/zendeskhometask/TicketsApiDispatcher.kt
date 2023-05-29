package com.czyzewski.zendeskhometask

import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection


class TicketsApiDispatcher(
    viewId: Long,
    page: Int,
) : Dispatcher() {

    private val path = "/api/v2/views/$viewId/tickets.json?page=$page"

    override fun dispatch(request: RecordedRequest): MockResponse {

        return when (request.path) {
            path-> successWithBody("ticketsResponse.json")
            else -> MockResponse().setResponseCode(500)
        }
    }
    private fun successWithBody(fileName: String): MockResponse {
        val jsonResponse = getStringFromFile(fileName)
        return MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(jsonResponse)
    }
    private fun getStringFromFile(fileName: String, replacements: Map<String, String> = emptyMap()): String {
        val context = InstrumentationRegistry.getInstrumentation().context
        var output = context.assets.open(fileName)
            .bufferedReader().use { it.readText() }
        replacements.forEach { (key, value) -> output = output.replace(key, value) }
        return output
    }
}

