package pdm.isel.yama_pdm.network

import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonRequest
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

class GetRequest(url: String, success: Response.Listener<UserDto>, error: Response.ErrorListener)
    : JsonRequest<UserDto>(Request.Method.GET, url, "", success, error) {

    override fun parseNetworkResponse(response: NetworkResponse): Response<UserDto> {
        val mapper = jacksonObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        val userDto = mapper.readValue(String(response.data), UserDto::class.java)
        return Response.success(userDto, null)
    }
}