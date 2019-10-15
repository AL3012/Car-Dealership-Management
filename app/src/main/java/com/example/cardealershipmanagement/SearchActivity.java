package com.example.cardealershipmanagement;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.cardealershipmanagement.Adapters.CarAdapter;
import com.example.cardealershipmanagement.Database.BuyerTable;
import com.example.cardealershipmanagement.Database.CarModel;
import com.example.cardealershipmanagement.Database.DBHelper;
import com.example.cardealershipmanagement.pdf.MyPdf;

import java.io.File;
import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.KITKAT;
import static android.os.Build.VERSION_CODES.M;

public class SearchActivity extends AppCompatActivity {

    Cursor cursor;
    private RecyclerView recyclerView;
    DBHelper dbHelper;
    ArrayList<CarModel> carTable = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SearchView searchView = findViewById(R.id.search_view);
        recyclerView = findViewById(R.id.recycler_view);
        FloatingActionButton floatingActionButton = findViewById(R.id.button);
        
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPdf();
            }
        });
        
        dbHelper = DBHelper.getInstance(this);
        cursor = dbHelper.getCar();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayout.VERTICAL, false));
                recyclerView.addItemDecoration(new DividerItemDecoration(SearchActivity.this, LinearLayout.VERTICAL));
                CarAdapter carAdapter = new CarAdapter(carTable);
                recyclerView.setAdapter(carAdapter);
                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        cursor.moveToFirst();

                        do {
                            String make = cursor.getString(1);
                            String model = cursor.getString(2);
                            String year = cursor.getString(3);
                            String color = cursor.getString(4);
                            String vin = cursor.getString(5);

                            if(make.contains(query)) {
                                carTable.add(new CarModel(make, model, year, color, vin));
                            }
                            else if(model.contains(query)) {
                                carTable.add(new CarModel(make, model, year, color, vin));
                            }
                            else if(color.contains(query)) {
                                carTable.add(new CarModel(make, model, year, color, vin));
                            }

                        } while (cursor.moveToNext());

                    } else {
                        Toast.makeText(SearchActivity.this, "Empty Table", Toast.LENGTH_LONG).show();
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void callPdf() {
        String fileName = System.currentTimeMillis() + "sales.pdf";
        new MyPdf().write(this, fileName, carTable);

        Intent pdfOpenIntent = new Intent(Intent.ACTION_VIEW);

        try {
            if (Build.VERSION.SDK_INT > KITKAT) {
                try {
                    Uri photoURI = FileProvider.getUriForFile(
                            this,
                            getApplicationContext().getPackageName() + ".my.package.name.provider",
                            new File("/sdcard/Download/" + fileName)
                    );
                    pdfOpenIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    pdfOpenIntent.setDataAndType(
                            photoURI,
                            "application/pdf"
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                pdfOpenIntent.setDataAndType(
                        Uri.parse("file://" + "/sdcard/Download/" + "/" + fileName),
                        "application/pdf"
                );

            }
            startActivity(pdfOpenIntent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }
}
