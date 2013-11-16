package org.kozak127.phantom.InEventObject

class InEventObjectCreator extends InEventObjectStaff  {

	static def create(Map properties) {
    	withTransaction {
			InEventObjectCreator object = new InEventObjectCreator()
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
