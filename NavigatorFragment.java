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
import android.widget.Toast;

import com.example.myapplication.model.Deliverer;
import com.example.myapplication.model.Order;
import com.example.myapplication.model.OrderAdapter;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.GeoObjectCollection;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Geometry;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.places.panorama.PanoramaService;
import com.yandex.mapkit.search.Response;
import com.yandex.mapkit.search.SearchFactory;
import com.yandex.mapkit.search.SearchManager;
import com.yandex.mapkit.search.SearchManagerType;
import com.yandex.mapkit.search.SearchOptions;
import com.yandex.mapkit.search.Session;
import com.yandex.runtime.Error;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NavigatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavigatorFragment extends Fragment {

    private MapView mapview;
    private SearchManager searchManager;
    private Session searchSession;

    public Deliverer deliverer;
    public NavigatorFragment() {
        // Required empty public constructor
    }

    public static NavigatorFragment newInstance(String param1, String param2) {
        NavigatorFragment fragment = new NavigatorFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MapKitFactory.initialize(getContext());


        var listView = (ListView)getActivity().findViewById(R.id.ordersList);
        OrderAdapter orderAdapter = new OrderAdapter(getContext(), deliverer.getAcceptedOrders().toArray(new Order[0]));
        listView.setAdapter(orderAdapter);


        mapview = (MapView)getActivity().findViewById(R.id.mapview);

        searchManager = SearchFactory.getInstance().createSearchManager(SearchManagerType.ONLINE);



        listView.setOnItemClickListener((arg0, arg1, position, arg3) -> {
            Order order = (Order) listView.getAdapter().getItem(position);
            var point = Geometry.fromPoint(new Point(59.95, 30.32));
            searchSession = searchManager.submit(order.getAddressTo(), point, new SearchOptions(),
                    new Session.SearchListener() {
                        @Override
                        public void onSearchResponse(@NonNull Response response) {
                            setPoint(response);
                        }

                        @Override
                        public void onSearchError(@NonNull Error error) {
                            Toast.makeText(getContext(),"error", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

    }

    @Override
    public void onStop() {
        mapview.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapview.onStart();
    }

    public void setPoint(Response response) {
        for (GeoObjectCollection.Item searchResult : response.getCollection().getChildren()) {
            Point resultLocation = searchResult.getObj().getGeometry().get(0).getPoint();
            if (resultLocation != null) {
                CameraPosition cameraPosition = new CameraPosition(resultLocation, 14.0f, 0.0f, 0.0f);
                mapview.getMap().move(cameraPosition);
                mapview.getMap().getMapObjects().addPlacemark(resultLocation);
                return;
            }
        }
    }
}
