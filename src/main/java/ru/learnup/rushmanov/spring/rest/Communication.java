package ru.learnup.rushmanov.spring.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.learnup.rushmanov.spring.rest.entity.TheatricalPerformance;
import ru.learnup.rushmanov.spring.rest.entity.Ticket;

import java.util.List;

@Component
public class Communication {

    @Autowired
    private RestTemplate restTemplate;
    private final String URL="http://localhost:8080/spring_course_rest/api/performances";


// отправляем HTTP запрос и получаем ответ в responseEntity.
// restTemplate - вспомогательный класс spring. Нужен для совершения Http запросов из клиента.
// ParameterizedTypeReference - вспомогательный класс для передачи дженерик типа.

    public List<TheatricalPerformance> getAllPerformances(){
        ResponseEntity<List<TheatricalPerformance>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<TheatricalPerformance>>() {});
        List<TheatricalPerformance> allPerformance = responseEntity.getBody();
        return allPerformance;
    }

    public TheatricalPerformance getPerformance(int id){
        TheatricalPerformance performance = restTemplate.getForObject(URL + "/" + id, TheatricalPerformance.class);
        return performance;
    }

    // В ResponseEntity указываем String, так как нам будет возвращаться в ответе JSON в виде текста
    public void savePerformance (TheatricalPerformance performance){
        int id = performance.getId();
        if(id==0){
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, performance, String.class);
            System.out.println("New Performance was added in DB");
            System.out.println(responseEntity.getBody());
        } else {
            restTemplate.put(URL, performance);
            System.out.println("Performance with ID "+ id + " was updated successfully");
        }
    }

    public void deletePerformance(int id){
        restTemplate.delete(URL + "/" + id);
        System.out.println("The performance with ID " + id + " was deleted from DB");
    }

    public List<Ticket> getAllPerformanceTickets(String title){
        ResponseEntity<List<Ticket>> responseEntity =
                restTemplate.exchange(URL + "/" + "Ticket" + "/" + title, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Ticket>>() {});
        List<Ticket> allPerformanceTickets = responseEntity.getBody();
        return allPerformanceTickets;
    }

    public Ticket buyTicket (int id){
        Ticket ticket = restTemplate.getForObject(URL + "/Ticket/Buy/" + id, Ticket.class);
        System.out.println("The ticket with ID: " + id +" was purchased successfully " + ticket);
        return ticket;
    }

    public Ticket refundTicket (int id){
        Ticket ticket = restTemplate.getForObject(URL + "/Ticket/Refund/" + id, Ticket.class);
        System.out.println("The ticket with ID: " + id +" was return successfully");
        return ticket;
    }




}
