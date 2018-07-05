package me.star.centerdialogfragment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import star.me.dialog.CenterDialogFragment
import star.me.dialog.CenterDialogListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener({
            val dialog = CenterDialogFragment.newInstance()
            dialog.show(supportFragmentManager, CenterDialogFragment.TAG)
        })
        button2.setOnClickListener({
            val dialog = CenterDialogListFragment.newInstance()
            dialog.show(supportFragmentManager, CenterDialogListFragment.TAG)
        })
    }
}
