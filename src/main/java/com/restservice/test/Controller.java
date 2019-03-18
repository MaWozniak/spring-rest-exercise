package com.restservice.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class Controller {

    @RequestMapping("/status/ping")
    public String getPing() {
        return "pong";
    }


    @RequestMapping(value = "/numbers/sort-command")
    public SortedNumbers getNumbers(@RequestBody NumbersToSort numbers) {
        if (numbers.getNumbers() == null || numbers.getOrder() == null) {
            throw new NullException();
        }

        if (!(numbers.getOrder().equals("ASC") || numbers.getOrder().equals("DESC"))) {
            throw new WrongOrderException();
        }

        numbers.Sorting();
        return new SortedNumbers(numbers.getNumbers());
    }


    @RequestMapping(value = "/currencies/get-current-currency-value-command")
    public CurrencyValue getMidConversionRate(@RequestBody CurrencyName currency) {

        if (currency.getName() == null) {
            throw new NullException();
        }

        final RestTemplate restTemplate = new RestTemplate();

        final ResponseEntity<List<Table>> resp = restTemplate.exchange("http://api.nbp.pl/api/exchangerates/tables/A?format=json",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Table>>() {
                });

        final Table table = resp.getBody().get(0);

        final Rate rate1 = table.rates.stream().filter(rate
                -> rate.code.equals(currency.getName())).findFirst().orElseThrow(()
                -> new WrongCurrencyException());

        return new CurrencyValue(rate1.mid);
    }


    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class Table {
        private List<Rate> rates;
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class Rate {
        private String code;
        private double mid;
    }


    @ResponseStatus(value = HttpStatus.NOT_FOUND,
            reason = "Empty field")
    @ExceptionHandler(NullException.class)
    private void emptyNameException() {
    }


    @ResponseStatus(value = HttpStatus.BAD_REQUEST,
            reason = "Bad request")
    @ExceptionHandler(WrongOrderException.class)
    public void handlerWrongOrderException() {
    }

}