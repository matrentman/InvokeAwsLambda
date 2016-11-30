package com.emptylogic.dto;

public class HelloResponse 
{
    private String greeting;

    public HelloResponse(String greeting) 
    {
        this.greeting = greeting;
    }

    public HelloResponse() { }

    public String getGreeting() 
    {
        return greeting;
    }

    public void setGreeting(String greeting) 
    {
        this.greeting = greeting;
    }
}
