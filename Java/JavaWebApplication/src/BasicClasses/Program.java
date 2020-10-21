package BasicClasses;

public class Program {
    private String programName;
    private int minutesToTalk, mbToSpend,smsToSpend,chargePerSMS, chargePerMin,chargePerMb, planID;

    Program(int planID, String programName, int chargePerMin, int chargePerMb,int chargePerSMS, int minutesToTalk, int mbToSpend,int smsToSpend)
    {
        this.planID = planID;
        this.programName = programName;
        this.chargePerMin = chargePerMin;
        this.chargePerMb = chargePerMb;
        this.chargePerSMS = chargePerSMS;
        this.minutesToTalk = minutesToTalk;
        this.mbToSpend = mbToSpend;
        this.smsToSpend = smsToSpend;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public int getMinutesToTalk() {
        return minutesToTalk;
    }

    public void setMinutesToTalk(int minutesToTalk) {
        this.minutesToTalk = minutesToTalk;
    }

    public int getMbToSpend() {
        return mbToSpend;
    }

    public void setMbToSpend(int mbToSpend) {
        this.mbToSpend = mbToSpend;
    }

    public int getChargePerMinute() {
        return chargePerMin;
    }

    public void SetChargePerMinute(int chargePerMin) {
        this.chargePerMin = chargePerMin;
    }

    public int getChargePerMB() {
        return chargePerMb;
    }

    public void SetChargePerMB(int chargePerMb) {
        this.chargePerMb = chargePerMb;
    }

    public int getPlanID() {
        return planID;
    }

    public void SetPlanID(int PlanID){
        this.planID = PlanID;
    }

    @Override
    public String toString() {
        return "<tr><td>"+planID + "</td><td>" + programName + "</td><td>" + chargePerMin + "</td><td>" + chargePerMb + "</td><td>"+chargePerSMS+"</td><td>" + minutesToTalk + "</td><td>" + mbToSpend +"</td><td>"+smsToSpend+"</td></tr>";
    }
}