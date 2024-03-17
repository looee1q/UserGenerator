package com.example.usergenerator.ui.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.usergenerator.databinding.ActivityRootBinding

class RootActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityRootBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
    }
}
