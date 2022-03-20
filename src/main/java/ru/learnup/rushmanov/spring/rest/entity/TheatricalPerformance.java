package ru.learnup.rushmanov.spring.rest.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.ArrayList;
import java.util.List;



public class TheatricalPerformance {

    private int id;

    private String title;

    private String dateAndTime;

    private String ageCategory;

    private int numberOfSeats;

    private int ticketPrice1;

    private int ticketPrice2;

    private int ticketPrice3;

//    @OneToMany(
////            mappedBy = "theatricalPerformance",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY, orphanRemoval = true)
//    @JsonIgnore
//    @JoinColumn(name = "theatrical_performance_id")
    private List<Ticket> ticketList = new ArrayList<>();



    public TheatricalPerformance(String title,
                                 String dateAndTime,
                                 String ageCategory,
                                 int ticketPrice1,
                                 int ticketPrice2,
                                 int ticketPrice3) {

        this.title = title;
        this.dateAndTime = dateAndTime;
        this.ageCategory = ageCategory;
        this.ticketPrice1=ticketPrice1;
        this.ticketPrice2=ticketPrice2;
        this.ticketPrice3=ticketPrice3;
        for (int i = 0; i < 10; i ++){

            this.ticketList.add(new Ticket(title, ticketPrice1, (i +1)));
        }
        for (int i = 10; i < 20; i ++){
            this.ticketList.add(new Ticket(title, ticketPrice2, (i +1)));
        }
        for (int i =20; i < 30; i ++){
            this.ticketList.add(new Ticket(title, ticketPrice3,  (i +1)));
        }
        this.numberOfSeats = ticketList.size();
    }

    public TheatricalPerformance() {
//        for (int i = 0; i < 10; i ++){
//
//            this.ticketList.add(new Ticket(title, ticketPrice1, (i +1)));
//        }
//        for (int i = 10; i < 20; i ++){
//            this.ticketList.add(new Ticket(title, ticketPrice2, (i +1)));
//        }
//        for (int i =20; i < 30; i ++){
//            this.ticketList.add(new Ticket(title, ticketPrice3,  (i +1)));
//        }
//        this.numberOfSeats = ticketList.size();

    }


    public void setTicketList(String title,int ticketPrice1,
                              int ticketPrice2,
                              int ticketPrice3) {

            for (int i = 0; i < 10; i++) {

                this.ticketList.add(new Ticket(title, ticketPrice1, (i + 1)));
            }
            for (int i = 10; i < 20; i++) {
                this.ticketList.add(new Ticket(title, ticketPrice2, (i + 1)));
            }
            for (int i = 20; i < 30; i++) {
                this.ticketList.add(new Ticket(title, ticketPrice3, (i + 1)));
            }

        this.numberOfSeats = ticketList.size();
    }


    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getTicketPrice1() {
        return ticketPrice1;
    }

    public void setTicketPrice1(int ticketPrice1) {
        this.ticketPrice1 = ticketPrice1;
    }

    public int getTicketPrice2() {
        return ticketPrice2;
    }

    public void setTicketPrice2(int ticketPrice2) {
        this.ticketPrice2 = ticketPrice2;
    }

    public int getTicketPrice3() {
        return ticketPrice3;
    }

    public void setTicketPrice3(int ticketPrice3) {
        this.ticketPrice3 = ticketPrice3;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(String ageCategory) {
        this.ageCategory = ageCategory;
    }



    @Override
    public String toString() {
        return
                "№:" + id + " \'" +
                        title + '\'' +
                        ", Дата и время:'" + dateAndTime + "\'" +
                        ", Возрастное ограничение:'" + ageCategory + "\'";
    }

    public void buyTicket (int seatNumber, int price){
        ticketList.get((seatNumber-1)).buy(price);
    }

    public void returnTicket (int seatNumber, int price){
        ticketList.get((seatNumber-1)).ticketRefund(price);
    }


}
