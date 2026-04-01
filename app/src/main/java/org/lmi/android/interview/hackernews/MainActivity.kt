package org.lmi.android.interview.hackernews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import org.lmi.android.interview.hackernews.databinding.ActivityMainBinding

/**
 * This Activity shows the Hacker News home screen
 *
 * The Challenge:
 *
 * Recreate the Hacker News home screen in the app. The UI can be simple.
 *
 * For reference: https://news.ycombinator.com/
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var app: HackerNewsApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as HackerNewsApp

        // Choose your path
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        // Or

//        setContent {
//            Text("Hello World")
//        }
    }
}
