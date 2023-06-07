package model;

import java.util.List;
import java.util.Map;

public class Ticket {

    private Showtime showtime;
    private List<Seat> seats;
    private Map<Combo, Integer> combos;

    public Ticket() {
    }

    public Ticket(Showtime showtime) {
        this.showtime = showtime;
    }

    public Ticket(Showtime showtime, List<Seat> seat, Map<Combo, Integer> combos) {
        this.showtime = showtime;
        this.seats = seat;
        this.combos = combos;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Map<Combo, Integer> getCombos() {
        return combos;
    }

    public void setCombos(Map<Combo, Integer> combos) {
        this.combos = combos;
    }

    public String getSeatNum() {
        StringBuilder sb = new StringBuilder("");
        if (seats != null && !seats.isEmpty()) {
            for (Seat s : seats) {
                sb.append(s.getSeatNum());
                sb.append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.toString();
    }

    public int getSeatPrice() {
        int price = 0;
        
        if (seats != null && !seats.isEmpty()) {
            for (Seat s : seats) {
                switch (s.getType()) {
                    case "N":
                        price += showtime.getBasePrice();
                        break;
                    case "V":
                        price += showtime.getBasePrice() * 1.5;
                        break;
                    case "C":
                        price += showtime.getBasePrice() * 2;
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        }
        return price;
    }
    
    public int getCombosPrice() {
        int price = 0;
        if (combos != null && !combos.isEmpty()) {
            for (Map.Entry<Combo, Integer> entry : combos.entrySet()) {
                Combo combo = entry.getKey();
                int quan = entry.getValue();
                price += combo.getPrice() * quan;
            }
        }
        return price;
    }
}
