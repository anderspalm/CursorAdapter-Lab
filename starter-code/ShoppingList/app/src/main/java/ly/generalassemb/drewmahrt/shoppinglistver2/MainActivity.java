package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import ly.generalassemb.drewmahrt.shoppinglistver2.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ignore the two lines below, they are for setup
        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        listView = (ListView) findViewById(R.id.shopping_list_view);

        Cursor cursor = DatabaseHelper.getInstance(MainActivity.this).getItemList();

        CursorAdapter customadapter = new CursorAdapter(MainActivity.this, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {

            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.item_list_view, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView name = (TextView) view.findViewById(R.id.my_name);
                TextView description = (TextView) view.findViewById(R.id.my_desc);
                TextView price = (TextView) view.findViewById(R.id.my_price);

                name.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME)));
                description.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DESC)));
                String temp = "$" + cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PRICE));
                price.setText(temp);

            }
        };

        listView.setAdapter(customadapter);
    }
}
