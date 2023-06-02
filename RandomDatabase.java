package com.example.myapplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomDatabase implements IDatabase {

    private List<Firm> firms = new ArrayList<Firm>(Arrays.asList(
            new Firm("Evil inn", "рандомный адрес"),
            new Firm("фирма 1243", "рандомный адрес2"),
            new Firm("Сдек", "рандомный адрес3"),
            new Firm("Еще какая-то фирма", "не рандомный адрес"),
            new Firm("Не фирма", "улица пушкина дом колотушкина")
    ));

    private List<String> addressList = new ArrayList<>(Arrays.asList(
            "Россия, г. Нефтекамск, Солнечный пер., д. 12 кв.53",
            "Россия, г. Ноябрьск, Ленина ул., д. 2 кв.170",
            "Россия, г. Батайск, Зеленая ул., д. 12 кв.177",
            "Россия, г. Железногорск, Южная ул., д. 20 кв.29",
            "Россия, г. Каспийск, Луговой пер., д. 5 кв.28",
            "Россия, г. Березники, Приозерная ул., д. 1 кв.105",
            "Россия, г. Новочебоксарск, Центральная ул., д. 4 кв.18",
            "Россия, г. Старый Оскол, Ленина ул., д. 7 кв.94",
            "Россия, г. Одинцово, Парковая ул., д. 15 кв.79",
            "Россия, г. Ростов-на-Дону, Речной пер., д. 8 кв.1",
            "Россия, г. Бийск, Заречная ул., д. 23 кв.178"
    ));

    @Override
    public List<Order> GetOrders() {
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(10) + 5; i++) {
            var add1 = addressList.get(new Random().nextInt(addressList.size()));
            var add2 = addressList.get(new Random().nextInt(addressList.size()));
            var firm = firms.get(new Random().nextInt(firms.size()));

            Package pac = switch (new Random().nextInt(3)) {
                case 0 -> new SimplePackage(new Random(0).nextBoolean());
                case 1 -> new Documents("имя" + new Random().nextInt(2000));
                default -> new BigPackage(new Random().nextBoolean());
            };

            orders.add(new Order(firm, pac, add1, add2, new Random().nextInt(4000) + 500));
        }
        return orders;
    }

    @Override
    public List<Firm> GetFirms() {
        return new ArrayList<>(firms);
    }
}
