The first thing that i have to say is render thanks to johannilsson because
all the part of pull to refresh listview is based in the code of his repository 
at <https://github.com/johannilsson/android-pulltorefresh>.

The target of this project is help other to apply the  interaction pattern 
pull to refresh and load more on an android listview.  

#Costum Load more listview for android
![Screenshot](https://github.com/shontauro/android-pulltorefresh-and-loadmore/raw/master/loadmore.png)

More information about load more interaction pattern 
at <http://www.androidpatterns.com/uap_pattern/dynamic-loading-of-a-list>

#Costum Pull to refresh listview for android
![Screenshot](https://github.com/shontauro/android-pulltorefresh-and-loadmore/raw/master/pulltorefresh.png)

More information about pull to refresh interaction pattern 
at <http://www.androidpatterns.com/uap_pattern/pull-to-refresh-2>

Repository at <https://github.com/shontauro/android-pulltorefresh-and-loadmore>.

## Usage

### Layout for loadmore listview

``` xml
    <!-- We have to indicate that the listview is now a LoadMoreListView -->

    <com.costum.android.widget.LoadMoreListView
        android:id="@+id/android:list"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent" />
```
### Activity to LoadMoreListView


``` java
// set a listener to be invoked when the list reaches the end
		((LoadMoreListView) getListView())
				.setOnLoadMoreListener(new OnLoadMoreListener() {
					public void onLoadMore() {
						// Do the work to load more items at the end of list here
						new LoadDataTask().execute();
					}
				});


private class LoadDataTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {

			if (isCancelled()) {
				return null;
			}

			// Simulates a background task
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			for (int i = 0; i < mNames.length; i++)
				mListItems.add(mNames[i]);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			mListItems.add("Added after load more");

			// We need notify the adapter that the data have been changed
			((BaseAdapter) getListAdapter()).notifyDataSetChanged();

			// Call onLoadMoreComplete when the LoadMore task, has finished
			((LoadMoreListView) getListView()).onLoadMoreComplete();

			super.onPostExecute(result);
		}

		@Override
		protected void onCancelled() {
			// Notify the loading more operation has finished
			((LoadMoreListView) getListView()).onLoadMoreComplete();
		}
	}
``` 


### Layout for pullandload listview

``` xml
      <!-- We have to indicate that the listview is now a PullAndLoadListView -->

    <com.costum.android.widget.PullAndLoadListView
        android:id="@+id/android:list"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent" />
```
### Activity to PullAndLoadListView

Here we have to pass two listeners one to pull operation and the other to load operation

``` java
// Set a listener to be invoked when the list should be refreshed.
		((PullAndLoadListView) getListView())
				.setOnRefreshListener(new OnRefreshListener() {

					public void onRefresh() {
						// Do work to refresh the list here.
						new PullToRefreshDataTask().execute();
					}
				});

		// set a listener to be invoked when the list reaches the end
		((PullAndLoadListView) getListView())
				.setOnLoadMoreListener(new OnLoadMoreListener() {
					
					public void onLoadMore() {
						// Do the work to load more items at the end of list
						// here
						new LoadMoreDataTask().execute();
					}
				});


private class LoadMoreDataTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {

			if (isCancelled()) {
				return null;
			}

			// Simulates a background task
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			for (int i = 0; i < mNames.length; i++)
				mListItems.add(mNames[i]);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			mListItems.add("Added after load more");

			// We need notify the adapter that the data have been changed
			((BaseAdapter) getListAdapter()).notifyDataSetChanged();

			// Call onLoadMoreComplete when the LoadMore task, has finished
			((PullAndLoadListView) getListView()).onLoadMoreComplete();

			super.onPostExecute(result);
		}

		@Override
		protected void onCancelled() {
			// Notify the loading more operation has finished
			((PullAndLoadListView) getListView()).onLoadMoreComplete();
		}
	}

private class PullToRefreshDataTask extends AsyncTask<Void, Void, Void> {
	
		@Override
		protected Void doInBackground(Void... params) {

			if (isCancelled()) {
				return null;
			}

			// Simulates a background task
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			for (int i = 0; i < mAnimals.length; i++)
				mListItems.addFirst(mAnimals[i]);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			mListItems.addFirst("Added after pull to refresh");

			// We need notify the adapter that the data have been changed
			((BaseAdapter) getListAdapter()).notifyDataSetChanged();

			// Call onLoadMoreComplete when the LoadMore task, has finished
			((PullAndLoadListView) getListView()).onRefreshComplete();

			super.onPostExecute(result);
		}

		@Override
		protected void onCancelled() {
			// Notify the loading more operation has finished
			((PullAndLoadListView) getListView()).onLoadMoreComplete();
		}
	}
``` 


### License
Licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

Copyright (c) 2012 [Fabian Leon](http://yelamablog.blogspot.com/)


