package org.kozak127.phantom.InEventObject
import org.kozak127.phantom.Event
import org.kozak127.phantom.InEventObject.InEventObjectCreator

class InEventObject {

    boolean accepted = false
    Date creationDate

    static belongsTo = [event: Event, creator: InEventObjectCreator]

    static constraints = {
        creator(nullable: false)
        event(nullable: false)
    }
}
