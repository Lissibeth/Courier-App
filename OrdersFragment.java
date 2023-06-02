package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Presenters.OrdersPresenter;
import com.example.myapplication.database.IDatabase;
import com.example.myapplication.model.Deliverer;
import com.example.myapplication.model.Order;
import com.example.myapplication.model.OrderAdapter;
import com.example.myapplication.database.RandomDatabase;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrdersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrdersFragment extends Fragment {

    OrdersPresenter presenter;

    public Deliverer logedDel;

    public OrdersFragment() {
    }

    public static OrdersFragment newInstance() {
        return new OrdersFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_orders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        presenter = new OrdersPresenter(logedDel, new RandomDatabase(getContext()));
        UpdateDeliverer();

        ListView listView = UpdateList();

        listView.setOnItemClickListener((arg0, arg1, position, arg3) -> {
            Order order = (Order) listView.getAdapter().getItem(position);
            if(presenter.IsSelected(order))
            {
                presenter.DeselectOrder(order);
                arg1.setBackgroundColor(Color.WHITE);
                return;
            }
            try {
                presenter.SelectedOrder(order);
                arg1.setBackgroundColor(Color.GREEN);

            } catch (Exception e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void UpdateDeliverer()
    {

        ((TextView)getActivity().findViewById(R.id.nameDel)).setText("Имя: "+ presenter.loggedDeliverer.getName());
        ((TextView)getActivity().findViewById(R.id.ordersCount)).setText("Количество заказов: "+ presenter.loggedDeliverer.getAcceptedOrders().size());
        ((TextView)getActivity().findViewById(R.id.totalIncomes)).setText("Всего доходов: "+ presenter.loggedDeliverer.GetTotalCost());
    }

    public ListView UpdateList() {
        List<Order> orderList = presenter.GetOrders();
        Order[] arr = orderList.toArray(new Order[1]);
        OrderAdapter orderAdapter = new OrderAdapter(getContext(), arr);

        UpdateDeliverer();

        ListView listView = getActivity().findViewById(R.id.list);
        listView.setAdapter(orderAdapter);
        return listView;
    }

}
