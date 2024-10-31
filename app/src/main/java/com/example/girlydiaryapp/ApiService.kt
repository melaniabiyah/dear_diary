package com.example.girlydiaryapp

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("signup.php")
    suspend fun signUp(@Body userData: SignUpRequest): Response<SignUpResponse>

    @POST("login.php")
    suspend fun login(@Body loginData: LoginRequest): Response<LoginResponse>

}
