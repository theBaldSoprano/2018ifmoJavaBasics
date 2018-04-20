package com.study.itmo.gregory.lesson7.eventhub.events;

import com.study.itmo.gregory.lesson7.OnNewMail;


@OnNewMail
public class NewMail{
    private int amount;
    public NewMail(int amount) {
        this.amount = amount;
    }
    public int getAmount() {
        return amount;
    }


    public String getMessage() {
        if (amount == 1) return "You got new message!";
        else return String.format("You got %d new messages", amount);
    }
}
