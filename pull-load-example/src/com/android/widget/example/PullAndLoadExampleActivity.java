package com.android.widget.example;

import java.util.Arrays;
import java.util.LinkedList;

import com.costum.android.widget.PullAndLoadListView;
import com.costum.android.widget.PullAndLoadListView.OnLoadMoreListener;
import com.costum.android.widget.PullToRefreshListView.OnRefreshListener;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

public class PullAndLoadExampleActivity extends ListActivity {

	// list with the data to show in the listview
	private LinkedList<String> mListItems;

	// The data to be displayed in the ListView
	private String[] mNames = { "Fabian", "Carlos", "Alex", "Andrea", "Karla",
			"Freddy", "Lazaro", "Hector", "Carolina", "Edwin", "Jhon",
			"Edelmira", "Andres" };

	// The data to be displayed in the ListView
	private String[] mAnimals = { "Perro", "Gato", "Oveja", "Elefante", "Pez",
			"Nicuro", "Bocachico", "Chucha", "Curie", "Raton", "Aguila",
			"Leon", "Jirafa" };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pull_and_load);

		mListItems = new LinkedList<String>();
		mListItems.addAll(Arrays.asList(mNames));

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mListItems);

		setListAdapter(adapter);

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

	}

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
}
