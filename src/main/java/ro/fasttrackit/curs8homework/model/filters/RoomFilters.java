package ro.fasttrackit.curs8homework.model.filters;

import lombok.Value;

@Value
public class RoomFilters {
    String number;
    Integer floor;
    String hotelName;
    Boolean tv;
    Boolean doubleBed;
}
