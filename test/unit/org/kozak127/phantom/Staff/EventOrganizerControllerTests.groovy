package org.kozak127.phantom.Staff



import org.junit.*
import grails.test.mixin.*

@TestFor(EventOrganizerController)
@Mock(EventOrganizer)
class EventOrganizerControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/eventOrganizer/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.eventOrganizerInstanceList.size() == 0
        assert model.eventOrganizerInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.eventOrganizerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.eventOrganizerInstance != null
        assert view == '/eventOrganizer/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/eventOrganizer/show/1'
        assert controller.flash.message != null
        assert EventOrganizer.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/eventOrganizer/list'

        populateValidParams(params)
        def eventOrganizer = new EventOrganizer(params)

        assert eventOrganizer.save() != null

        params.id = eventOrganizer.id

        def model = controller.show()

        assert model.eventOrganizerInstance == eventOrganizer
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/eventOrganizer/list'

        populateValidParams(params)
        def eventOrganizer = new EventOrganizer(params)

        assert eventOrganizer.save() != null

        params.id = eventOrganizer.id

        def model = controller.edit()

        assert model.eventOrganizerInstance == eventOrganizer
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/eventOrganizer/list'

        response.reset()

        populateValidParams(params)
        def eventOrganizer = new EventOrganizer(params)

        assert eventOrganizer.save() != null

        // test invalid parameters in update
        params.id = eventOrganizer.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/eventOrganizer/edit"
        assert model.eventOrganizerInstance != null

        eventOrganizer.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/eventOrganizer/show/$eventOrganizer.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        eventOrganizer.clearErrors()

        populateValidParams(params)
        params.id = eventOrganizer.id
        params.version = -1
        controller.update()

        assert view == "/eventOrganizer/edit"
        assert model.eventOrganizerInstance != null
        assert model.eventOrganizerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/eventOrganizer/list'

        response.reset()

        populateValidParams(params)
        def eventOrganizer = new EventOrganizer(params)

        assert eventOrganizer.save() != null
        assert EventOrganizer.count() == 1

        params.id = eventOrganizer.id

        controller.delete()

        assert EventOrganizer.count() == 0
        assert EventOrganizer.get(eventOrganizer.id) == null
        assert response.redirectedUrl == '/eventOrganizer/list'
    }
}
