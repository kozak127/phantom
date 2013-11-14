package org.kozak127.phantom.InEventObject
import org.kozak127.phantom.Reservation

class InEventObjectStaff {

    Date creationDate

    static belongsTo = [reservation: Reservation]    
    
    static constraints = {
        reservation(nullable:false)
    }
}