package com.example.networkcoroutines.network.managers

import com.example.networkcoroutines.network.apis.TraktApi
import com.uwetrottmann.trakt5.TraktV2

class TraktV2Manager(apiKey: String) : TraktV2(apiKey){
     val api = retrofit().create(TraktApi::class.java)
}