package org.kozak127.phantom.Staff
import org.kozak127.phantom.ProgramItem

class ProgramItemWorker extends StaffMember {

    static belongsTo = [programItem: ProgramItem]
    static constraints = {
    	programItem(nullable:false)
    }
}
