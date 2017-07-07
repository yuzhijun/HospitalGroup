package com.lenovohit.lartemis_api.model;

import java.util.List;

/**
 * Created by yuzhijun on 2016/8/1.
 */
public class DoctorAppoint {
    /**
     * AID : sample string 1
     * AppType : sample string 2
     * AppTypeName : sample string 3
     * Note : sample string 4
     * State : sample string 5
     * Money : 6.0
     * AppDate : sample string 7
     * AppStart : sample string 8
     * AppEnd : sample string 9
     * SuplusNum : sample string 10
     * Suplus : null
     */
    private String AID;
    private String AppType;
    private String AppTypeName;
    private String Note;
    private String State;
    private double Money;
    private String AppDate;
    private String AppStart;
    private String AppEnd;
    private String SuplusNum;
    private List<SuplusInfo> Suplus;

    public String getAID() {
        return AID;
    }

    public void setAID(String AID) {
        this.AID = AID;
    }

    public String getAppType() {
        return AppType;
    }

    public void setAppType(String AppType) {
        this.AppType = AppType;
    }

    public String getAppTypeName() {
        return AppTypeName;
    }

    public void setAppTypeName(String AppTypeName) {
        this.AppTypeName = AppTypeName;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public double getMoney() {
        return Money;
    }

    public void setMoney(double Money) {
        this.Money = Money;
    }

    public String getAppDate() {
        return AppDate;
    }

    public void setAppDate(String AppDate) {
        this.AppDate = AppDate;
    }

    public String getAppStart() {
        return AppStart;
    }

    public void setAppStart(String AppStart) {
        this.AppStart = AppStart;
    }

    public String getAppEnd() {
        return AppEnd;
    }

    public void setAppEnd(String AppEnd) {
        this.AppEnd = AppEnd;
    }

    public String getSuplusNum() {
        return SuplusNum;
    }

    public void setSuplusNum(String SuplusNum) {
        this.SuplusNum = SuplusNum;
    }

    public List<SuplusInfo> getSuplus() {
        return Suplus;
    }

    public void setSuplus(List<SuplusInfo> suplus) {
        Suplus = suplus;
    }

   public static class SuplusInfo{
        private String SupID;
        private String Name;
        private String NoNum;
        private String Start;
        private String End;

        public String getSupID() {
            return SupID;
        }

        public void setSupID(String supID) {
            SupID = supID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getNoNum() {
            return NoNum;
        }

        public void setNoNum(String noNum) {
            NoNum = noNum;
        }

        public String getStart() {
            return Start;
        }

        public void setStart(String start) {
            Start = start;
        }

        public String getEnd() {
            return End;
        }

        public void setEnd(String end) {
            End = end;
        }
    }
}
