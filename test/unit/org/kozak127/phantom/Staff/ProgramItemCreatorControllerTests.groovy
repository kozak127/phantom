package org.kozak127.phantom.Staff



import org.junit.*
import grails.test.mixin.*

@TestFor(ProgramItemCreatorController)
@Mock(ProgramItemCreator)
class ProgramItemCreatorControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/programItemCreator/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.programItemCreatorInstanceList.size() == 0
        assert model.programItemCreatorInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.programItemCreatorInstance != null
    }

    void testSave() {
        controller.save()

        assert model.programItemCreatorInstance != null
        assert view == '/programItemCreator/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/programItemCreator/show/1'
        assert controller.flash.message != null
        assert ProgramItemCreator.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/programItemCreator/list'

        populateValidParams(params)
        def programItemCreator = new ProgramItemCreator(params)

        assert programItemCreator.save() != null

        params.id = programItemCreator.id

        def model = controller.show()

        assert model.programItemCreatorInstance == programItemCreator
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/programItemCreator/list'

        populateValidParams(params)
        def programItemCreator = new ProgramItemCreator(params)

        assert programItemCreator.save() != null

        params.id = programItemCreator.id

        def model = controller.edit()

        assert model.programItemCreatorInstance == programItemCreator
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/programItemCreator/list'

        response.reset()

        populateValidParams(params)
        def programItemCreator = new ProgramItemCreator(params)

        assert programItemCreator.save() != null

        // test invalid parameters in update
        params.id = programItemCreator.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/programItemCreator/edit"
        assert model.programItemCreatorInstance != null

        programItemCreator.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/programItemCreator/show/$programItemCreator.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        programItemCreator.clearErrors()

        populateValidParams(params)
        params.id = programItemCreator.id
        params.version = -1
        controller.update()

        assert view == "/programItemCreator/edit"
        assert model.programItemCreatorInstance != null
        assert model.programItemCreatorInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/programItemCreator/list'

        response.reset()

        populateValidParams(params)
        def programItemCreator = new ProgramItemCreator(params)

        assert programItemCreator.save() != null
        assert ProgramItemCreator.count() == 1

        params.id = programItemCreator.id

        controller.delete()

        assert ProgramItemCreator.count() == 0
        assert ProgramItemCreator.get(programItemCreator.id) == null
        assert response.redirectedUrl == '/programItemCreator/list'
    }
}
