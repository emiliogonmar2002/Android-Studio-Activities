package com.emilio.actividad3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    lateinit var datosFragment: DataFragment
    lateinit var perritoFragment: PetDataFragment
    lateinit var recycleViewFragment: RecycleViewFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        datosFragment = DataFragment.newInstance("Emilio", 19)
        perritoFragment = PetDataFragment.newInstance("Bearnardo", 5)
        recycleViewFragment = RecycleViewFragment()

        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.fragmentContainerView, datosFragment, TAG)
        transaction.commit()
    }

    fun swapData(view: View?) {
        val transaction = supportFragmentManager.beginTransaction()
        var fragmentoNuevo : Fragment = datosFragment
        transaction.replace(R.id.fragmentContainerView, fragmentoNuevo, TAG)
        transaction.commit()
    }

    fun swapPet(view: View?) {
        val transaction = supportFragmentManager.beginTransaction()
        var fragmentoNuevo : Fragment = perritoFragment
        transaction.replace(R.id.fragmentContainerView, fragmentoNuevo, TAG)
        transaction.commit()
    }

    fun swapRecycle(view: View?) {
        val transaction = supportFragmentManager.beginTransaction()
        var fragmentoNuevo : Fragment = recycleViewFragment
        transaction.replace(R.id.fragmentContainerView, fragmentoNuevo, TAG)
        transaction.commit()
    }

    companion object {

        private const val TAG = "fragmentito"
    }
}
