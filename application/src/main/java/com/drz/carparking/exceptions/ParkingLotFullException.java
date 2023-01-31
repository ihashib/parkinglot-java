package com.drz.carparking.exceptions;

import static com.drz.carparking.utils.MessageConstants.PARKING_LOT_FULL_MSG;

public class ParkingLotFullException extends RuntimeException {

    @Override
    public String getMessage() {
        return PARKING_LOT_FULL_MSG;
    }
}
