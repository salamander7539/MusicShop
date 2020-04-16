package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    String[] addresses = {"salamander7539@gmail.com"};
    String subject = "Order from Music Shop";
    String emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Your Order");
        setContentView(R.layout.activity_order);

        Intent receivedOrderIntent = getIntent();
        String userName = receivedOrderIntent.getStringExtra("userNameForIntend");
        String goodsName = receivedOrderIntent.getStringExtra("goodsNameForIntend");
        int quantity = receivedOrderIntent.getIntExtra("quantityForIntend", 0);
        double orderPrice = receivedOrderIntent.getDoubleExtra("orderPriceForIntend", 0.0);

        emailText = "Buyer: " + userName + "\n\nGood: " +  goodsName + "\n\nQuantity: " + quantity + "x" + "\n\nOrder Price: " + orderPrice;

        TextView orderTextView = findViewById(R.id.orderTextView);
        orderTextView.setText(emailText);
    }

    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
