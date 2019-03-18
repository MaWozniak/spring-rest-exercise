package com.restservice.test;

import org.junit.Test;

public class ControllerTest {

    //UnitTests

    @Test(expected = NullException.class)
    public void nullCurrency() throws Exception {
        Controller controller = new Controller();
        controller.getMidConversionRate(new CurrencyName(null));
    }

    @Test(expected = WrongCurrencyException.class)
    public void wrongCurrency() throws Exception {
        Controller controller = new Controller();
        controller.getMidConversionRate(new CurrencyName("AAA"));
    }

    @Test(expected = NullException.class)
    public void nullSorting() throws Exception {
        Controller controller = new Controller();
        controller.getNumbers(new NumbersToSort(null, null));
    }

    @Test(expected = NullException.class)
    public void nullNumbers() throws Exception {
        Controller controller = new Controller();
        controller.getNumbers(new NumbersToSort(null, "ASC"));
    }

    @Test(expected = NullException.class)
    public void nullOrder() throws Exception {
        Controller controller = new Controller();
        controller.getNumbers(new NumbersToSort(new int[2], null));
    }

    @Test(expected = WrongOrderException.class)
    public void wrongOrder() throws Exception {
        Controller controller = new Controller();
        controller.getNumbers(new NumbersToSort(new int[2], "RANDOM"));
    }

}