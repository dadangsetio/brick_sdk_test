package com.repairzone.bricktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.onebrick.sdk.*
import io.onebrick.sdk.model.AccessToken
import io.onebrick.sdk.model.AuthenticateUserResponse
import io.onebrick.sdk.model.AuthenticateUserResponseData
import io.onebrick.sdk.util.Environment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ICoreBrickUISDK {
    private val clientId = "c78beac4-8f2d-4fd8-b242-540f87e8123a"
    private val clientSecret = "6vBhdvOHAPujWJY3wexk558DPQNBj7"
    private val name = "dadang"
    private val url = "https://onebrick.io"

    private val username = ""
    private val password = ""
    private val instintutionId = "1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoreBrickUISDK.initializedUISDK(this, clientId, clientSecret, name, url, this, Environment.SANDBOX)
        setupOnClick()
    }

    private fun setupOnClick(){
        button_access_token.setOnClickListener { onAccessToken() }
        button_institution.setOnClickListener { onInstitution() }
        button_auth_user.setOnClickListener { onAuthUser() }
        button_list_account.setOnClickListener { onListAccount() }
        button_demo_ui.setOnClickListener { onDemoUI() }
    }

    private fun onAccessToken(){
        CoreBrickSDK.initializedSDK(clientId, clientSecret, name, url, Environment.SANDBOX)
    }

    private fun onInstitution(){
        CoreBrickSDK.requestAccessToken(object : IAccessTokenRequestResult{
            override fun error(t: Throwable?) {
                println("error Institution: ${t?.message}")
            }

            override fun success(accessToken: AccessToken?) {
                println("success Institution: ${accessToken?.message}")
            }
        })
    }

    private fun onAuthUser(){
        CoreBrickSDK.authenticateUser(
            username,
            password,
            instintutionId,
            object : IRequestResponseUserAuth{
                override fun error(t: Throwable?) {
                    println("error authUser: ${t?.message}")
                }

                override fun success(response: AuthenticateUserResponse) {
                    println("success authUser: ${response.message}")
                }
            })
    }

    private fun onListAccount(){
        CoreBrickSDK.listAccountUser()
    }

    private fun onDemoUI(){
        CoreBrickUISDK.initializedUISDK(
            this,
            clientId,
            clientSecret,
            name,
            url,
            this,
            Environment.SANDBOX
        )
    }

    override fun onTransactionSuccess(transactionResult: AuthenticateUserResponseData) {
        Log.d("BRICKMAIN", transactionResult.toString())
    }
}