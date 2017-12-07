# TimeLineView 
Android Timeline View Library demonstrate the the power of ConstraintnLayout and RecyclerView.



[![](https://jitpack.io/v/po10cio/TimeLineView.svg)](https://jitpack.io/#po10cio/TimeLineView)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://github.com/po10cio/TimeLineView/blob/master/LICENSE.md) 
 

##Showcase


<img src="sc/sc1.png" alt="ExampleMain" width="240">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img src="sc/sc2.png" alt="ExampleMain" width="240">


##Quick Setup
### 1. Include library

**Using Gradle**

Timelineview is currently available in on Jitpack so add the following line before every other thing if you have not done that already.

```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
	
Then add the following line 

``` gradle
dependencies {
    compile 'com.github.po10cio:TimeLineView:1.0.0'
}
```

**Using Maven**

Also add the following lines before adding the maven dependency

```maven
<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
Then add the dependency

``` maven
<dependency>
	    <groupId>com.github.po10cio</groupId>
	    <artifactId>TimeLineView</artifactId>
	    <version>1.0.0</version>
	</dependency>
```

###2. Usage
In your XML layout include the Timeline View as afollows:

```xml
 <me.jerryhanks.stepview.TimeLineView
        android:id="@+id/timelineView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_marginBottom="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginTop="16dp">
        
```
Then in your kotlin code, do the following:
 
 **Create a class that extends Timeline**
 
```kotlin
class MyTimeLine(status: Status, var title: String?, var content: String?) : TimeLine(status) {


    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is MyTimeLine) return false

        val that = o as MyTimeLine?

        if (if (title != null) title != that!!.title else that!!.title != null) return false
        return if (content != null) content == that.content else that.content == null
    }
    }

```
  
  
Timeline has three Statuses:

- Status.COMPLETED
- Status.UN_COMPLETED
- Status.ATTENTION

You can choose from any of the  statuses depending on the status of the itme you want to represent.

**Create an Array of your Timelines**

```kotlin
val timeLines = ArrayList<MyTimeLine>()
        timeLines.add(MyTimeLine(Status.COMPLETED, getString(R.string.title_1), getString(R.string.content_1)))
        timeLines.add(MyTimeLine(Status.UN_COMPLETED, getString(R.string.title_2), getString(R.string.content_2)))
        timeLines.add(MyTimeLine(Status.COMPLETED, getString(R.string.title_3), getString(R.string.content_3)))
        timeLines.add(MyTimeLine(Status.COMPLETED, getString(R.string.title_4), getString(R.string.content_4)))
        timeLines.add(MyTimeLine(Status.ATTENTION, getString(R.string.title_5), getString(R.string.content_5)))
        timeLines.add(MyTimeLine(Status.COMPLETED, getString(R.string.title_6), getString(R.string.content_6)))

```

**Create the IndicatorAdapter and add it to the TimelineView**

```kotlin
val adapter = IndicatorAdapter(timeLines, this, object : TimeLineViewCallback<MyTimeLine> {
            override fun onBindView(model: MyTimeLine, container: FrameLayout, position: Int): View {
                val view = layoutInflater
                        .inflate(R.layout.sample_time_line,
                                container, false)
                (view.findViewById<TextView>(R.id.tv_title)).text = model.title
                (view.findViewById<TextView>(R.id.tv_content)).text = model.content

//Note, you can set your onclick listeners
//and do other manipulations here

                return view
            }
        })
        timelineView.setIndicatorAdapter(adapter)
        adapter.swapItems(timeLines)

```
**IndicatorAdapter**
This extends RecyclerView.Adapter and exposes the following functions:

- Swaps the old items with the new items

	```kotlin
	fun swapItems(timeLines: List<T>)
	```
- Update a single item given teh index

	```kotlin
	fun updatItem(timeline: T, position: Int) 
	```
- Adds a list of items to the list
	
	```kotlin
	fun addItems(vararg items: T)
	```
	
	
## Changelog

See the [changelog](/CHANGELOG.md) file.


## License

Time is distributed under the MIT license. [See LICENSE](https://github.com/po10cio/TimeLineView/blob/master/LICENSE.md) for details.