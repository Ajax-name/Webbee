package Tasks;

import java.time.*;
import java.util.*;

/**
 * Необходимо разработать метод, который будет получать на вход дату,
 * а на выходе возвращать true или false в соответствии с производственным календарем
 * на май 2024 года (https://www.consultant.ru/law/ref/calendar/proizvodstvennye/2024/).
 * true - если дата является выходным (не рабочим днем)
 * false - если дата является рабочим днем.
 *
 * 2 уровень:
 * Добавить еще один метод для проверки даты и времени с учетом часового пояса
 * на принадлежность к рабочему времени в соответствии с установленным
 * графиком работы и производственным календарем.
 * Дополнительно необходимо осуществлять проверку на время и возвращать:
 * true - если дата и время НЕ являются рабочими в соответствии
 * с производственным календарем и установленным графиком работы
 * false - если дата и время являются рабочими в соответствии
 * с производственным календарем и установленным графиком работы
 * Установленный график работы: 40-часовая 5-дневная рабочая неделя
 * с двумя выходными днями (суббота и воскресенье). Рабочее время с 9.00 до 18.00
 * по Московскому времени, с 45-ти минутным перерывом на обед (может быть использован
 * в любое время в течении рабочего дня, на результат работы метода не влияет).
 */

public class DefineWeekend {

    // 1 уровень
    public static boolean isWeekend(LocalDate date) {

        if (date == null || date.getMonthValue() != 5 || date.getYear() != 2024) {
            throw new IllegalArgumentException("Дата должна быть в мае 2024 года.");
        }

        // Набор выходных дней в мае 2024 года
        final Set<Integer> WEEKENDS = Set.of(1, 4, 5, 9, 10, 11, 12, 18, 19, 25, 26);

        int dayOfMonth = date.getDayOfMonth();

        return WEEKENDS.contains(dayOfMonth);
    }

    // 2 уровень
    public static boolean isFreeDateTime(ZonedDateTime dateTime) {

        ZonedDateTime moscowTime = dateTime.withZoneSameInstant(ZoneId.of("Europe/Moscow"));

        if (isWeekend(moscowTime.toLocalDate())) {
            return true;
        }

        LocalTime time = moscowTime.toLocalTime();
        return !time.isAfter(LocalTime.of(9, 0)) || time.isAfter(LocalTime.of(18, 0));
    }

}
