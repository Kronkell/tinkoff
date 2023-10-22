package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    Task6.StockMarketImpl stockMarket = new Task6.StockMarketImpl();

    @Test
    public void shouldCalculateResultGivenSeveralAdds() {
        stockMarket.add(new Task6.Stock("Google", 14));
        stockMarket.add(new Task6.Stock("Amazon", 20));
        stockMarket.add(new Task6.Stock("Facebook", 10));
        stockMarket.add(new Task6.Stock("Yandex", 9));
        stockMarket.add(new Task6.Stock("Netfix", 12));
        Task6.Stock actualMostValuableStock = stockMarket.moseValuableStock();

        Task6.Stock expectedStock = new Task6.Stock("Amazon", 20);

        assertThat(actualMostValuableStock).isEqualTo(expectedStock);
    }

    @Test
    public void shouldCalculateResultGivenAddsAndRemoves() {
        Task6.Stock amazonStock = new Task6.Stock("Amazon", 20);

        stockMarket.add(new Task6.Stock("Google", 14));
        stockMarket.add(amazonStock);
        stockMarket.add(new Task6.Stock("Facebook", 10));
        stockMarket.add(new Task6.Stock("Yandex", 9));
        stockMarket.add(new Task6.Stock("Netfix", 12));
        stockMarket.remove(amazonStock);
        Task6.Stock actualMostValuableStock = stockMarket.moseValuableStock();

        Task6.Stock expectedStock = new Task6.Stock("Google", 14);

        assertThat(actualMostValuableStock).isEqualTo(expectedStock);
    }
}
