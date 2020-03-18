package pro.sisit.javacourse;

import pro.sisit.javacourse.optimal.DeliveryTask;
import pro.sisit.javacourse.optimal.Route;
import pro.sisit.javacourse.optimal.Transport;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;

public class PathFinder {

    /**
     * Возвращает оптимальный транспорт для переданной задачи.
     * Если deliveryTask равна null, то оптимальеый транспорт тоже равен null.
     * Если список transports равен null, то оптимальеый транспорт тоже равен null.
     */
    public Transport getOptimalTransport(DeliveryTask deliveryTask, List<Transport> transports) {

        if(deliveryTask == null || transports == null)
            return null;

        Optional<Transport> optimalTransport =
                transports.stream().filter(haveSameVolume(deliveryTask).and(haveSameRouteType(deliveryTask))).
                min(Comparator.comparing(transport -> transport.getPrice().multiply(
                        getRouteLength(transport, deliveryTask))));

        return optimalTransport.orElse(null);
    }

    public static BigDecimal getRouteLength(Transport transport, DeliveryTask deliveryTask)
    {
        return deliveryTask.getRoutes().stream().filter(isEqualRoute(transport)).findFirst().get().getLength();
    }

    public static Predicate<Transport> haveSameVolume(DeliveryTask deliveryTask)
    {
        return transport -> transport.getVolume().compareTo(deliveryTask.getVolume()) >= 0;
    }

    public static Predicate<Transport> haveSameRouteType(DeliveryTask deliveryTask)
    {
        return transport -> (deliveryTask.getRoutes().stream().anyMatch(isEqualRoute(transport)));
    }

    public static Predicate<Route> isEqualRoute(Transport transport)
    {
        return route -> route.getType().equals(transport.getType());
    }

}
