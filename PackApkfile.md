### React Native 打包生成apk文件

###  在上一篇文章中我记录了Android原生接入React Native的一些知识点，那么接入开发完毕之后就需要打包生成正式apk文件，下面就开始介绍如何打包

> 先说明一下，平时开发RN的时候，首先需要`react-native start` 开启服务，然后通过`react-native run-android` 就安装到手机或者模拟器了，然后也能运行。但是这里打的测试包其实不包含js代码，而是通过连接js Server来显示的。但是正式打包肯定不能这么干，所以相比较而言RN这种打包apk与我们平时Android打包，区别就在于RN要把js那部分打包进去。
然后网上有很多关于RN打包生成apk的文章，说的也很好，比如[【React Native开发】React Native进行签名打包成Apk(5)](http://blog.csdn.net/developer_jiangqq/article/details/50525976/)这篇文章。但是呢，其实这些文章记录的跟我的情况还不一样，我的是Android原生接入RN，大部分文章写的是RN工程，然后打包android的那部分生成apk，
所以，整个工程的结构都不一样，因此我也是尝试很多次才打包成功。。

### 步骤如下：

  * **首先第一步需要一个签名文件，这个一般都有吧，没有的话可以通过android studio生成，比较简单。**
  * **将签名文件复制到工程的app moudle目录下**
  * **需要对Android工程进行配置**


### 工程配置如下：

  * 修改项目中gradle.properties文件:

```
MYAPP_RELEASE_STORE_FILE=youdo.jks
MYAPP_RELEASE_KEY_ALIAS=qndroid
MYAPP_RELEASE_STORE_PASSWORD=rzq123456
MYAPP_RELEASE_KEY_PASSWORD=rzq123456
```

> 说明一下，填写的时候将签名文件的名称、密码、别名、别名密码都替换一下，应该没啥问题

  * 在moudle的gradle的文件下，进行如下配置：

```

    // 配置签名文件
    signingConfigs {
        release {
            storeFile file("youdo.jks")
            storePassword "rzq123456"
            keyAlias "qndroid"
            keyPassword "rzq123456"
        }
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'

            signingConfig signingConfigs.release

        }
    }

```

  * 生成签名包 由于是Android接入RN的，所以不存在react.gradle文件，接下来需要在main目录下生成assets文件，然后生成index.android.bundle文件放在这个文件夹下，在android studio的Terminal进行以下操作：

> step 1: mkdir -p  app/src/main/assets   (在main目录下生成assets)

> step 2: curl -k "http://localhost:8081/index.android.bundle" >app/src/main/assets/index.android.bundle (生成index.android.bundle)

> step 3：./gradlew assembleRelease  （打包生成apk文件，注意以上操作均在 Android工程的根目录 ，如果命令行打包卡住，用as的图形化界面打包试试）

### 到此为止，Android接入RN 项目的apk文件就生成了， 但是具体项目中其实还是需要多渠道打包的，可以统计各个渠道app的下载量，一般用友盟统计的比较多吧，下面介绍一些多渠道打包的配置：

  * 首先在AndroidManifest.xml的application下添加  <meta-data android:name="UMENG_CHANNEL" android:value="${UMENG_CHANNEL_VALUE}" />

  * 接着在app的build.gradle文件下添加：

```
   // 多渠道设置
       productFlavors {
           kuan {}
           xiaomi {}
           qh360 {}
           baidu {}
           wandoujia {}
       }

       productFlavors.all {
           flavor -> flavor.manifestPlaceholders = [UMENG_CHANNEL_VALUE: name]
       }

       buildTypes {
           release {
               minifyEnabled false
               proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'

               signingConfig signingConfigs.release

           }
       }
```

  * 配置完成之后，就需要打包了，可以重复上面的那个打包步骤，命令行或者as的图形化界面打包都是可以的



### 总结说明

> 对比下来，Android接入RN之后，跟以前原生的Android工程打包唯一的不同点就是需要将js打包进来，也就是在打包之前需要额外的生成index.android.bundle文件。官网以及其他的文章介绍中生成这个文件的命令不同，比如其他地方生成这个文件的命令是`curl -k "http://localhost:8081/index.android.bundle" >android/app/src/main/assets/index.android.bundle`, 我们这个工程生成这个文件的命令是`curl -k "http://localhost:8081/index.android.bundle" >android/app/src/main/assets/index.android.bundle`,那是因为我们这个项目结构跟他们的不一样，这个是Android接入RN，其实本质都是一样的，只要找对文件的位置就ok了。
