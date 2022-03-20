package ru.learnup.rushmanov.spring.rest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.learnup.rushmanov.spring.rest.configuration.MyConfig;
import ru.learnup.rushmanov.spring.rest.entity.TheatricalPerformance;
import ru.learnup.rushmanov.spring.rest.entity.Ticket;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        // Получаем контекст
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        // С помощью контекста создаем бин
        Communication communication = context.getBean("communication", Communication.class);

        // Получаем все представления
        List<TheatricalPerformance> allPerformance = communication.getAllPerformances();
        System.out.println(allPerformance);

        // Получаем дно представление по ID
//        TheatricalPerformance performanceById = communication.getPerformance(80);
//        System.out.println(performanceById);

        // При добавлении нового представления метод SET не используется, нужен для обновления существующего мероприятия
//        TheatricalPerformance performance = new TheatricalPerformance("Figaro Migaro","25.07.22","10+",
//                4000,2500,1500);
//        performance.setId(84);
//        communication.savePerformance(performance);

        // удаление
//        communication.deletePerformance(84);

        // Получаем билеты на представление
        List<Ticket> allPerformanceTickets = communication.getAllPerformanceTickets("Traviata");
        System.out.println(allPerformanceTickets);

        // Покупка билета
//        communication.buyTicket(1674);

        // Возврат билета
        communication.refundTicket(1672);

        List<Ticket> allPerformanceTickets2 = communication.getAllPerformanceTickets("Traviata");
        System.out.println(allPerformanceTickets2);
    }
}
