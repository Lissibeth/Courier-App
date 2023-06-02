package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;




public class OrderAdapter extends ArrayAdapter<Order> {

    private final Context context;
    private final Order[] orderList;
    public OrderAdapter(@NonNull Context context, Order[] values) {
        super(context, R.layout.list_template, values);
        this.context = context;
        orderList = values;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View template = inflater.inflate(R.layout.list_template, parent, false);
        var order = orderList[position];
        ((TextView)template.findViewById(R.id.firm)).setText(order.getFirm().getName());
        ((TextView)template.findViewById(R.id.goodType)).setText(order.getGood().getType());
        ((TextView)template.findViewById(R.id.sender)).setText(order.getAddressFrom());
        ((TextView)template.findViewById(R.id.recipient)).setText(order.getAddressTo());
        ((TextView)template.findViewById(R.id.price)).setText(order.GetPrice()+"₽");
        return template;
    }

    @NonNull
    public View getNewView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View template = inflater.inflate(R.layout.list_template, parent, false);
        var order = orderList[position];
        ((TextView)template.findViewById(R.id.firm)).setText(order.getFirm().getName());
        ((TextView)template.findViewById(R.id.goodType)).setText(order.getGood().getType());
        ((TextView)template.findViewById(R.id.sender)).setText(order.getAddressFrom());
        ((TextView)template.findViewById(R.id.recipient)).setText(order.getAddressTo());
        if (order.compareTo(order) > 0) {
            ((TextView) template.findViewById(R.id.price)).setText(order.GetPrice() + "₽");
        }
        return template;
    }
}
