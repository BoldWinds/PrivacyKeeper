package com.lbw.privacykeeper.data.preference

interface PreferenceRepository {

    //在这里只需要特定读取是否为第一次启动信息，所以不需要key(有默认key)
    suspend fun readBoolean(key:String):Boolean?

    suspend fun saveBoolean(key: String,value:Boolean)

    suspend fun readString(key : String):String?

    suspend fun saveString(key: String,value:String)
}