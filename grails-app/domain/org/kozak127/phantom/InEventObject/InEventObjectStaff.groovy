package org.kozak127.phantom.InEventObject
import org.kozak127.phantom.Reservation

class InEventObjectStaff {

	static belongsTo = [reservation: Reservation]
	Date creationDate
    
    static constraints = {
    	reservation(nullable:false)
    }
}