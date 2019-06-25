<img src="./art/Riddle.jpg" alt="Riddle" style="width:300px;"/>

=============================================

[![Release](https://jitpack.io/v/mofneko/Riddle.svg)](https://jitpack.io/#mofneko/Riddle)

Riddle is debugger attach tracking agent for Android.

# How to use

##### Java and Kotlin

Users of your library will need add the jitpack.io repository:

```gradle
allprojects {
 repositories {
    jcenter()
    maven { url "https://jitpack.io" }
 }
}
```

and:

```gradle
dependencies {
    compile 'com.github.mofneko:Riddle:0.1.1'
}
```

Step1. Handling DebuggerAttachDetectDelegate

```kotlin
            val detectCallback = object : RiddleCallback() {
                override fun onDetect() {
                    // Debugger Attach Detection.
                }
            }
```

Step2. Detect

```kotlin
        var riddle = Riddle()
        riddle.initialize(3000, detectCallback)
        button.setOnClickListener {
            riddle.start()
        }
```

NOTE: To improve performance, stop detection when it is not necessary.

```kotlin
            riddle.start()
```

##### C# (Unity)
Create a folder with the structure Assets/Plugins/Android and put [*.aar](https://github.com/mofneko/Riddle/blob/master/aar/) in the Android folder.

and fact Delegate.

```C# (Unity)
　　public class DetectListener : AndroidJavaProxy
    {
        public DetectListener()
            : base("com.nekolaboratory.Riddle.RiddleCallback")
        {
        }
        void onDetect() {
            // Debugger Attach Detection.
        }
    }
```

and execute Detect.

```C# (Unity)
    void Detect()
    {
        // Step1. Instantiate
        using (AndroidJavaObject Riddle = new AndroidJavaObject("com.nekolaboratory.Riddle.Riddle"))
        {
        // Step2. Detect
        Riddle.Call("initialize", 3000, new DetectListener());
        Riddle.Call("start");
        }
    }
```

To improve performance, stop detection when it is not necessary.

```C# (Unity)
        // The stop function is an instance method.
        Riddle.Call("stop");
```

# Development

```
$ git clone git@github.com:mofneko/Riddle.git
$ cd Riddle
$ ./gradlew assembleRelease
```

# License

```
MIT License

Copyright (c) 2019 Yusuke Arakawa

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
