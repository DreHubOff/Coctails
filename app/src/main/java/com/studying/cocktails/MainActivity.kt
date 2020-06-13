package com.studying.cocktails

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.studying.cocktails.db.model.CocktailEntity
import com.studying.cocktails.navigate.OnItemClickListener
import com.studying.cocktails.network.ApiService
import io.reactivex.Flowable.just
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity(), OnItemClickListener {

    lateinit var disposable: Disposable
    lateinit var listFragment: ListFragment
    lateinit var infoFragment: InfoFragment

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            listFragment = ListFragment()
            infoFragment = InfoFragment()
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.container, listFragment)
            .add(R.id.container, infoFragment)
            .show(listFragment)
            .hide(infoFragment)
            .commit()

        disposable = (application as App).dataBase.getCocktailsDao()
            .selectAll()
            .flatMap {
                if (it.isNotEmpty()) {
                    return@flatMap just(it)
                } else {
                    var request: List<CocktailEntity>? = null
                    ApiService.simpleCocktailsList()
                        .subscribe { holder ->
                            request = holder.cocktails.map { list ->
                                CocktailEntity(list.cocktailId, list.name)
                            }
                        }
                    (application as App).dataBase.getCocktailsDao().insert(request!!)
                    return@flatMap just(request)
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showFragment(listFragment, infoFragment)
                listFragment.update(it!!)
            }, {
                it.printStackTrace()
                Toast.makeText(this, "Something want wrong", Toast.LENGTH_SHORT).show()
            })
    }

    private fun showFragment(fShow: Fragment, fHide: Fragment) {
        supportFragmentManager.beginTransaction()
            .show(fShow)
            .hide(fHide)
            .commit()
    }

    override fun onStop() {
        super.onStop()
        disposable.dispose()
    }

    override fun onItemClick(cocktailId: String) {
        disposable = ApiService.findCocktailById(cocktailId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showFragment(infoFragment, listFragment)
                infoFragment.update(it.cocktails[0])
            }, {
                Toast.makeText(this, "Something want wrong", Toast.LENGTH_SHORT).show()
            })

    }

    override fun onBackPressed() {
        if (listFragment.isVisible) {
            super.onBackPressed()
        } else {
            showFragment(listFragment, infoFragment)
        }
    }

}
