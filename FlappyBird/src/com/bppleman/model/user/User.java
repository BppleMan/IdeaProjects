package com.bppleman.model.user;

import java.util.ArrayList;

/**
 * Created by BppleMan on 17/5/29.
 */
public class User
{
    private String name;
    private String password;
    private int score;
    private ArrayList<Integer> finishedLeverl;
    private static User instance;

    public synchronized static User getInstance()
    {
        if (instance == null)
            throw new NullPointerException("User单例尚未初始化！");
        return instance;
    }

    public static void creatUser(String name, String password)
    {
        if (instance == null)
        {
            instance = new User(name, password);
        }
    }

    private User(String name, String password)
    {
        this.name = name;
        this.password = password;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public ArrayList<Integer> getFinishedLeverl()
    {
        return finishedLeverl;
    }

    public void setFinishedLeverl(ArrayList<Integer> finishedLeverl)
    {
        this.finishedLeverl = finishedLeverl;
    }
}
