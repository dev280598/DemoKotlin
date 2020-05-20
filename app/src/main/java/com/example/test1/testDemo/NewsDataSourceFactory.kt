import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.test1.testDemo.Data
import com.example.test1.testDemo.NetworkService
import io.reactivex.disposables.CompositeDisposable

class NewsDataSourceFactory(
        private val compositeDisposable: CompositeDisposable,
        private val networkService: NetworkService)
    : DataSource.Factory<Int, Data>() {

    val newsDataSourceLiveData = MutableLiveData<NewsDataSource>()

    override fun create(): DataSource<Int, Data> {
        val newsDataSource = NewsDataSource(networkService, compositeDisposable)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}