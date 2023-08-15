import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectapi.repository.TriviaRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryViewModel @Inject constructor(private val repository: TriviaRepository) : ViewModel() {

    private val _categoriesLiveData = MutableLiveData<List<String>>()
    val categoriesLiveData: LiveData<List<String>> get() = _categoriesLiveData
    val categories: LiveData<List<String>> = TriviaRepository().fetchAllCategories()

    init {
        fetchCategoriesFromAPI()
    }

    fun getCategories() {
        viewModelScope.launch {
            try {
                val result = repository.fetchCategories()
                if (result.isSuccessful) {
                    categories.postValue(result.body()?.categories)
                } else {
                    errorMessage.postValue(result.message())
                }
            } catch (e: Exception) {
                errorMessage.postValue(e.localizedMessage)
            }
        }
    }

    private fun fetchCategoriesFromAPI() {
        viewModelScope.launch {
            val categories = repository.fetchAllCategories().map { it.name }
            _categoriesLiveData.postValue(categories)
        }
    }
}
