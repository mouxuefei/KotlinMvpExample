# KotlinMvpExample
一个kotlin+mvp+retrofit+Rxjava的开发基础lib
##  项目介绍

* 最近对项目进行了优化,之前写的代码有点小low,网络方面也进行了优化,并且上传到了jcenter库,可以直接添加依赖进行使用了,具体的介绍请移步博客查看,当然优化的内容博客里面并没有介绍

* 博客地址[https://blog.csdn.net/villa_mou/article/details/81185903](https://blog.csdn.net/villa_mou/article/details/81185903)

**具体使用:**

  					implementation 'com.mou:mvp:1.0.0'

##   优化内容

> 1,网络请求方面进行了优化处理

具体使用如下:

       mModel?.getMainData()
                ?.bindDialogAndDisposable(mView, mModel)
                ?.onResult {

                }

或者

       mModel?.getMainData()
                ?.bindDialog(mView)
                ?.bindDisposable(mModel)
                ?.onResult {

                }
或者

     mModel?.getMainData()?.mSubscribe(mView,mModel){
            
        }

都是可行的,只是进行了拆分

其中`bindDisposable`是肯定需要的,解决内存泄漏问题,当然有的人喜欢有autodispose也是很方便的,bindDialog代表有弹窗,不写表示没有弹窗,具体看自己的情况

> 2,网络配置方面进行了优化
具体如下:
	
    object MainRetrofit : RetrofitFactory<MainApi>() {
    override fun getBaseUrl()= "http://www.baidu.com"

    override fun getHeader(builder: Request.Builder): Request.Builder {
        builder.addHeader("token","XXXXXXXXXXXXXXXXXXXXX")
        return  builder
    }

    override fun getApiService(): Class<MainApi> {
        return MainApi::class.java
    }

	}


可以配置header和baseurl

> 3,另外写了一个移除model的库,项目地址:[https://github.com/mouxuefei/KotlinVpExample](https://github.com/mouxuefei/KotlinVpExample)