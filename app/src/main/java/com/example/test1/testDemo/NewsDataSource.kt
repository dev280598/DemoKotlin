import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.test1.testDemo.Data
import com.example.test1.testDemo.NetworkService

import com.example.test1.testDemo.State
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class NewsDataSource(
        private val networkService: NetworkService,
        private val compositeDisposable: CompositeDisposable)
    : PageKeyedDataSource<Int, Data>() {

    var state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Data>) {
        updateState(State.LOADING)
        compositeDisposable.add(
                networkService.getNews(1, params.requestedLoadSize)
                        .subscribe(
                                { response ->
                                    updateState(State.DONE)
                                    callback.onResult(response.news,
                                            null,
                                            2
                                    )
                                },
                                {
                                    updateState(State.ERROR)
                                    setRetry(Action  { loadInitial(params, callback) })
                                }
                        )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Data>) {
        updateState(State.LOADING)
        compositeDisposable.add(
                networkService.getNews(params.key, params.requestedLoadSize)
                        .subscribe(
                                { response ->
                                    updateState(State.DONE)
                                    callback.onResult(response.news,
                                            params.key + 1
                                    )
                                },
                                {
                                    updateState(State.ERROR)
                                    setRetry(Action { loadAfter(params, callback) })
                                }
                        )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Data>) {
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe())
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }

}