package org.kozak127.phantom.InEventObject
import org.kozak127.phantom.Reservation

class InEventObjectStaff {

    Date creationDate = new Date()
    boolean creator = false

    static belongsTo = [reservation: Reservation, inEventObject: InEventObject]    
    
    static constraints = {
        reservation(nullable:false)
        inEventObject(nullable:false)
        creationDate(nullable: false)
    }

    def getUser() {
        return reservation.user
    }
}