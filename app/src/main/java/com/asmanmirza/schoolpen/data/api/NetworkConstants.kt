package com.asmanmirza.schoolpen.data.api

/**
 * @Author: Asman Mirza
 * @Email: asman@otmalse.com
 * @Date: 28-09-2022
 * @Time: 21:12
 */

object NetworkConstants {
    object Api {
        private const val API = "api/v1"
        const val BASE_URL = "http://aie-backend.herokuapp.com/"

        object EndPoints {
            object Auth {
                const val SIGNIN = "$API/authenticate"
                const val SIGNUP = "$API/signUp"
                const val GENERATE_OTP = "$API/generateOtp"
                const val VERIFY_OTP = "$API/verifyOtp"
            }
        }
    }
}