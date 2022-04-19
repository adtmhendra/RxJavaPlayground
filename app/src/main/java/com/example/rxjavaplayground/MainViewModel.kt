package com.example.rxjavaplayground

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rxjavaplayground.model.TopRating
import com.example.rxjavaplayground.model.TopRatingResponse
import com.example.rxjavaplayground.network.Api
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private val _topRatingData = MutableLiveData<List<TopRating?>>()
    val topRatingData: LiveData<List<TopRating?>> = _topRatingData

    init {
        getTopRatingData()
    }

    private fun getTopRatingData() {
        CompositeDisposable().add(
            Api.retrofitService.getListTopRating()
                .subscribeOn(Schedulers.io())               // Menentukan operasi observable akan dilakukan di thread I/O
                .observeOn(AndroidSchedulers.mainThread())  // Menentukan thread mana yang akan mulai dioperasikan observable
                .subscribe({ user ->                        // Operator subscribe adalah perekat yang menghubungkan observer ke observable
                    _topRatingData.value = user.results
                }, { error ->
                    Log.d("MainViewModel", error.message.toString())
                })
        )
    }

    // Contoh jika menggunakan observer
    // Letakkan fungsi di bawah ini ke dalam operator .subscribe() pada fungsi getTopRatingData()
    private fun getTopRatingDataObserver(): Observer<TopRatingResponse> {
        return object : Observer<TopRatingResponse> {
            override fun onSubscribe(d: Disposable) {
                // Tampilkan progress bar di sini
            }

            // Data berhasil diterima
            override fun onNext(t: TopRatingResponse) {
                val topRating = t.results
                _topRatingData.value = topRating
            }

            // Data gagal diterima (error)
            override fun onError(e: Throwable) {
                _topRatingData.value = null
            }

            // Proses pengiriman sudah selesai dilakukan
            override fun onComplete() {
                // Sembunyikan progress bar di sini
            }

        }
    }
}