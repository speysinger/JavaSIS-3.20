package pro.sisit.javacourse;

import pro.sisit.javacourse.inverse.BigDecimalRange;
import pro.sisit.javacourse.inverse.InverseDeliveryTask;
import pro.sisit.javacourse.inverse.Solution;
import pro.sisit.javacourse.optimal.DeliveryTask;
import pro.sisit.javacourse.optimal.Route;
import pro.sisit.javacourse.optimal.Transport;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InversePathFinder {

    /**
     * Принимает на вход InverseDeliveryTask task - обратную задачу доставки груза.
     * Мы должны по переданному в ней списку возможных задач, списку доступного транспорта,
     * а также диапазону цены определить что за задачи доставки груза могли быть решены.
     * Возвращает список решений задачи доставки груза (Solution),
     * удовлетворяющий параметру переданному параметру task:
     * 1. Транспорт должен быть доступен для решения данной задачи
     * 2. Стоимость решения задачи должна входить в диапазон цены пераметра task
     * 3. Также возвращаемый список должен быть осортирован по двум значениям:
     * - сначала по итоговой стоимости решения задачи - по убыванию
     * - затем, если цены одинаковы - по наименованию задачи доставки по алфавиту - по возрастанию
     * Если task равна null, то функция должна вернуть пустой список доступных решений.
     * Если внутри параметра task данные по возможным задачам, доступному транспорту либо по итоговой цене равны null,
     * то функция должна вернуть пустой список доступных решений.
     */
    public List<Solution> getAllSolutions(InverseDeliveryTask task) {

        Optional<InverseDeliveryTask> inverseTask = Optional.ofNullable(task);

        if (!inverseTask.isPresent()) {
            return new ArrayList<>();
        }

        Optional<List<Transport>> transports = Optional.ofNullable(task.getTransports());
        Optional<List<DeliveryTask>> tasks = Optional.ofNullable(task.getTasks());
        Optional<BigDecimalRange> priceRange = Optional.ofNullable(task.getPriceRange());

        if (!transports.isPresent() | !tasks.isPresent() | !priceRange.isPresent()) {
            return new ArrayList<>();
        }

        Optional<List<Solution>> solutions = tasks.get()
                .stream()
                .map(deliveryTask -> deliveryTask
                        .getRoutes().stream()
                        .map(route -> transports
                                .get()
                                .stream()
                                .filter(sameRouteType(route)
                                        .and(isPriceInRange(route, priceRange.get())
                                                .and(haveSameVolume(deliveryTask)
                                                )
                                        )
                                )
                                .map(transport -> getSolution(deliveryTask, transport, route))
                                .reduce(Optional.of(new ArrayList<>()), accumulator, combiner)
                        )
                        .reduce(Optional.of(new ArrayList<>()), combiner)
                ).reduce(Optional.of(new ArrayList<>()), combiner);

        return solutions.orElse(new ArrayList<>())
                .stream()
                .sorted(Comparator
                        .comparing(Solution::getPrice).reversed()
                        .thenComparing(solution -> solution
                                .getDeliveryTask()
                                .getName()))
                .collect(Collectors.toList());
    }


    public static Predicate<Transport> haveSameVolume(DeliveryTask deliveryTask) {
        return transport -> transport.getVolume().compareTo(deliveryTask.getVolume()) >= 0;
    }

    BiFunction<Optional<List<Solution>>, Solution, Optional<List<Solution>>> accumulator = (solutionsList, solution) -> {
        solutionsList.ifPresent(solutions -> solutions.add(solution));
        return solutionsList;
    };

    BinaryOperator<Optional<List<Solution>>> combiner = (baseSolutions, addedSolutions) -> {
        baseSolutions.ifPresent(solutions -> solutions.addAll(addedSolutions.orElse(Collections.emptyList())));
        return baseSolutions;
    };

    public static Solution getSolution(DeliveryTask deliveryTask, Transport transport, Route route) {
        return new Solution(deliveryTask, transport, getDeliveryPrice(transport, route));
    }

    public static Predicate<Transport> sameRouteType(Route route) {
        return transport -> transport.getType() == route.getType();
    }

    public static BigDecimal getDeliveryPrice(Transport transport, Route route) {
        return transport.getPrice().multiply(route.getLength());
    }

    public static Predicate<Transport> isPriceInRange(Route route, BigDecimalRange range) {
        return transport -> range
                .isValueInRange(
                        getDeliveryPrice(transport, route));
    }
}
