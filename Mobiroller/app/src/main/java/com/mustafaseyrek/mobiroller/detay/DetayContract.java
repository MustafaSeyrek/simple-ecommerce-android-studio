package com.mustafaseyrek.mobiroller.detay;

import java.util.ArrayList;

public interface DetayContract {
    interface View {
    }

    interface Presenter {
        public ArrayList<String> detayGoster(String key);
    }
}
