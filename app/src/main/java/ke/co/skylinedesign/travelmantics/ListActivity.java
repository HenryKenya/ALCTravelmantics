package ke.co.skylinedesign.travelmantics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

	ArrayList<TravelDeal> deals;

	private FirebaseDatabase mFirebaseDatabase;

	private DatabaseReference mDatabaseRef;

	private ChildEventListener mChildEventListener;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		FirebaseUtil.openFbReference("traveldeals", this);

		RecyclerView rvDeals = findViewById(R.id.rv_deals);

		final DealAdapter adapter = new DealAdapter();

		rvDeals.setAdapter(adapter);

		LinearLayoutManager mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

		rvDeals.setLayoutManager(mLayoutManager);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();

		inflater.inflate(R.menu.list_activity_menu, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.item_insert_menu:
				Intent intent = new Intent(this, DealActivity.class);
				startActivity(intent);
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		super.onPause();
		FirebaseUtil.detachListener();
	}

	@Override
	protected void onResume() {
		super.onResume();
		FirebaseUtil.attachListener();
	}
}
