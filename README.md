ReactiveX Kotlin Extensions
============

Kotlin extensions for convenient using rx library.

Download
--------
[ ![Download](https://api.bintray.com/packages/idapgroup/kotlin/lifecycle-ktx/images/download.svg?version=1.0.0) ](https://bintray.com/idapgroup/kotlin/lifecycle-ktx/1.0.0/link)

Add repository to your root `build.gradle`

```groovy
repositories {
    jcenter()
}
```


```groovy
dependencies {
  implementation 'com.idapgroup:rx-ktx:latest-version'
}
```
Extensions
-------------
Each Rx type(Observable, Single, Completable, Maybe, Flowable) has these extensions:

* Subscribe On:
**subscribeIo()** -> subscribeOn(Schedulers.io())
**subscribeComputation()** -> subscribeOn(Schedulers.computation())
**subscribeMainThread()** -> subscribeOn(AndroidSchedulers.mainThread())

* Observe On: 
**io()** -> observeOn(Schedulers.io())
**computation()** -> observeOn(Schedulers.computation())
**mainThread()** -> observeOn(AndroidSchedulers.mainThread())

* Logging all rx events (such as onSubscribe, onError etc.) for debug mode based on [Timber][1] logs:

```kotlin
    Observable.just(1)
        .debug()
        .subscribe()
```

Logs:
~~~
.onSubscribe() thread: main
 [1]-> value: 1, passed time: 3 ms, thread: main
.onComplete() passed time: 5 ms, thread: main
~~~

Each OnNext() event has counter that helps to track execution steps.
~~~
 [1]-> ...
 [2]-> ...
 [3]-> ...
 ...
~~~

* disposeOn(LifecycleOwner, Lifecycle.Event) method that helps auto control of your disposable by LifecycleOwner lifecycle.

```kotlin
class ExampleActivity: AppCompatActivity() {

    fun doWork() {
        someBigWork()
            .subscribe()
            .disposeOn(this, Lifecycle.Event.ON_DESTROY)
    }
        
}
```

For more convenience added all lifecycle events methods:

**disposeOnPause(LifecycleOwner)**
**disposeOnStop(LifecycleOwner)**
**disposeOnDestroy(LifecycleOwner)**

* Top level functions for converting com.google.android.gms.tasks.Task to rx type: 
```kotlin    
    completableOf { task }
    singleOf { task }
    maybeOf { task }
```

AutoDisposable
------------

Interface that helps dispose/cancel your Disposable by events that you define. 
Already implemented fo ViewModel
```kotlin    
open class RxViewModel : ViewModel(), AutoDisposable by AutoDisposableImpl() {

    override fun onCleared() {
        dispose()
        super.onCleared()
    }

}
```

Using example:
```kotlin    
class TestRxViewModel : RxViewModel() {
    val testDisposable =
        Observable.never<Int>()
            .subscribe()
            .autoDispose()
}
```




 [1]: https://raw.githubusercontent.com/JakeWharton/timber/