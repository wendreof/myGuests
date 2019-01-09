package com.example.wlf.meusconvidados.entities;

public class GuestCount
{
    private int presentCount;
    private int absentCount;
    private int allInvited;

    public GuestCount(int present, int absent, int total)
    {
        this.presentCount = present;
        this.absentCount = absent;
        this.allInvited = total;
    }
    public int getPresentCount() {
        return presentCount;
    }

    public void setPresentCount(int presentCount) {
        this.presentCount = presentCount;
    }

    public int getAbsentCount() {
        return absentCount;
    }

    public void setAbsentCount(int absentCount) {
        this.absentCount = absentCount;
    }

    public int getAllInvited() {
        return allInvited;
    }

    public void setAllInvited(int allInvited) {
        this.allInvited = allInvited;
    }
}
