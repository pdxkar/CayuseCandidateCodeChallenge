package com.cayuse;

public class HelloWorld
{

    private String name = "";

    public int getUniqueId() {
        return 42;
    }

    public String concatAndConvertString(String str1, String str2) {
        String concatedString=str1.concat(str2);
        return concatedString.toUpperCase();
    }

    public String getMessage() {
        if (name == "")
        {
            return "Hello!";
        }
        else
        {
            return "Hello " + name + "!";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }






}