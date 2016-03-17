package com.gabik.metro.model;

/**
 * Created by GaBiK on 22.02.2016.
 */
public class IdCommunication {

    public IdCommunication(int idOne, int idTwo) {
        this.idOne = idOne;
        this.idTwo = idTwo;
    }

    public int idOne;
    public int idTwo;

    @Override
    public String toString() {
        return "ID первой станции: " + idOne + ". ID второй станции: " + idTwo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdCommunication that = (IdCommunication) o;

        if ((idOne == that.idOne && idTwo == that.idTwo) ||
                (idOne == that.idTwo && idTwo == that.idOne)){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int higherNumber,lowerNumber;
        if (idOne > idTwo){
            higherNumber = idOne;
            lowerNumber = idTwo;
        } else {
            higherNumber = idTwo;
            lowerNumber = idOne;
        }
        int result = higherNumber;
        result = 31 * result + lowerNumber;
        return result;
    }
}
