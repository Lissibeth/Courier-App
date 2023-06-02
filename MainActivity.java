package com.example.myapplication;


import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.*;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;

import com.example.myapplication.database.IDatabase;
import com.example.myapplication.database.RandomDatabase;
import com.example.myapplication.model.Deliverer;
import com.yandex.mapkit.MapKitFactory;

public class MainActivity extends AppCompatActivity {


    OrdersFragment ordersFragment;
    NavigatorFragment navigatorFragment;
    Deliverer loggedDeliverer;
    IDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MapKitFactory.setApiKey("1f12526d-c202-4a1c-9c94-07acc131b468");

        setContentView(R.layout.activity_main);
        var delId = getIntent().getIntExtra("delId", -1);
        if(delId == -1)
            throw new RuntimeException("no deliverers");

        database = new RandomDatabase(getApplicationContext());

        loggedDeliverer = database.GetDeliverers().stream().filter(deliverer -> deliverer.getId() == delId).findFirst().get();

        ordersFragment = (OrdersFragment) getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        ordersFragment.logedDel = loggedDeliverer;

        navigatorFragment = new NavigatorFragment();
        navigatorFragment.deliverer = loggedDeliverer;
    }

    public void DenyClick(View view)
    {
        for(int index = 0; index < ((ViewGroup) findViewById(R.id.list)).getChildCount(); index++) {
            View nextChild = ((ViewGroup) findViewById(R.id.list)).getChildAt(index);
            nextChild.setBackgroundColor(Color.WHITE);
        }
        ordersFragment.presenter.DeselectAll();
    }

    public void AcceptClick(View view)
    {
        Toast.makeText(this, "Взяты заказы на сумму: " + ordersFragment.presenter.GetPriceOfSelected(), Toast.LENGTH_SHORT).show();

        database.AddOrdersToDeliverer(loggedDeliverer.getId(),ordersFragment.presenter.getSelectedOrders());
        ordersFragment.presenter.Accept();
        ordersFragment.UpdateList();
        ordersFragment.UpdateDeliverer();

    }

    public void SortPriceClick(View view)
    {
        ordersFragment.presenter.Sort();
        ordersFragment.UpdateList();
    }

    public void ChangeToOrders(View view)
    {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragment, ordersFragment, "OrderFragment");
        ft.commit();
    }
    public void ChangeToNavigator(View view)
    {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragment, navigatorFragment, "OrderFragment");
        ft.commit();
    }

}
