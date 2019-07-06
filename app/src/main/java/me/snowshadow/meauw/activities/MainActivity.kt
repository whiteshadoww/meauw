package me.snowshadow.meauw.activities

import android.os.Bundle
import me.snowshadow.meauw.R
import me.snowshadow.meauw.utils.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
