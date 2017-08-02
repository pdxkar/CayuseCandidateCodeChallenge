package com.cayuse;

public class HelloWorld
{

    private String name = "";

    public String getName()
    {
        return name;
    }

    public String getMessage()
    {
        if (name == "")
        {
            return "Hello!";
        }
        else
        {
            return "Hello " + name + "!";
        }
    }

    public void setName(String name)
    {
        this.name = name;
    }


    public String concatAndConvertString(String str1, String str2){
        String concatedString=str1.concat(str2);
        return concatedString.toUpperCase();
    }

    public int getUniqueId(){
        return 42;
    }

}