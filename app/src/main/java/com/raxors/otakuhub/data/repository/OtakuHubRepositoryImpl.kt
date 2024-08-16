package com.raxors.otakuhub.data.repository

import com.raxors.otakuhub.data.api.ShikimoriOneApi
import com.raxors.otakuhub.domain.repository.OtakuHubRepository

class OtakuHubRepositoryImpl(
    private val api: ShikimoriOneApi
) : OtakuHubRepository {

}