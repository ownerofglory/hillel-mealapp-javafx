package ua.ithillel.mealapp.ui.event;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class MealSetFavouriteEvent extends Event {
    public MealSetFavouriteEvent(Object o, EventTarget eventTarget, EventType<? extends Event> eventType) {
        super(o, eventTarget, eventType);
    }
}
