package com.restservice.test;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class NumbersToSort {

    final private int[] numbers;
    final private String order;

    public NumbersToSort(@JsonProperty("numbers") int[] numbers, @JsonProperty("order") String order) {
        this.numbers = numbers;
        this.order = order;
    }

    public void Sorting() {
        Arrays.sort(this.numbers);
        if (this.order.equals("DESC")) {
            //reverse array "in-place"
            for (int i = 0; i < numbers.length / 2; i++) {
                int temp = numbers[i];
                numbers[i] = numbers[numbers.length - i - 1];
                numbers[numbers.length - i - 1] = temp;
            }
        }
    }

    public int[] getNumbers() {
        return numbers;
    }

    public String getOrder() {
        return order;
    }
}
