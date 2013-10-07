package org.kozak127.phantom.Staff
import org.kozak127.phantom.Stall

class StallWorker extends StaffMember {

    static belongsTo = [stall: Stall]
    static constraints = {
    	stall(nullable:false)
    }
}
