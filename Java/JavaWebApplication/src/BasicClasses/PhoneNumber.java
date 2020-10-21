package BasicClasses;

import java.sql.*;
import java.util.Properties;

public class PhoneNumber {
    private int phoneNum;
    PhoneNumber(int phoneNum)
    {
        this.phoneNum = phoneNum;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }


    public void showPhoneNumber()
    {
        System.out.println(this.phoneNum);
    }

}
