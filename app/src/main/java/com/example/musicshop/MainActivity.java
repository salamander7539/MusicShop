package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity<yourClickListener> extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int quantity = 0;

    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;

    HashMap goodsMap;
    String goodsName;
    double price;

    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createSpinner();
        createMap();

        userNameEditText = findViewById(R.id.editText);
    }

    void createMap(){
        goodsMap = new HashMap();
        goodsMap.put("Guitar", 500.0);
        goodsMap.put("Drums", 1500.0);
        goodsMap.put("Keyboard", 2500.0);
    }

    void createSpinner(){
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();

        spinnerArrayList.add("Guitar");
        spinnerArrayList.add("Drums");
        spinnerArrayList.add("Keyboard");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    public void increaseQuantity(View view) {
        quantity = quantity + 1;
        TextView QuantityTextView = findViewById(R.id.QuantityTextView);
        QuantityTextView.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price + " $");
    }

    public void decreaseQuantity(View view) {
        quantity = quantity - 1;
        if (quantity <= 0){
            quantity = 0;
        }
        TextView QuantityTextView = findViewById(R.id.QuantityTextView);
        QuantityTextView.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price + " $");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         goodsName = spinner.getSelectedItem().toString();
         price = (double)goodsMap.get(goodsName);
         TextView priceTextView = findViewById(R.id.priceTextView);
         priceTextView.setText("" + quantity * price + " $");

        ImageView goodsImageView = findViewById(R.id.goodsimageView);

        switch (goodsName){
            case "Guitar":
                goodsImageView.setImageResource(R.drawable.guitar);
                break;
            case "Drums":
                goodsImageView.setImageResource(R.drawable.drums);
                break;
            case "Keyboard":
                goodsImageView.setImageResource(R.drawable.keyboard);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.guitar);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void clearText (View view) {
            userNameEditText.setText("");
    }

    public void addToCard(View view) {
        Order order = new Order();

        order.userName = userNameEditText.getText().toString();

        order.goodsName = goodsName;

        order.quantity = quantity;

        order.orderPrice = quantity * price;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userNameForIntend", order.userName);
        orderIntent.putExtra("goodsNameForIntend", order.goodsName);
        orderIntent.putExtra("quantityForIntend", order.quantity);
        orderIntent.putExtra("orderPriceForIntend", order.orderPrice);
        startActivity(orderIntent);
    }
}
