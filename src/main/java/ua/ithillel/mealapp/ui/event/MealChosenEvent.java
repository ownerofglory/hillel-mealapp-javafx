package ua.ithillel.mealapp.ui.event;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class MealChosenEvent extends Event {
    public MealChosenEvent(Object o, EventTarget eventTarget, EventType<? extends Event> eventType) {
        super(o, eventTarget, eventType);

    }
}
