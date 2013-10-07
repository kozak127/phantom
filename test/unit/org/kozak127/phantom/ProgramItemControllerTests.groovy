package org.kozak127.phantom



import org.junit.*
import grails.test.mixin.*

@TestFor(ProgramItemController)
@Mock(ProgramItem)
class ProgramItemControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/programItem/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.programItemInstanceList.size() == 0
        assert model.programItemInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.programItemInstance != null
    }

    void testSave() {
        controller.save()

        assert model.programItemInstance != null
        assert view == '/programItem/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/programItem/show/1'
        assert controller.flash.message != null
        assert ProgramItem.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/programItem/list'

        populateValidParams(params)
        def programItem = new ProgramItem(params)

        assert programItem.save() != null

        params.id = programItem.id

        def model = controller.show()

        assert model.programItemInstance == programItem
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/programItem/list'

        populateValidParams(params)
        def programItem = new ProgramItem(params)

        assert programItem.save() != null

        params.id = programItem.id

        def model = controller.edit()

        assert model.programItemInstance == programItem
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/programItem/list'

        response.reset()

        populateValidParams(params)
        def programItem = new ProgramItem(params)

        assert programItem.save() != null

        // test invalid parameters in update
        params.id = programItem.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/programItem/edit"
        assert model.programItemInstance != null

        programItem.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/programItem/show/$programItem.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        programItem.clearErrors()

        populateValidParams(params)
        params.id = programItem.id
        params.version = -1
        controller.update()

        assert view == "/programItem/edit"
        assert model.programItemInstance != null
        assert model.programItemInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/programItem/list'

        response.reset()

        populateValidParams(params)
        def programItem = new ProgramItem(params)

        assert programItem.save() != null
        assert ProgramItem.count() == 1

        params.id = programItem.id

        controller.delete()

        assert ProgramItem.count() == 0
        assert ProgramItem.get(programItem.id) == null
        assert response.redirectedUrl == '/programItem/list'
    }
}
