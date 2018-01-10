package ru.omgtu.autodetector.autodetector.view;

import ru.omgtu.autodetector.autodetector.model.History.CarHistory;

/**
 * Created by denis on 23.12.17.
 */

public interface HistoryView {
    void returnHistory(CarHistory carHistory);
    void returnHistoryError(String error);
}
