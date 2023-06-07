package model;

import java.util.Map;

public class Ticket {
    private Seat[] seat;
    private Map<Combo, Integer> combo;

    public Ticket() {
        
    }

    public Ticket(Seat[] seat, Map<Combo, Integer> combo) {
        this.seat = seat;
        this.combo = combo;
    }  
    
    
    
}
