package org.example;

public class Hotel
{
    private String name;
    private int weekdayRateRegular;
    private int weekendRateRegular;
    private int weekdayRateReward;
    private int weekendRateReward;
    private int rating;

    public Hotel(String name, int weekdayRateRegular, int weekendRateRegular, int weekdayRateReward, int weekendRateReward, int rating) {
        this.name = name;
        this.weekdayRateRegular = weekdayRateRegular;
        this.weekendRateRegular = weekendRateRegular;
        this.weekdayRateReward = weekdayRateReward;
        this.weekendRateReward = weekendRateReward;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public int getWeekdayRate(boolean isRewardCustomer) {
        return isRewardCustomer ? weekdayRateReward : weekdayRateRegular;
    }

    public int getWeekendRate(boolean isRewardCustomer) {
        return isRewardCustomer ? weekendRateReward : weekendRateRegular;
    }

    public int getRating() {
        return rating;
    }
}
