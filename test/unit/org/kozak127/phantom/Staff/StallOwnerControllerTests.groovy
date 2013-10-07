package org.kozak127.phantom.Staff



import org.junit.*
import grails.test.mixin.*

@TestFor(StallOwnerController)
@Mock(StallOwner)
class StallOwnerControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/stallOwner/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.stallOwnerInstanceList.size() == 0
        assert model.stallOwnerInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.stallOwnerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.stallOwnerInstance != null
        assert view == '/stallOwner/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/stallOwner/show/1'
        assert controller.flash.message != null
        assert StallOwner.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/stallOwner/list'

        populateValidParams(params)
        def stallOwner = new StallOwner(params)

        assert stallOwner.save() != null

        params.id = stallOwner.id

        def model = controller.show()

        assert model.stallOwnerInstance == stallOwner
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/stallOwner/list'

        populateValidParams(params)
        def stallOwner = new StallOwner(params)

        assert stallOwner.save() != null

        params.id = stallOwner.id

        def model = controller.edit()

        assert model.stallOwnerInstance == stallOwner
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/stallOwner/list'

        response.reset()

        populateValidParams(params)
        def stallOwner = new StallOwner(params)

        assert stallOwner.save() != null

        // test invalid parameters in update
        params.id = stallOwner.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/stallOwner/edit"
        assert model.stallOwnerInstance != null

        stallOwner.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/stallOwner/show/$stallOwner.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        stallOwner.clearErrors()

        populateValidParams(params)
        params.id = stallOwner.id
        params.version = -1
        controller.update()

        assert view == "/stallOwner/edit"
        assert model.stallOwnerInstance != null
        assert model.stallOwnerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/stallOwner/list'

        response.reset()

        populateValidParams(params)
        def stallOwner = new StallOwner(params)

        assert stallOwner.save() != null
        assert StallOwner.count() == 1

        params.id = stallOwner.id

        controller.delete()

        assert StallOwner.count() == 0
        assert StallOwner.get(stallOwner.id) == null
        assert response.redirectedUrl == '/stallOwner/list'
    }
}
