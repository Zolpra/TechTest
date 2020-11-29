package com.example.techtestcb;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button addDLC_Button;
    EditText newGTIN_Input;
    EditText dlc_Input;
    ListView product_List;

    // beginning data set
    Product product1 = new Product("3341874503874", "101220");
    Product product2 = new Product("54739846372938", "101220");
    Product product3 = new Product("52638474535283", "011220");
    Product product4 = new Product("87635284162462", "141220");
    Product product5 = new Product("78258645218654", "041220");
    Product product6 = new Product("39887154785217", "171220");
    Product product7 = new Product("45612736912632", "191220");
    Product product8 = new Product("23657216367213", "221220");
    Product product9 = new Product("50002135423742", "131220");
    Product product10 = new Product("30042357276423", "311220");

    List<Product> stockProduct = new ArrayList<Product>();
    Product[] basicStockProduct = {
            product1,
            product2,
            product3,
            product4,
            product5,
            product6,
            product7,
            product8,
            product9,
            product10,
    };
    List<String> products = new ArrayList<String>();
    String[] basicList = {
            product1.productFullInfo(),
            product2.productFullInfo(),
            product3.productFullInfo(),
            product4.productFullInfo(),
            product5.productFullInfo(),
            product6.productFullInfo(),
            product7.productFullInfo(),
            product8.productFullInfo(),
            product9.productFullInfo(),
            product10.productFullInfo(),
    };
    ArrayAdapter productListAdapter;
    //


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addDLC_Button = findViewById(R.id.addDLC_Button);
        newGTIN_Input = findViewById(R.id.newGTIN_Input);
        product_List = findViewById(R.id.product_List);
        dlc_Input = findViewById(R.id.dlc_Input);

        products = new ArrayList<String>(Arrays.asList(basicList));
        stockProduct = new ArrayList<Product>(Arrays.asList(basicStockProduct));
        productListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products);
        product_List.setAdapter(productListAdapter);
        products.sort(Comparator.comparing(products -> products.substring(5, 14)));
        products.sort(Comparator.comparing(products -> products.substring(8, 10)));
        products.sort(Comparator.comparing(products -> products.substring(11, 13)));
        productListAdapter.notifyDataSetChanged();

        addDLC_Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if( dlc_Input.getText().toString().length() != 8 ){
                    dlc_Input.setError("Mauvais format de Date");
                }
                else {
                    String newGTIN = newGTIN_Input.getText().toString();
                    String newDLC = dlc_Input.getText().toString();
                    newDLC = newDLC.substring(0, 2) + newDLC.substring(3, 5) + newDLC.substring(6, 8);
                    if (findEqualGTIN(stockProduct ,newGTIN) == true){
                        Product tempProduct = findObject(stockProduct ,newGTIN);
                        tempProduct.setDLC(newDLC);
                        tempProduct.setLongGTIN(tempProduct.getGTIN() + tempProduct.getDLC());
                        products.removeIf(row -> row.contains(newGTIN));
                        products.add(tempProduct.productFullInfo());
                    }
                    else {
                        Product newProduct = new Product(newGTIN, newDLC);
                        products.add(newProduct.productFullInfo());
                        stockProduct.add(newProduct);
                    }
                    products.sort(Comparator.comparing(products -> products.substring(5, 14)));
                    products.sort(Comparator.comparing(products -> products.substring(8, 10)));
                    products.sort(Comparator.comparing(products -> products.substring(11, 13)));

                    productListAdapter.notifyDataSetChanged();
                }
            }

            public Boolean findEqualGTIN(List<Product> list, String GTIN){
                return list.stream().filter(object -> object.getGTIN().equals(GTIN)).findFirst().isPresent();
            };

            public Product findObject(List<Product> list, String GTIN) {
                return list.stream().filter(object -> object.getGTIN().equals(GTIN)).findFirst().get();
            };
        });
    }
}