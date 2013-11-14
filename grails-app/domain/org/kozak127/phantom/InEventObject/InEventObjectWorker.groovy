package org.kozak127.phantom.InEventObject

class InEventObjectWorker extends InEventObjectStaff {

    boolean accepted = false

    static belongsTo = [inEventObject: InEventObject]
    
    static constraints = {
        inEventObject(nullable:false)
    }
}
