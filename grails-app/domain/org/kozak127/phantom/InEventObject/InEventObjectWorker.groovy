package org.kozak127.phantom.InEventObject

class InEventObjectWorker extends InEventObjectStaff {

    boolean accepted = false

    static def create(Map properties) {
    	withTransaction {
			InEventObjectStaff object = new InEventObjectStaff()
			object.update(properties)

			return object
		}
    }

    def update(Map properties) {
    	withTransaction {
			this.properties = properties
			save()

			return this
		}
    }
}
