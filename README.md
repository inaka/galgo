Galgo
=====
A tiny Android library for those moments when you want your logs to be displayed on screen. 

### Abstract
<img src="http://i59.tinypic.com/eagm6h.png" align="right" style="float:right" height="200" />
Sometimes we want/need to know what's going on behind the scenes but our app is not always connected to our computer to let us check the logs. Galgo will let you display your log messages as an overlay on top of your UI.

Extremely useful for testers who want to have more insight into what's going on behind the scenes in our apps when your app misbehaves.

You can also define some basic settings such as background color, text color, text size and number of lines to display on screen so it better fits your needs.

### Usage

You can start using Galgo by either cloning this repo and adding it as a module in Android Studio or simply downloading the aar file here and importing it directly into Android Studio.

Whichever way you choose, you must add the following permission to your AndroidManifest.xml file:

`<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />`

### Code Example
```java
public class ExampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        // add some customization to the log messages
        GalgoOptions options = new GalgoOptions.Builder()
                .numberOfLines(15)
                .backgroundColor(Color.parseColor("#D9d6d6d6"))
                .textColor(Color.BLACK)
                .textSize(15)
                .build();
        Galgo.enable(this, options);

        Galgo.log("I am a log message");
    }

    public void onDestroy() {
        super.onDestroy();
        Galgo.disable(this);
    }
}
```

### Example
Here's an example of the log messages of being displayed on top of our Activity:

<img src="http://i61.tinypic.com/2qw3by0.gif" align="left" style="float:left" height="400" />

### Contact Us
For **questions** or **general comments** regarding the use of this library, please use our public
[hipchat room](https://www.hipchat.com/gpBpW3SsT).

If you find any **bugs** or have a **problem** while using this library, please [open an issue](https://github.com/inaka/galgo/issues/new) in this repo (or a pull request :)).

And you can check all of our open-source projects at [inaka.github.io](http://inaka.github.io)