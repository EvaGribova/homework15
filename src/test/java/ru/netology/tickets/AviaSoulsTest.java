package ru.netology.tickets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Comparator;


public class AviaSoulsTest {

    Ticket tickets1 = new Ticket("Мордер", "Шир", 1_000, 13, 17);
    Ticket tickets2 = new Ticket("Мордер", "Шир", 500, 12, 14);
    Ticket tickets3 = new Ticket("Мордер", "Шир", 7_000, 11, 13);
    Ticket tickets4 = new Ticket("Мордер", "Шир", 2_000, 15, 16);
    Ticket tickets5 = new Ticket("Мордер", "Шир", 1_000, 9, 10);
    Ticket tickets6 = new Ticket("Винтерфелл", "Королевская гавань", 2_500, 2, 19);
    Ticket tickets7 = new Ticket("Винтерфелл", "Королевская гавань", 1_000, 3, 15);

    @Test
    public void shouldCompareByPrice() {

        int expectedMore = 1;
        int actualMore = tickets1.compareTo(tickets2);
        int expectedLess = -1;
        int actualLess = tickets4.compareTo(tickets3);
        int expectedEquals = 0;
        int actualEquals = tickets5.compareTo(tickets1);

        Assertions.assertEquals(expectedMore, actualMore);
        Assertions.assertEquals(expectedLess, actualLess);
        Assertions.assertEquals(expectedEquals, actualEquals);
    }

    @Test
    public void shouldSortByPrice() {
        AviaSouls service = new AviaSouls();

        service.add(tickets1);
        service.add(tickets2);
        service.add(tickets3);
        service.add(tickets4);

        Ticket[] expected = {tickets2, tickets1, tickets4, tickets3};
        Ticket[] actual = service.search("Мордер", "Шир");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldCompareTime() {
        TicketTimeComparator comp = new TicketTimeComparator();

        int expectedMore = 1;
        int actualMore = comp.compare(tickets2, tickets5);
        int expectedLess = -1;
        int actualLess = comp.compare(tickets4, tickets1);
        int expectedEquals = 0;
        int actualEquals = comp.compare(tickets4, tickets5);
    }

    @Test
    public void shouldFindWithComparator() {
        TicketTimeComparator comp = new TicketTimeComparator();
        AviaSouls service = new AviaSouls();

        service.add(tickets1);
        service.add(tickets2);
        service.add(tickets6);

        Ticket[] expected = {tickets2, tickets1};
        Ticket[] actual = service.searchAndSortBy("Мордер", "Шир", comp);
    }

    @Test
    public void shouldFindWithoutComparatorSomeTickets() {
        AviaSouls service = new AviaSouls();

        service.add(tickets1);
        service.add(tickets2);
        service.add(tickets7);

        Ticket[] expected = {tickets1, tickets2};
        Ticket[] actual = service.search("Мордер", "Шир");
    }

    @Test
    public void shouldFindWithoutComparatorOneTicket() {
        AviaSouls service = new AviaSouls();

        service.add(tickets1);
        service.add(tickets6);
        service.add(tickets7);

        Ticket[] expected = {tickets1};
        Ticket[] actual = service.search("Мордер", "Шир");
    }

    @Test
    public void shouldNotFindTicketsWithoutComparator() {
        AviaSouls service = new AviaSouls();

        service.add(tickets6);
        service.add(tickets7);

        Ticket[] expected = {};
        Ticket[] actual = service.search("Мордер", "Шир");
    }
}
