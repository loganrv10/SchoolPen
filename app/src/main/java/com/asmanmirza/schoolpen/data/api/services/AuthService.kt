package com.asmanmirza.schoolpen.data.api.services

import com.asmanmirza.schoolpen.data.api.NetworkConstants
import com.asmanmirza.schoolpen.domain.model.auth.AuthResponse
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @Author: Asman Mirza
 * @Email: asman@otmalse.com
 * @Date: 28-09-2022
 * @Time: 21:10
 */
interface AuthService {
    @POST(NetworkConstants.Api.EndPoints.Auth.SIGNIN)
    suspend fun login(@Body json: JsonObject): AuthResponse

    @POST(NetworkConstants.Api.EndPoints.Auth.SIGNUP)
    suspend fun register(@Body json: JsonObject): AuthResponse

    @POST(NetworkConstants.Api.EndPoints.Auth.GENERATE_OTP)
    suspend fun generateOtp(@Body json: JsonObject): AuthResponse

    @POST(NetworkConstants.Api.EndPoints.Auth.VERIFY_OTP)
    suspend fun verifyOtp(@Body json: JsonObject): AuthResponse
}