package com.asmanmirza.schoolpen.data.api.repository

import com.asmanmirza.schoolpen.common.NetworkResponse
import com.asmanmirza.schoolpen.data.api.services.AuthService
import com.asmanmirza.schoolpen.domain.api.AuthServiceRepository
import com.asmanmirza.schoolpen.domain.model.auth.AuthResponse
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @Author: Asman Mirza
 * @Email: asman@otmalse.com
 * @Date: 30-09-2022
 * @Time: 12:10
 */
class AuthServiceRepositoryImpl @Inject constructor(
    private val service: AuthService
) : AuthServiceRepository {
    override suspend fun login(json: JsonObject): Flow<NetworkResponse<AuthResponse>> {
        return try {
            val data = service.login(json = json)
            flow {
                emit(NetworkResponse.Success(data = data))
            }
        } catch (e: Exception) {
            flow {
                emit(NetworkResponse.Error(message = e.message ?: "Something went wrong!"))
            }
        }
    }

    override suspend fun register(json: JsonObject): Flow<NetworkResponse<AuthResponse>> {
        return try {
            val data = service.register(json = json)
            flow {
                emit(NetworkResponse.Success(data = data))
            }
        } catch (e: Exception) {
            flow {
                emit(NetworkResponse.Error(message = e.message ?: "Something went wrong!"))
            }
        }
    }

    override suspend fun generateOtp(json: JsonObject): Flow<NetworkResponse<AuthResponse>> {
        return try {
            val data = service.generateOtp(json = json)
            flow {
                emit(NetworkResponse.Success(data = data))
            }
        } catch (e: Exception) {
            flow {
                emit(NetworkResponse.Error(message = e.message ?: "Something went wrong!"))
            }
        }
    }

    override suspend fun verifyOtp(json: JsonObject): Flow<NetworkResponse<AuthResponse>> {
        return try {
            val data = service.verifyOtp(json = json)
            flow {
                emit(NetworkResponse.Success(data = data))
            }
        } catch (e: Exception) {
            flow {
                emit(NetworkResponse.Error(message = e.message ?: "Something went wrong!"))
            }
        }
    }
}