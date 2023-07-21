package ua.ithillel.mealapp.ui.event;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class MealUnsetFavourite extends Event {
    public MealUnsetFavourite(Object o, EventTarget eventTarget, EventType<? extends Event> eventType) {
        super(o, eventTarget, eventType);
    }
}
